package eh.project.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eh.project.ems.core.entities.concretes.Claim;
import eh.project.ems.entity.concretes.ProjectManager;

public interface ProjectManagerRepository extends JpaRepository<ProjectManager, Long>{
}
