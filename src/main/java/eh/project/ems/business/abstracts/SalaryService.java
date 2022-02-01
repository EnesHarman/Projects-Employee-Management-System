package eh.project.ems.business.abstracts;

import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.entity.concretes.Salary;

public interface SalaryService {
	DataResult<Salary> addSalary(Salary salary);
	Result deleteSalaryById(long id);
}
