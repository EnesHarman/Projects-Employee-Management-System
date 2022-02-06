package eh.project.ems.business.concretes;

import eh.project.ems.business.abstracts.RoleService;
import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.core.utilities.result.SuccessDataResult;
import eh.project.ems.entity.concretes.Role;
import eh.project.ems.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public DataResult<Role> getSystemManagerRole() {
        return new SuccessDataResult<Role> (this.roleRepository.getSystemManagerRole());
    }

    @Override
    public DataResult<Role> getProjectManagerRole() {
        return new SuccessDataResult<Role> (this.roleRepository.getProjectManagerRole());
    }

    @Override
    public DataResult<Role> getTeamLeaderRole() {
        return new SuccessDataResult<Role> (this.roleRepository.getTeamLeaderRole());
    }

    @Override
    public DataResult<Role> getTeamMemberRole() {
        return new SuccessDataResult<Role> (this.roleRepository.getTeamMemberRole());
    }
}
