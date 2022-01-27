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

import eh.project.ems.core.entities.concretes.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="team_leaders")
public class TeamLeader {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long teamLeaderId;
	
	@Column(name="leader_since")
	private Date teamLeaderSince= new Date();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="team_id")
	private Team team;
}