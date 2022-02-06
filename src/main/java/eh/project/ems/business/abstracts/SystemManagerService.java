package eh.project.ems.business.abstracts;

import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.entity.dto.requests.CreateProjectRequest;
import eh.project.ems.entity.dto.requests.SystemManagerRegisterRequest;

public interface SystemManagerService {

	Result register(SystemManagerRegisterRequest systemManagerRegisterRequest);
	
	Result addProject(CreateProjectRequest createProjectRequest);

	Result deleteProject(long projectId);
}
