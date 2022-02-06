package eh.project.ems.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eh.project.ems.business.abstracts.ProjectManagerService;
import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.entity.dto.requests.AddTeamRequest;

@RestController
@RequestMapping("/api/projectmanagerpanel")
public class ProjectManagerController {
	private final ProjectManagerService projectManagerService;

	public ProjectManagerController(ProjectManagerService projectManagerService) {
		super();
		this.projectManagerService = projectManagerService;
	}
	
	@PostMapping("/project/addteam")
	public ResponseEntity<?> addTeamToProject(@RequestBody AddTeamRequest addTeamRequest){
		Result result = this.projectManagerService.addTeamToProject(addTeamRequest);
		if(result.getResult()) {
			return ResponseEntity.ok(result.getMessage());
		}
		else {
			return ResponseEntity.badRequest().body(result.getMessage());
		}
	}
	
	@DeleteMapping("/project/removeteam/{teamId}")
	public ResponseEntity<?> removeTeamFromProject(@PathVariable long teamId){
		Result result = this.projectManagerService.removeTeamFromProject(teamId);
		if(result.getResult()) {
			return ResponseEntity.ok(result.getMessage());
		}
		else {
			return ResponseEntity.badRequest().body(result.getMessage());
		}
	}
}
