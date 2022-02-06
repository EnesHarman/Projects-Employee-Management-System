package eh.project.ems.business.abstracts;

import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.entity.concretes.Project;
import eh.project.ems.entity.concretes.Team;
import eh.project.ems.entity.concretes.TeamLeader;
public interface TeamService {
	public DataResult<Team> getDefaultNullTeam();

	public DataResult<Team> createTeam(String teamName, TeamLeader teamLeader,Project project);

	public DataResult<Team> getTeamById(long teamId);

	public Result deleteTeam(Team data);
}
