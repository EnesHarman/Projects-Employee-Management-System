package eh.project.ems.repository;

import eh.project.ems.entity.concretes.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query(value="CALL GET_SYSTEM_MANAGER_ROLE();" ,nativeQuery = true)
    Role getSystemManagerRole();

    @Query(value="CALL GET_PROJECT_MANAGER_ROLE();" ,nativeQuery = true)
    Role getProjectManagerRole();

    @Query(value="CALL GET_TEAM_LEADER_ROLE();" ,nativeQuery = true)
    Role getTeamLeaderRole();

    @Query(value="CALL GET_TEAM_MEMBER_ROLE();" ,nativeQuery = true)
    Role getTeamMemberRole();
}
