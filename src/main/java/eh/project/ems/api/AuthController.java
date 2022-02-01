package eh.project.ems.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import eh.project.ems.business.abstracts.AuthService;
import eh.project.ems.core.utilities.result.DataResult;
import eh.project.ems.core.utilities.result.ErrorResult;
import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.entity.dto.requests.LoginRequest;
import eh.project.ems.entity.dto.requests.SystemManagerRegisterRequest;
import eh.project.ems.entity.dto.requests.TeamMemberRegisterRequest;
import eh.project.ems.entity.dto.responses.LoginResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private final AuthService authService;

	@Autowired
	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
		DataResult<LoginResponse> result = this.authService.login(loginRequest);
		if(result.getResult()) {
			return ResponseEntity.ok(result.getData());
		}
		else {
			return ResponseEntity.badRequest().body(result.getMessage());
		}
	}
	
	
	@PostMapping("/systemmanager/register") // TODO sertifika bazlı 
	public ResponseEntity<?> register(@RequestBody SystemManagerRegisterRequest systemManagerRegisterRequest){
		Result result = this.authService.register(systemManagerRegisterRequest);
		if(result.getResult()) {
			return ResponseEntity.ok(result.getMessage());
		}
		else {
			return ResponseEntity.badRequest().body(result.getMessage());
		}
	}
	
	@PostMapping("/teammember/register")
	public ResponseEntity<?> register(@RequestBody TeamMemberRegisterRequest teamMemberRegisterRequest ){
		Result result = this.authService.register(teamMemberRegisterRequest);
		if(result.getResult()) {
			return ResponseEntity.ok(result.getMessage());
		}
		else {
			return ResponseEntity.badRequest().body(result.getMessage());
		}
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResult handleDuplicateError(DataIntegrityViolationException exceptions){
		return new ErrorResult("Duplicate error! There is already a user with these informations. Please check your email and identity number.");
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ErrorResult handleDuplicateError(BadCredentialsException exceptions){
		return new ErrorResult("Incorrect username or password. Please check your inputs.");
	}
}
