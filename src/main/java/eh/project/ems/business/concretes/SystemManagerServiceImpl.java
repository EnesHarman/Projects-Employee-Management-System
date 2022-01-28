package eh.project.ems.business.concretes;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eh.project.ems.business.abstracts.ClaimService;
import eh.project.ems.business.abstracts.EmployeeService;
import eh.project.ems.business.abstracts.SystemManagerService;
import eh.project.ems.core.entities.concretes.Employee;
import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.entity.dto.requests.SystemManagerRegisterRequest;
import eh.project.ems.repository.SystemManagerRepository;

@Service
public class SystemManagerServiceImpl implements SystemManagerService{

	private final SystemManagerRepository systemManagerRepository;
	private final EmployeeService employeeService;
	private final ClaimService claimService;
	
	@Autowired
	public SystemManagerServiceImpl(SystemManagerRepository systemManagerRepository, EmployeeService employeeService, ClaimService claimService) {
		super();
		this.systemManagerRepository = systemManagerRepository;
		this.employeeService = employeeService;
		this.claimService = claimService;
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
		
		return null;
	}

}
