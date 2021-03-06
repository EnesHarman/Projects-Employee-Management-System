package eh.project.ems.entity.dto.requests;

import eh.project.ems.entity.concretes.Address;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemManagerRegisterRequest {
	
	private String email;
	
	private String password;
	
	private String name;

	private String lastName;

	private String phoneNumber;

	private String identityNumber;

	private String profileInfo;
	
	private Address address;
	

}
