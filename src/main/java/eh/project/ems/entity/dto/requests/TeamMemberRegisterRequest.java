package eh.project.ems.entity.dto.requests;

import java.time.LocalDate;
import java.util.Date;

import eh.project.ems.entity.concretes.Address;
import eh.project.ems.entity.concretes.Salary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamMemberRegisterRequest {
	private String email;
	
	private String password;
	
	private String name;

	private String lastName;

	private String phoneNumber;

	private String identityNumber;

	private String profileInfo;
	
	private Address address;
	
	private Salary salary;
	
	private long departmantId;
	
	private Date memberSince;
}
