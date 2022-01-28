package eh.project.ems.business.concretes;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import eh.project.ems.business.abstracts.AuthService;
import eh.project.ems.business.abstracts.EmployeeService;
import eh.project.ems.business.abstracts.SystemManagerService;
import eh.project.ems.core.security.constants.SecurityConstants;
import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.core.utilities.result.ErrorDataResult;
import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.core.utilities.result.SuccessDataResult;
import eh.project.ems.entity.dto.requests.LoginRequest;
import eh.project.ems.entity.dto.requests.SystemManagerRegisterRequest;
import eh.project.ems.entity.dto.responses.LoginResponse;
@Service
public class AuthServiceImpl implements AuthService{

	private final EmployeeService employeeService;
	private final AuthenticationManager authenticationManager;
	private final SystemManagerService systemManagerService;
	
	@Autowired
	public AuthServiceImpl(EmployeeService employeeService,AuthenticationManager authenticationManager,SystemManagerService systemManagerService) {
		super();
		this.employeeService = employeeService;
		this.authenticationManager = authenticationManager;
		this.systemManagerService =  systemManagerService;
	}

	@Override
	public DataResult<LoginResponse> login(LoginRequest loginRequest) {
		UsernamePasswordAuthenticationToken token = new  UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword());
		Authentication authentication = this.authenticationManager.authenticate(token);
		if(authentication.isAuthenticated()) {
			User user = (User)authentication.getPrincipal();
			Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.SecretKey.getBytes());
			String access_token = JWT.create()
					.withSubject(user.getUsername())
					.withExpiresAt(new Date(System.currentTimeMillis()+SecurityConstants.jwtExpireDate))
					.withClaim("role", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
					.sign(algorithm);
			
			LoginResponse loginResponse = new LoginResponse();
			loginResponse.setToken(access_token);
			loginResponse.setRole(user.getAuthorities().toString()); //TODO FRONTENDE DÜZGÜN BİR ROLE DÖN
			return new SuccessDataResult<LoginResponse>(loginResponse);	
		}
		else {
			return new ErrorDataResult<LoginResponse>();
		}
	}

	@Override
	public Result register(SystemManagerRegisterRequest systemManagerRegisterRequest) {
		return this.systemManagerService.register(systemManagerRegisterRequest);
		
	}
	
}
