package eh.project.ems.business.concretes;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import eh.project.ems.business.abstracts.*;
import eh.project.ems.entity.concretes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eh.project.ems.business.constants.Messages;
import eh.project.ems.core.entities.concretes.Employee;
import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.core.utilities.result.ErrorDataResult;
import eh.project.ems.core.utilities.result.ErrorResult;
import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.core.utilities.result.SuccessDataResult;
import eh.project.ems.core.utilities.result.SuccessResult;
import eh.project.ems.entity.dto.requests.AddTeamRequest;
import eh.project.ems.repository.ProjectManagerRepository;

@Service
public class ProjectManagerServiceImpl implements ProjectManagerService{
	
	private final ProjectManagerRepository projectManagerRepository;
	private final ClaimService claimService;
	private final EmployeeService employeeService;
	private final ProjectService projectService;
	private final TeamService teamService;
	private final TeamMemberService  teamMemberService;
	private final TeamLeaderService teamLeaderService;
	private  final RoleService roleService;
	private final PastRoleService pastRoleService;

	@Autowired
	public ProjectManagerServiceImpl(ProjectManagerRepository projectManagerRepository, ClaimService claimService, EmployeeService employeeService, ProjectService projectService,
									 TeamService teamService, TeamMemberService teamMemberService, TeamLeaderService teamLeaderService, RoleService roleService,PastRoleService pastRoleService) {
		this.projectManagerRepository = projectManagerRepository;
		this.claimService = claimService;
		this.employeeService = employeeService;
		this.projectService = projectService;
		this.teamService = teamService;
		this.teamMemberService = teamMemberService;
		this.teamLeaderService = teamLeaderService;
		this.roleService = roleService;
		this.pastRoleService = pastRoleService;
	}

	@Override
	public DataResult<ProjectManager> assignProjectManager(TeamMember teamMember) {
		ProjectManager projectManager = new ProjectManager(); 
		projectManager.setEmployee(teamMember.getEmployee());
		projectManager.getEmployee().setClaims(this.claimService.getProjectManagerClaims().getData());
		this.employeeService.updateEmployee(projectManager.getEmployee());

		PastRole pastRole = new PastRole(new Date(),this.roleService.getTeamMemberRole().getData(),projectManager.getEmployee());
		this.pastRoleService.addPastRole(pastRole);

		projectManager.setManagerSince(new Date());
		projectManager.setSalary(teamMember.getSalary());	
		this.projectManagerRepository.save(projectManager);

		return new SuccessDataResult<ProjectManager>(projectManager);
	}


	@Override
	public DataResult<ProjectManager> getProjectManagerById(long id) {
		Optional<ProjectManager> projectManager = this.projectManagerRepository.findById(id);
		if(!projectManager.isPresent()) {
			return new ErrorDataResult<ProjectManager>(Messages.ProjectManagerCouldntFind);
		}
		else {
			return new SuccessDataResult<ProjectManager>(projectManager.get());
		}
	}


	@Override
	@Transactional
	public Result deleteProjectManager(ProjectManager projectManager) {
		this.projectManagerRepository.delete(projectManager);
		return new SuccessResult(Messages.ProjectManagerDeleted);
	}


	@Override
	@Transactional
	public Result addTeamToProject(AddTeamRequest addTeamRequest) {
		String teamManagerEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		
		DataResult<Project> projectResult = this.projectService.getProjectByManagerEmail(teamManagerEmail);
		if(!projectResult.getResult()) {
			return new ErrorResult(Messages.SessionError);
		}
		
		DataResult<TeamMember> teamMemberResult = this.teamMemberService.getTeamMember(addTeamRequest.getLeaderId());
		if(!teamMemberResult.getResult()) {
			return new ErrorResult(teamMemberResult.getMessage());
		}
		
		TeamLeader teamLeader = this.teamLeaderService.assignTeamLeader(teamMemberResult.getData()).getData();
		
		this.teamMemberService.deleteTeamMember(teamMemberResult.getData());
		
		Team team = this.teamService.createTeam(addTeamRequest.getTeamName(),teamLeader,projectResult.getData()).getData();
		
		teamLeader.setTeam(team);
		this.teamLeaderService.saveTeamLeader(teamLeader);
		
		return new SuccessResult(Messages.TeamAdded);
	}


	@Override
	@Transactional
	public Result removeTeamFromProject(long teamId) {
		DataResult<Team>teamResult = this.teamService.getTeamById(teamId);
		if(!teamResult.getResult()) {
			return new ErrorResult(teamResult.getMessage());
		}
		TeamLeader teamLeader = teamResult.getData().getTeamLeader();
		
		Result teamMemberAssignResult = this.teamMemberService.assignTeamMember(teamLeader);
		if(!teamMemberAssignResult.getResult()) {
			return new ErrorResult(teamMemberAssignResult.getMessage());
		}
		
		this.teamLeaderService.deleteTeamLeader(teamLeader);
		
		List<TeamMember> teamMembers = teamResult.getData().getTeamMembers();
		teamMembers.forEach(teamMember->{
			this.teamMemberService.setTeamMembersTeamNull(teamMember);
		});
		
		return this.teamService.deleteTeam(teamResult.getData());
	}
}
