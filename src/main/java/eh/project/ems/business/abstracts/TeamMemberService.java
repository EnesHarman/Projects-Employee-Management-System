package eh.project.ems.business.abstracts;

import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.entity.dto.requests.TeamMemberRegisterRequest;

public interface TeamMemberService {

	Result register(TeamMemberRegisterRequest teamMemberRegisterRequest);

}
