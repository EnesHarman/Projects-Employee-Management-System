package eh.project.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eh.project.ems.entity.concretes.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{
	Project getProjectByProjectManager_Employee_Email(String email);
}
