package eh.project.ems.business.concretes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eh.project.ems.business.abstracts.TeamService;
import eh.project.ems.business.constants.Messages;
import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.core.utilities.result.ErrorDataResult;
import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.core.utilities.result.SuccessDataResult;
import eh.project.ems.core.utilities.result.SuccessResult;
import eh.project.ems.entity.concretes.Project;
import eh.project.ems.entity.concretes.Team;
import eh.project.ems.entity.concretes.TeamLeader;
import eh.project.ems.repository.TeamRepository;

@Service
public class TeamServiceImpl implements TeamService{

	private final TeamRepository teamRepository;
	
	@Autowired
	public TeamServiceImpl(TeamRepository teamRepository) {
		super();
		this.teamRepository = teamRepository;
	}

	@Override
	public DataResult<Team> getDefaultNullTeam() {
		
		return new SuccessDataResult<Team>(this.teamRepository.getDefaultNullTeam());
	}

	@Override
	public DataResult<Team> createTeam(String teamName, TeamLeader teamLeader,Project project) {
		Team team = new Team();
		team.setProject(project);
		team.setTeamLeader(teamLeader);
		team.setTeamName(teamName);
		this.teamRepository.save(team);
		return new SuccessDataResult<Team>(team);
	}

	@Override
	public DataResult<Team> getTeamById(long teamId) {
		Optional<Team> team = this.teamRepository.findById(teamId);
		if(!team.isPresent()) {
			return new ErrorDataResult<Team>(Messages.TeamNotFound);
		}
		return new SuccessDataResult<Team>(team.get());
	}

	@Override
	public Result deleteTeam(Team data) {
		this.teamRepository.delete(data);
		return new SuccessResult(Messages.TeamDeleted);
	}

	
}
