package eh.project.ems.entity.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTeamRequest {
	private String teamName;
	private long leaderId;
}
