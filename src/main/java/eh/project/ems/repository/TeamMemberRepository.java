package eh.project.ems.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import eh.project.ems.entity.concretes.TeamMember;
import eh.project.ems.entity.dto.responses.TeamMembersResponse;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long>{
	
	@Query(value="select new eh.project.ems.entity.dto.responses.TeamMembersResponse(tm.id,tm.memberSince, e.email,e.name,e.lastName,e.phoneNumber,e.identityNumber,e.joinAt,te.teamName)"
			+ "From TeamMember tm Inner Join tm.employee e Inner Join tm.team te")
	List<TeamMembersResponse> getAllTeamMembers();
	
	@Query(value="select new eh.project.ems.entity.dto.responses.TeamMembersResponse(tm.id,tm.memberSince, e.email,e.name,e.lastName,e.phoneNumber,e.identityNumber,e.joinAt,te.teamName)"
			+ "From TeamMember tm Inner Join tm.employee e Inner Join tm.team te where tm.team.id != 0")
	List<TeamMembersResponse> getAllTeamMembersWithTeam();
	
	@Query(value="select new eh.project.ems.entity.dto.responses.TeamMembersResponse(tm.id,tm.memberSince, e.email,e.name,e.lastName,e.phoneNumber,e.identityNumber,e.joinAt,te.teamName)"
			+ "From TeamMember tm Inner Join tm.employee e Inner Join tm.team te where tm.team.id = 0")
	List<TeamMembersResponse> getAllTeamMembersWithoutTeam();
	
}
