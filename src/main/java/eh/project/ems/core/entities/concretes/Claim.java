package eh.project.ems.core.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="claims")
public class Claim {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long claimId;
	
	@Column(name="name")
	private long claimName;
	
	@ManyToMany
	@JoinTable(
			  name = "employee_claims", 
			  joinColumns = @JoinColumn(name = "claim_id"), 
			  inverseJoinColumns = @JoinColumn(name = "employee_id"))
	private List<Employee> employees; 
}
