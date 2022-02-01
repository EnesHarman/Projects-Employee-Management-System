package eh.project.ems.business.abstracts;

import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.entity.concretes.ProjectManager;
import eh.project.ems.entity.concretes.TeamMember;

public interface ProjectManagerService {
	DataResult<ProjectManager> assignProjectManager(TeamMember teamMember);
}
