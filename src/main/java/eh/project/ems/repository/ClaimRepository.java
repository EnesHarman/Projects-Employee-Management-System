package eh.project.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eh.project.ems.core.entities.concretes.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Long>{
	
	@Query(value="CALL GET_SYSTEM_MANAGER_CLAIMS();", nativeQuery = true)
	List<Claim> getSystemManagerClaims();

	@Query(value="CALL GET_TEAM_MANAGER_CLAIMS();", nativeQuery = true)
	List<Claim> getTeamMemberClaims();
}
