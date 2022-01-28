package eh.project.ems.business.abstracts;

import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.entity.dto.requests.LoginRequest;
import eh.project.ems.entity.dto.requests.SystemManagerRegisterRequest;
import eh.project.ems.entity.dto.responses.LoginResponse;

public interface AuthService {
	public DataResult<LoginResponse> login(LoginRequest loginRequest);

	public Result register(SystemManagerRegisterRequest systemManagerRegisterRequest);
}
