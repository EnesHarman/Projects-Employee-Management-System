package eh.project.ems.business.abstracts;

import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.entity.concretes.ProjectManager;
import eh.project.ems.entity.concretes.TeamMember;
import eh.project.ems.entity.dto.requests.AddTeamRequest;

public interface ProjectManagerService {
	DataResult<ProjectManager> assignProjectManager(TeamMember teamMember);

	DataResult<ProjectManager> getProjectManagerById(long id);

	Result deleteProjectManager(ProjectManager projectManager);

	Result addTeamToProject(AddTeamRequest addTeamRequest);

	Result removeTeamFromProject(long teamId);
}
