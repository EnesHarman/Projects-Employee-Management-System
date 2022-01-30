package eh.project.ems.business.concretes;

import org.springframework.stereotype.Service;

import eh.project.ems.business.abstracts.SalaryService;
import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.core.utilities.result.SuccessDataResult;
import eh.project.ems.entity.concretes.Salary;
import eh.project.ems.repository.SalaryRepository;

@Service
public class SalaryServiceImpl implements SalaryService{

	private final SalaryRepository salaryRepository;
	
	public SalaryServiceImpl(SalaryRepository salaryRepository) {
		super();
		this.salaryRepository = salaryRepository;
	}

	@Override
	public DataResult<Salary> addSalary(Salary salary) {
		return new SuccessDataResult<Salary>(this.salaryRepository.save(salary));
	}

}
