package eh.project.ems.business.concretes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eh.project.ems.business.abstracts.DepartmantService;
import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.core.utilities.result.SuccessDataResult;
import eh.project.ems.entity.concretes.Departmant;
import eh.project.ems.repository.DepartmantRepository;

@Service
public class DepartmantServiceImpl implements DepartmantService {

	private final DepartmantRepository departmantRepository;
	
	@Autowired
	public DepartmantServiceImpl(DepartmantRepository departmantRepository) {
		super();
		this.departmantRepository = departmantRepository;
	}

	@Override
	public DataResult<Departmant> getSystemManagerDepartmant() {
		
		return new SuccessDataResult<Departmant>(this.departmantRepository.getSystemManagerDepartmant());
	}

	@Override
	public DataResult<Departmant> getById(long id) {
		Optional<Departmant> departman = this.departmantRepository.findById(id);
		if(departman.isPresent()) {
			return new SuccessDataResult<Departmant>(departman.get());
		}
		else {
			return null; //TODO THROW ERROR
		}
	}

}
