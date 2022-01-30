package eh.project.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eh.project.ems.entity.concretes.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Long>{

}
