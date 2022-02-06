package eh.project.ems.business.abstracts;

import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.entity.concretes.Role;

public interface RoleService {
    DataResult<Role> getSystemManagerRole();
    DataResult<Role> getProjectManagerRole();
    DataResult<Role> getTeamLeaderRole();
    DataResult<Role> getTeamMemberRole();
}
