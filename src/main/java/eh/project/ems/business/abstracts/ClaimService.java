package eh.project.ems.business.abstracts;

import java.util.List;

import eh.project.ems.core.entities.concretes.Claim;
import eh.project.ems.core.utilities.result.DataResult;

public interface ClaimService {
	DataResult<List<Claim>> getSystemManagerClaims();
}
