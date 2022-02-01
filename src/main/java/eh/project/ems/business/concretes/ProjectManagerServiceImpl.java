package eh.project.ems.business.concretes;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eh.project.ems.business.abstracts.ClaimService;
import eh.project.ems.business.abstracts.EmployeeService;
import eh.project.ems.business.abstracts.ProjectManagerService;
import eh.project.ems.business.abstracts.TeamMemberService;
import eh.project.ems.core.entities.concretes.Employee;
import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.core.utilities.result.SuccessDataResult;
import eh.project.ems.entity.concretes.ProjectManager;
import eh.project.ems.entity.concretes.TeamMember;
import eh.project.ems.repository.ProjectManagerRepository;

@Service
public class ProjectManagerServiceImpl implements ProjectManagerService{
	
	private final  ProjectManagerRepository projectManagerRepository;
	private final ClaimService claimService;
	private final EmployeeService employeeService;
	
	@Autowired
	public ProjectManagerServiceImpl(ProjectManagerRepository projectManagerRepository, ClaimService claimService,
			EmployeeService employeeService) {
		super();
		this.projectManagerRepository = projectManagerRepository;
		this.claimService = claimService;
		this.employeeService = employeeService;
	}


	@Override
	public DataResult<ProjectManager> assignProjectManager(TeamMember teamMember) {
		ProjectManager projectManager = new ProjectManager(); 
		projectManager.setEmployee(teamMember.getEmployee());
		projectManager.getEmployee().setClaims(this.claimService.getProjectManagerClaims().getData());
		this.employeeService.updateEmployee(projectManager.getEmployee());
		projectManager.setManagerSince(new Date());
		projectManager.setSalary(teamMember.getSalary());	
		this.projectManagerRepository.save(projectManager);

		return new SuccessDataResult<ProjectManager>(projectManager);
	}


}
