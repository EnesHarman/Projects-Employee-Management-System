package eh.project.ems.entity.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name="tasks")
public class Task {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long taskId;
	
	@Column(name="name")
	private long taskName;
	
	@ManyToOne()
	@JoinColumn(name="type_id")
	private TaskType type;
	
	@ManyToMany
	@JoinTable(
			name="employee_tasks",
			joinColumns =@JoinColumn (name="task_id"),
			inverseJoinColumns = @JoinColumn(name="employee_id"))		
	private List<Employee> employees;
}
