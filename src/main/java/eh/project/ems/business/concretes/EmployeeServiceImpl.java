package eh.project.ems.business.concretes;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import eh.project.ems.business.abstracts.EmployeeService;
import eh.project.ems.business.constants.Messages;
import eh.project.ems.core.entities.concretes.Employee;
import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.core.utilities.result.SuccessDataResult;
import eh.project.ems.core.utilities.result.SuccessResult;
import eh.project.ems.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService{

	private final EmployeeRepository employeeRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository,PasswordEncoder passwordEncoder) {
		super();
		this.employeeRepository = employeeRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Employee employee = this.employeeRepository.findByEmail(email);
		if(employee == null) {
			throw new UsernameNotFoundException(Messages.EmployeeNotFound);
		}
		else {
			//TODO LOGGING
			
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
			employee.getClaims().forEach(claim->{
				authorities.add(new SimpleGrantedAuthority(claim.getClaimName()));
			});
			return new User(employee.getEmail(),employee.getPassword(),authorities);
		}
	}

	@Override
	public DataResult<Employee> addEmployee(Employee employee) {
		employee.setPassword(this.passwordEncoder.encode(employee.getPassword()));
		return new SuccessDataResult<Employee>(this.employeeRepository.save(employee));
	}
	
	@Override
	public DataResult<Employee> updateEmployee(Employee employee){
		return new SuccessDataResult<Employee>(this.employeeRepository.save(employee));
	}

	

}
