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
@AllArgsConstructor
@NoArgsConstructor
@Table(name="system_managers")
@Entity
public class SystemManager {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long systemManagerId;
	
	@Column(name="system_manager_since")
	private Date systemManagerSince = new Date();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="employee_id")
	private Employee employee;
}
