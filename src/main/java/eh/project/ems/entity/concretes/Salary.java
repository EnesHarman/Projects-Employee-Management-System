package eh.project.ems.entity.concretes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import eh.project.ems.core.entities.concretes.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="salaries")
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
	private Employee employee;
}
