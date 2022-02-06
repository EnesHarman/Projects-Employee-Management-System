package eh.project.ems.entity.concretes;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="roles")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","pastRoles"})
public class Role {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long roleId;
	
	@Column(name="name")
	private String roleName;

	@OneToMany(mappedBy = "role")
	private List<PastRole> pastRoles;
}
