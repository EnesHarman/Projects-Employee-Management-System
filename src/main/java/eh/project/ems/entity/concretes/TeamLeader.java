package eh.project.ems.entity.concretes;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import eh.project.ems.core.entities.concretes.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="team_leaders")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","team"})
public class TeamLeader {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long teamLeaderId;
	
	@Column(name="leader_since", nullable = true)
	private Date teamLeaderSince;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="team_id")
	private Team team;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="salary_id",nullable = true)
	private Salary salary;
}
