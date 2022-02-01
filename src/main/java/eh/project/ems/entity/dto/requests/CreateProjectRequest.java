package eh.project.ems.entity.dto.requests;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProjectRequest {
	
	private String projectName;
	private String projectDescription;
	private Date endingDate;
	private Date startingDate;
	private long projectManagerId;
	
}
