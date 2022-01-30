package eh.project.ems.business.concretes;

import java.util.Date;

import org.springframework.stereotype.Service;

import eh.project.ems.business.abstracts.AddressService;
import eh.project.ems.business.abstracts.ClaimService;
import eh.project.ems.business.abstracts.DepartmantService;
import eh.project.ems.business.abstracts.EmployeeService;
import eh.project.ems.business.abstracts.SalaryService;
import eh.project.ems.business.abstracts.TeamMemberService;
import eh.project.ems.business.constants.Messages;
import eh.project.ems.core.entities.concretes.Employee;
import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.core.utilities.result.SuccessResult;
import eh.project.ems.entity.concretes.TeamMember;
import eh.project.ems.entity.dto.requests.TeamMemberRegisterRequest;
import eh.project.ems.repository.TeamMemberRepository;

@Service
public class TeamMemberServiceImpl implements TeamMemberService{
	
	private final TeamMemberRepository teamMemberRepository;
	private final DepartmantService departmantService;
	private final SalaryService salaryService;
	private final AddressService addressService;
	private final ClaimService claimService;
	private final EmployeeService employeeService;

	public TeamMemberServiceImpl(TeamMemberRepository teamMemberRepository,DepartmantService departmantService,
			SalaryService salaryService,AddressService addressService,ClaimService claimService, EmployeeService employeeService) {
		super();
		this.teamMemberRepository = teamMemberRepository;
		this.departmantService = departmantService;
		this.salaryService = salaryService;
		this.addressService = addressService;
		this.claimService = claimService;
		this.employeeService =employeeService;
	}

	@Override
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
		this.teamMemberRepository.save(teamMember);
		
		return new SuccessResult(Messages.TeamMemberRegistered);
	};
	
	
	
}
