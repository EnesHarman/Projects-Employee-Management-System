package eh.project.ems.business.concretes;

import java.util.Date;

import eh.project.ems.business.abstracts.*;
import eh.project.ems.entity.concretes.PastRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eh.project.ems.business.constants.Messages;
import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.core.utilities.result.SuccessDataResult;
import eh.project.ems.core.utilities.result.SuccessResult;
import eh.project.ems.entity.concretes.Team;
import eh.project.ems.entity.concretes.TeamLeader;
import eh.project.ems.entity.concretes.TeamMember;
import eh.project.ems.repository.TeamLeaderRepository;

@Service
public class TeamLeaderServiceImpl implements TeamLeaderService{
	private final TeamLeaderRepository teamLeaderRepository;
	private final ClaimService claimService;
	private final EmployeeService employeeService;
	private final TeamService teamService;
	private final RoleService roleService;
	private final PastRoleService pastRoleService;
	
	
	@Autowired
	public TeamLeaderServiceImpl(TeamLeaderRepository teamLeaderRepository, ClaimService claimService,
			EmployeeService employeeService, TeamService teamService,RoleService roleService,PastRoleService pastRoleService) {
		super();
		this.teamLeaderRepository = teamLeaderRepository;
		this.claimService = claimService;
		this.employeeService = employeeService;
		this.teamService = teamService;
		this.roleService = roleService;
		this.pastRoleService = pastRoleService;
	}

	@Transactional
	public DataResult<TeamLeader> assignTeamLeader(TeamMember teamMember) {
		TeamLeader teamLeader = new TeamLeader();
		teamLeader.setEmployee(teamMember.getEmployee());
		teamLeader.getEmployee().setClaims(this.claimService.getTeamLeaderClaims().getData());
		this.employeeService.updateEmployee(teamLeader.getEmployee());

		PastRole pastRole = new PastRole(new Date(),this.roleService.getTeamMemberRole().getData(),teamLeader.getEmployee());
		this.pastRoleService.addPastRole(pastRole);

		teamLeader.setSalary(teamMember.getSalary());
		teamLeader.setTeamLeaderSince(new Date());
		Team team = this.teamService.getDefaultNullTeam().getData();
		teamLeader.setTeam(team);
		
		this.saveTeamLeader(teamLeader);
		return new SuccessDataResult<TeamLeader>(teamLeader);
	}

	@Override
	public Result saveTeamLeader(TeamLeader teamLeader) {
		this.teamLeaderRepository.save(teamLeader);
		return new SuccessResult(Messages.TeamLeaderSaved);
	}

	@Override
	@Transactional
	public Result deleteTeamLeader(TeamLeader teamLeader) {
		this.teamLeaderRepository.delete(teamLeader);
		return new SuccessResult(Messages.TeamLeaderDeleted);
	}
	
	
}
