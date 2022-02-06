package eh.project.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eh.project.ems.entity.concretes.TeamLeader;

public interface TeamLeaderRepository extends JpaRepository<TeamLeader, Long>{
	
	
}
