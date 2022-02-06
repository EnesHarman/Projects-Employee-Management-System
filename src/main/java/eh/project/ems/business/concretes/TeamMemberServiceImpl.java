package eh.project.ems.business.concretes;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import eh.project.ems.business.abstracts.*;
import eh.project.ems.entity.concretes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eh.project.ems.business.constants.Messages;
import eh.project.ems.core.entities.concretes.Employee;
import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.core.utilities.result.ErrorDataResult;
import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.core.utilities.result.SuccessDataResult;
import eh.project.ems.core.utilities.result.SuccessResult;
import eh.project.ems.entity.dto.requests.TeamMemberRegisterRequest;
import eh.project.ems.entity.dto.responses.TeamMembersResponse;
import eh.project.ems.repository.TeamMemberRepository;

@Service
public class TeamMemberServiceImpl implements TeamMemberService{
	
	private final TeamMemberRepository teamMemberRepository;
	private final DepartmantService departmantService;
	private final SalaryService salaryService;
	private final AddressService addressService;
	private final ClaimService claimService;
	private final EmployeeService employeeService;
	private final TeamService teamService;
	private final RoleService roleService;
	private final PastRoleService pastRoleService;
	
	
	@Autowired
	public TeamMemberServiceImpl(TeamMemberRepository teamMemberRepository, DepartmantService departmantService,
			SalaryService salaryService, AddressService addressService, ClaimService claimService,
			EmployeeService employeeService, TeamService teamService,RoleService roleService,PastRoleService pastRoleService) {
		super();
		this.teamMemberRepository = teamMemberRepository;
		this.departmantService = departmantService;
		this.salaryService = salaryService;
		this.addressService = addressService;
		this.claimService = claimService;
		this.employeeService = employeeService;
		this.teamService = teamService;
		this.roleService = roleService;
		this.pastRoleService = pastRoleService;
	}

	@Override
	@Transactional
	public Result register(TeamMemberRegisterRequest teamMemberRegisterRequest) {
		Employee employee = new Employee();
		employee.setEmail(teamMemberRegisterRequest.getEmail());
		employee.setPassword(teamMemberRegisterRequest.getPassword());
		employee.setName(teamMemberRegisterRequest.getName());
		employee.setLastName(teamMemberRegisterRequest.getLastName());
		employee.setPhoneNumber(teamMemberRegisterRequest.getPhoneNumber());
		employee.setIdentityNumber(teamMemberRegisterRequest.getIdentityNumber());
		employee.setJoinAt(new Date());
		employee.setProfileInfo(teamMemberRegisterRequest.getProfileInfo());
		employee.setDepartmant(this.departmantService.getById(teamMemberRegisterRequest.getDepartmantId()).getData());
		employee.setClaims(this.claimService.getTeamMemberClaims().getData());
		this.employeeService.addEmployee(employee);
		
		teamMemberRegisterRequest.getAddress().setEmployee(employee);
		this.addressService.addAddress(teamMemberRegisterRequest.getAddress());
		
		
		TeamMember teamMember = new TeamMember();
		teamMember.setEmployee(employee);
		teamMember.setMemberSince(teamMemberRegisterRequest.getMemberSince());
		teamMember.setSalary(this.salaryService.addSalary(teamMemberRegisterRequest.getSalary()).getData());
		Team team = this.teamService.getDefaultNullTeam().getData();
		teamMember.setTeam(team);
		this.teamMemberRepository.save(teamMember);
		
		return new SuccessResult(Messages.TeamMemberRegistered);
	}

	@Override
	public DataResult<List<TeamMembersResponse>> getAllTeamMembers() {
		return new SuccessDataResult<List<TeamMembersResponse>>(this.teamMemberRepository.getAllTeamMembers());
	}

	@Override
	public DataResult<List<TeamMembersResponse>> getAllTeamMembersWithTeam() {
		return new SuccessDataResult<List<TeamMembersResponse>>( this.teamMemberRepository.getAllTeamMembersWithTeam());
	}

	@Override
	public DataResult<List<TeamMembersResponse>> getAllTeamMembersWithoutTeam() {
		return new SuccessDataResult<List<TeamMembersResponse>>( this.teamMemberRepository.getAllTeamMembersWithoutTeam());
	}

	@Override
	public DataResult<TeamMember> getTeamMember(long id) {
		Optional<TeamMember> teamMember = this.teamMemberRepository.findById(id);
		if(teamMember.isPresent()) {
			return new SuccessDataResult<TeamMember>(teamMember.get());
		}
		else {
			return new ErrorDataResult<TeamMember>(Messages.TeamMemberNotFound);
		}
	}

	@Override
	@Transactional
	public Result deleteTeamMember(TeamMember teamMember) {
		this.teamMemberRepository.deleteById(teamMember.getTeamMemberId());
		return new SuccessResult();
	}

	@Override
	@Transactional
	public Result assignTeamMember(ProjectManager projectManager) {
		TeamMember teamMember = new TeamMember();
		teamMember.setEmployee(projectManager.getEmployee());
		teamMember.getEmployee().setClaims(this.claimService.getTeamMemberClaims().getData());
		this.employeeService.updateEmployee(teamMember.getEmployee());

		PastRole pastRole = new PastRole(new Date(),this.roleService.getProjectManagerRole().getData(),teamMember.getEmployee());
		this.pastRoleService.addPastRole(pastRole);

		teamMember.setMemberSince(new Date());
		teamMember.setSalary(projectManager.getSalary());
		Team team = this.teamService.getDefaultNullTeam().getData();
		teamMember.setTeam(team);
		this.teamMemberRepository.save(teamMember);
		
		return new SuccessResult(Messages.EmployeeAssignAsTeamMember);
	}

	@Override
	@Transactional
	public Result assignTeamMember(TeamLeader teamLeader) {
		TeamMember teamMember = new TeamMember();
		teamMember.setEmployee(teamLeader.getEmployee());
		teamMember.getEmployee().setClaims(this.claimService.getTeamMemberClaims().getData());
		this.employeeService.updateEmployee(teamMember.getEmployee());

		PastRole pastRole = new PastRole(new Date(),this.roleService.getTeamLeaderRole().getData(),teamMember.getEmployee());
		this.pastRoleService.addPastRole(pastRole);

		teamMember.setMemberSince(new Date());
		teamMember.setSalary(teamLeader.getSalary());
		Team team = this.teamService.getDefaultNullTeam().getData();
		teamMember.setTeam(team);
		this.teamMemberRepository.save(teamMember);
		
		return new SuccessResult(Messages.EmployeeAssignAsTeamMember);
	}

	@Override
	public Result setTeamMembersTeamNull(TeamMember teamMember) {
		teamMember.setTeam(this.teamService.getDefaultNullTeam().getData());
		this.teamMemberRepository.save(teamMember);
		return new SuccessResult();
	}

	
	
}
