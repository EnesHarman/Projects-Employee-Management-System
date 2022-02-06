package eh.project.ems.business.abstracts;

import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.entity.concretes.TeamLeader;
import eh.project.ems.entity.concretes.TeamMember;

public interface TeamLeaderService {
	public DataResult<TeamLeader> assignTeamLeader(TeamMember teamMember);
	public Result saveTeamLeader(TeamLeader teamLeader);
	public Result deleteTeamLeader(TeamLeader teamLeader);
}
