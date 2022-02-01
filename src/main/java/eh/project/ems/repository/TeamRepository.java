package eh.project.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eh.project.ems.entity.concretes.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
	
	@Query(value="CALL GET_DEFAULT_NULL_TEAM();",nativeQuery = true)
	Team getDefaultNullTeam();
}
