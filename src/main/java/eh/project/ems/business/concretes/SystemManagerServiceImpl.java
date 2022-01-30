package eh.project.ems.business.concretes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import eh.project.ems.business.abstracts.AddressService;
import eh.project.ems.business.abstracts.ClaimService;
import eh.project.ems.business.abstracts.DepartmantService;
import eh.project.ems.business.abstracts.EmployeeService;
import eh.project.ems.business.abstracts.SystemManagerService;
import eh.project.ems.business.constants.Messages;
import eh.project.ems.core.entities.concretes.Employee;
import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.core.utilities.result.SuccessResult;
import eh.project.ems.entity.concretes.Address;
import eh.project.ems.entity.concretes.SystemManager;
import eh.project.ems.entity.dto.requests.SystemManagerRegisterRequest;
import eh.project.ems.repository.AddressRepository;
import eh.project.ems.repository.SystemManagerRepository;

@Service
public class SystemManagerServiceImpl implements SystemManagerService{

	private final SystemManagerRepository systemManagerRepository;
	private final EmployeeService employeeService;
	private final ClaimService claimService;
	private final DepartmantService departmantService;
	private final AddressService addressService;
	
	@Autowired
	public SystemManagerServiceImpl(SystemManagerRepository systemManagerRepository, EmployeeService employeeService, 
			ClaimService claimService,DepartmantService departmantService,AddressService addressService) {
		super();
		this.systemManagerRepository = systemManagerRepository;
		this.employeeService = employeeService;
		this.claimService = claimService;
		this.departmantService = departmantService;
		this.addressService = addressService;
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

}
