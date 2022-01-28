package eh.project.ems.entity.concretes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="salaries")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","projectManager","teamLeader","teamMember"})
public class Salary {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long salaryId;
	
	@Column(name="amount")
	private long amount;
	
	@Column(name="last_rise_at")
	private Date lastRiseAt;
	
	@Column(name="last_rise_percent")
	private long lastRisePercent;
	
	@OneToOne(mappedBy = "salary")
	private ProjectManager projectManager;

	@OneToOne(mappedBy = "salary")
	private TeamLeader teamLeader;

	@OneToOne(mappedBy = "salary")
	private TeamMember teamMember;
}
