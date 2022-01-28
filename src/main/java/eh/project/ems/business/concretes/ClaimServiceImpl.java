package eh.project.ems.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eh.project.ems.business.abstracts.ClaimService;
import eh.project.ems.core.entities.concretes.Claim;
import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.core.utilities.result.SuccessDataResult;
import eh.project.ems.repository.ClaimRepository;

@Service
public class ClaimServiceImpl implements ClaimService{

	private final ClaimRepository claimRepository;
	
	@Autowired
	public ClaimServiceImpl(ClaimRepository claimRepository) {
		super();
		this.claimRepository = claimRepository;
	}



	@Override
	public DataResult<List<Claim>> getSystemManagerClaims() {
		
		return new SuccessDataResult<List<Claim>>(this.claimRepository.getSystemManagerClaims());
	}

}
