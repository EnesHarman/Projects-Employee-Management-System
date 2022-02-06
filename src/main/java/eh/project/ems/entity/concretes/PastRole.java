package eh.project.ems.entity.concretes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import eh.project.ems.core.entities.concretes.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="past_roles")
public class PastRole {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long pastRoleId;
	
	@Column(name="ending_date")
	private Date endingDate;
	
	@ManyToOne()
	@JoinColumn(name="role_id")
	private Role role;
	
	@ManyToOne()
	@JoinColumn(name="employee_id")
	private Employee employee;

	public PastRole(Date endingDate, Role role, Employee employee){
		this.endingDate = endingDate;
		this.role = role;
		this.employee = employee;
	}
}
