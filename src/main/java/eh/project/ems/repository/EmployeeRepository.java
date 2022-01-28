package eh.project.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eh.project.ems.core.entities.concretes.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Employee findByEmail(String email);

}
