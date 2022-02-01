package eh.project.ems.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eh.project.ems.business.abstracts.SalaryService;
import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.core.utilities.result.SuccessDataResult;
import eh.project.ems.core.utilities.result.SuccessResult;
import eh.project.ems.entity.concretes.Salary;
import eh.project.ems.repository.SalaryRepository;

@Service
public class SalaryServiceImpl implements SalaryService{

	private final SalaryRepository salaryRepository;
	
	@Autowired
	public SalaryServiceImpl(SalaryRepository salaryRepository) {
		super();
		this.salaryRepository = salaryRepository;
	}

	@Override
	public DataResult<Salary> addSalary(Salary salary) {
		return new SuccessDataResult<Salary>(this.salaryRepository.save(salary));
	}

	@Override
	public Result deleteSalaryById(long id) {
		this.salaryRepository.deleteById(id);
		return new SuccessResult();
	}

}
