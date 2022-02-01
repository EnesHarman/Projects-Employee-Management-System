package eh.project.ems.entity.dto.responses;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamMembersResponse {
	private long memberId;
	private Date memberSince;
	private String email;
	private String name;
	private String lastName;
	private String phoneNumber;
	private String identityNumber;
	private Date joinAt;
	private String teamName;
}
