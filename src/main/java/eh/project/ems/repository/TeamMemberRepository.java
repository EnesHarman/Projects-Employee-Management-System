package eh.project.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eh.project.ems.entity.concretes.TeamMember;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long>{

}
