package eh.project.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eh.project.ems.entity.concretes.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
