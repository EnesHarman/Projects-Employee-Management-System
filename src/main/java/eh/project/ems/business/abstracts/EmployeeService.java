package eh.project.ems.business.abstracts;

import eh.project.ems.core.entities.concretes.Employee;
import eh.project.ems.core.utilities.result.DataResult;

public interface EmployeeService {
	DataResult<Employee> addEmployee(Employee employee);
	DataResult<Employee> updateEmployee(Employee employee);
}
