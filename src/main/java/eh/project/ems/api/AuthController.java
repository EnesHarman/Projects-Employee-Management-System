package eh.project.ems.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eh.project.ems.business.abstracts.AuthService;
import eh.project.ems.core.utilities.result.DataResult;
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
}
