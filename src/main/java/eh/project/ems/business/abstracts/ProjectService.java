package eh.project.ems.business.abstracts;

import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.entity.concretes.Project;

public interface ProjectService {
	Result addProject(Project project);
}
