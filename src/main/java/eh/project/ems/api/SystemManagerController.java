package eh.project.ems.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eh.project.ems.business.abstracts.SystemManagerService;
import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.entity.dto.requests.CreateProjectRequest;

@RestController
@RequestMapping("/api/systemmanagerpanel")
public class SystemManagerController {

	private final SystemManagerService systemManagerService;

	@Autowired
	public SystemManagerController(SystemManagerService systemManagerService) {
		super();
		this.systemManagerService = systemManagerService;
	}
	
	@PostMapping("/project/add")
	public ResponseEntity<?> addProject(@RequestBody CreateProjectRequest createProjectRequest){
		Result result = this.systemManagerService.addProject(createProjectRequest);
		if(result.getResult()) {
			return ResponseEntity.ok(result.getMessage());
		}
		else {
			return ResponseEntity.badRequest().body(result.getMessage());
		}
	}
	
}
