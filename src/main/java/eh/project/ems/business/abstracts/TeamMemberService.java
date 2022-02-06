package eh.project.ems.business.abstracts;

import java.util.List;

import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.entity.concretes.ProjectManager;
import eh.project.ems.entity.concretes.TeamLeader;
import eh.project.ems.entity.concretes.TeamMember;
import eh.project.ems.entity.dto.requests.TeamMemberRegisterRequest;
import eh.project.ems.entity.dto.responses.TeamMembersResponse;

public interface TeamMemberService {

	Result register(TeamMemberRegisterRequest teamMemberRegisterRequest);
	
	DataResult<List<TeamMembersResponse>> getAllTeamMembers();
	
	DataResult<List<TeamMembersResponse>> getAllTeamMembersWithTeam();
	
	DataResult<List<TeamMembersResponse>> getAllTeamMembersWithoutTeam();
	
	DataResult<TeamMember> getTeamMember(long id);
	
	Result deleteTeamMember(TeamMember teamMember);

	Result assignTeamMember(ProjectManager projectManager);
	
	Result assignTeamMember(TeamLeader teamLeader);
	
	Result setTeamMembersTeamNull(TeamMember teamMember);
	
}
