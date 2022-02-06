package eh.project.ems.business.concretes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import eh.project.ems.business.abstracts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import eh.project.ems.business.constants.Messages;
import eh.project.ems.core.entities.concretes.Employee;
import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.core.utilities.result.ErrorResult;
import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.core.utilities.result.SuccessDataResult;
import eh.project.ems.core.utilities.result.SuccessResult;
import eh.project.ems.entity.concretes.Address;
import eh.project.ems.entity.concretes.Project;
import eh.project.ems.entity.concretes.ProjectManager;
import eh.project.ems.entity.concretes.SystemManager;
import eh.project.ems.entity.concretes.TeamLeader;
import eh.project.ems.entity.concretes.TeamMember;
import eh.project.ems.entity.dto.requests.CreateProjectRequest;
import eh.project.ems.entity.dto.requests.SystemManagerRegisterRequest;
import eh.project.ems.entity.dto.responses.TeamMembersResponse;
import eh.project.ems.repository.AddressRepository;
import eh.project.ems.repository.SystemManagerRepository;

@Service
public class SystemManagerServiceImpl implements SystemManagerService{

	private final SystemManagerRepository systemManagerRepository;
	private final EmployeeService employeeService;
	private final ClaimService claimService;
	private final DepartmantService departmantService;
	private final AddressService addressService;
	private final TeamMemberService teamMemberService;
	private final ProjectManagerService projectManagerService;
	private final ProjectService projectService;
	private final RoleService roleService;

	@Autowired
	public SystemManagerServiceImpl(SystemManagerRepository systemManagerRepository, EmployeeService employeeService,
									ClaimService claimService, DepartmantService departmantService, AddressService addressService,
									TeamMemberService teamMemberService, ProjectManagerService projectManagerService,
									ProjectService projectService, RoleService roleService) {
		this.systemManagerRepository = systemManagerRepository;
		this.employeeService = employeeService;
		this.claimService = claimService;
		this.departmantService = departmantService;
		this.addressService = addressService;
		this.teamMemberService = teamMemberService;
		this.projectManagerService = projectManagerService;
		this.projectService = projectService;
		this.roleService = roleService;
	}

	@Override
	public Result register(SystemManagerRegisterRequest registerRequest) {
		
		Employee employee =new Employee();
		employee.setEmail(registerRequest.getEmail());
		employee.setPassword(registerRequest.getPassword());
		employee.setName(registerRequest.getName());
		employee.setLastName(registerRequest.getLastName());
		employee.setPhoneNumber(registerRequest.getPhoneNumber());
		employee.setIdentityNumber(registerRequest.getIdentityNumber());
		employee.setJoinAt(new Date());
		employee.setProfileInfo(registerRequest.getProfileInfo());
		employee.setClaims(this.claimService.getSystemManagerClaims().getData());
		employee.setDepartmant(this.departmantService.getSystemManagerDepartmant().getData());
		this.employeeService.addEmployee(employee).getData();

		registerRequest.getAddress().setEmployee(employee);
		this.addressService.addAddress(registerRequest.getAddress());
		
		SystemManager systemManager = new SystemManager();
		systemManager.setSystemManagerSince(new Date());
		systemManager.setEmployee(employee);
		
		this.systemManagerRepository.save(systemManager);
		
		return new SuccessResult(Messages.SystemManagerRegistered);
	}

	@Override
	public Result addProject(CreateProjectRequest createProjectRequest) {
		DataResult<TeamMember> teamMemberResult =  this.teamMemberService.getTeamMember(createProjectRequest.getProjectManagerId());
		if(!teamMemberResult.getResult()) {
			return new ErrorResult(Messages.TeamMemberNotFound);
		}
		ProjectManager projectManager =  this.projectManagerService.assignProjectManager(teamMemberResult.getData()).getData();

		this.teamMemberService.deleteTeamMember(teamMemberResult.getData());
		
		Project project = new Project();
		project.setProjectName(createProjectRequest.getProjectName());
		project.setEndingDate(createProjectRequest.getEndingDate());
		project.setProjectDescription(createProjectRequest.getProjectDescription());
		project.setProjectManager(projectManager);
		project.setStartingDate(createProjectRequest.getStartingDate());
		
		this.projectService.addProject(project);
		return new SuccessResult(Messages.ProjectAdded);
	}


	@Override
	public Result deleteProject(long projectId) {
		DataResult<Project> projectResult = this.projectService.getProjectById(projectId);
		if(!projectResult.getResult()) {
			return new ErrorResult(projectResult.getMessage());
		}
		DataResult<ProjectManager> projectManagerResult = this.projectManagerService.getProjectManagerById(projectResult.getData().getProjectManager().getProjectManagerId());
		if(!projectManagerResult.getResult()) {
			return new ErrorResult(projectResult.getMessage());
		}
		
		this.teamMemberService.assignTeamMember(projectManagerResult.getData());
		
		this.projectService.deleteProject(projectResult.getData());
		
		this.projectManagerService.deleteProjectManager(projectManagerResult.getData());
		
		return new SuccessResult(Messages.ProjectDeleted);
	}

}
