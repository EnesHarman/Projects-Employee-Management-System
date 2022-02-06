package eh.project.ems.business.concretes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eh.project.ems.business.abstracts.ProjectService;
import eh.project.ems.business.constants.Messages;
import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.core.utilities.result.ErrorDataResult;
import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.core.utilities.result.SuccessDataResult;
import eh.project.ems.core.utilities.result.SuccessResult;
import eh.project.ems.entity.concretes.Project;
import eh.project.ems.repository.ProjectRepository;

@Service
public class ProjectServiceImpl  implements ProjectService{

	private final ProjectRepository projectRepository;
	
	@Autowired
	public ProjectServiceImpl(ProjectRepository projectRepository) {
		super();
		this.projectRepository = projectRepository;
	}

	@Override
	public Result addProject(Project project) {
		this.projectRepository.save(project);
		return new SuccessResult(Messages.ProjectAdded);
	}

	@Override
	public DataResult<Project> getProjectById(long projectId) {
		Optional<Project> project = this.projectRepository.findById(projectId);
		if(project.isPresent()) {
			return new SuccessDataResult<Project>(project.get());
		}
		else {			
			return new ErrorDataResult<Project>(Messages.ProjectCouldntFind);
		}
	}

	@Override
	@Transactional
	public Result deleteProject(Project project) {
		try {
			System.out.println(project.getProjectId());
			this.projectRepository.deleteById(project.getProjectId());
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return new SuccessResult(Messages.ProjectDeleted);
	}

	@Override
	public DataResult<Project> getProjectByManagerEmail(String managerEmail) {
		Project project = this.projectRepository.getProjectByProjectManager_Employee_Email(managerEmail);
		if(project ==null) {
			return new ErrorDataResult<Project>();
		}
		return new SuccessDataResult<Project>(project);
	}

}
