package eh.project.ems.business.concretes;

import eh.project.ems.business.abstracts.PastRoleService;
import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.core.utilities.result.SuccessResult;
import eh.project.ems.entity.concretes.PastRole;
import eh.project.ems.repository.PastRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PastRoleServiceImpl implements PastRoleService {

    private final PastRoleRepository pastRoleRepository;

    @Autowired
    public PastRoleServiceImpl(PastRoleRepository pastRoleRepository) {
        this.pastRoleRepository = pastRoleRepository;
    }

    @Override
    public Result addPastRole(PastRole pastRole) {
        this.pastRoleRepository.save(pastRole);
        return new SuccessResult();
    }
}
