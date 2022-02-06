package eh.project.ems.business.abstracts;

import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.entity.concretes.PastRole;

public interface PastRoleService {
    Result addPastRole(PastRole pastRole);
}
