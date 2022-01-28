package eh.project.ems.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eh.project.ems.business.abstracts.EmployeeService;
import eh.project.ems.core.entities.concretes.Employee;
import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.core.utilities.result.SuccessDataResult;
import eh.project.ems.core.utilities.result.SuccessResult;
import eh.project.ems.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private final EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public DataResult<Employee> addEmployee(Employee employee) {
		return new SuccessDataResult<Employee>(this.employeeRepository.save(employee));
	}

}
