package eh.project.ems.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eh.project.ems.business.abstracts.TeamService;
import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.core.utilities.result.SuccessDataResult;
import eh.project.ems.entity.concretes.Team;
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

}
