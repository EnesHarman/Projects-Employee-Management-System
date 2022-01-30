package eh.project.ems.business.abstracts;

import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.entity.concretes.Departmant;

public interface DepartmantService {
	public DataResult<Departmant> getSystemManagerDepartmant();
	public DataResult<Departmant> getById(long id);
}
