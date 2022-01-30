package eh.project.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eh.project.ems.entity.concretes.Departmant;

public interface DepartmantRepository extends JpaRepository<Departmant, Long>{
	
	@Query(value="CALL GET_SYSTEM_MANAGER_DEPARTMANT();",nativeQuery = true)
	public Departmant getSystemManagerDepartmant();
}
