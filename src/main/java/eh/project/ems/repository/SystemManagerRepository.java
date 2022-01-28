package eh.project.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eh.project.ems.entity.concretes.SystemManager;

public interface SystemManagerRepository extends JpaRepository<SystemManager, Long>{
	
}
