package eh.project.ems.business.abstracts;

import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.entity.concretes.Team;

public interface TeamService {
	public DataResult<Team> getDefaultNullTeam();
}
