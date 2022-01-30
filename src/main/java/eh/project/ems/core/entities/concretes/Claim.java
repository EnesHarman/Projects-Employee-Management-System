package eh.project.ems.core.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="claims")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","employees"})
public class Claim {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long claimId;
	
	@Column(name="name")
	private String claimName;
	
	@ManyToMany(mappedBy = "claims",fetch = FetchType.LAZY )
	private List<Employee> employees; 
}
