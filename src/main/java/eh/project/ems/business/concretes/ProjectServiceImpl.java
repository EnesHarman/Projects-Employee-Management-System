package eh.project.ems.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eh.project.ems.business.abstracts.ProjectService;
import eh.project.ems.business.constants.Messages;
import eh.project.ems.core.utilities.result.Result;
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

}
