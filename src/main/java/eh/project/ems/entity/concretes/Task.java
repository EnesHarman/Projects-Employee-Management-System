package eh.project.ems.entity.concretes;

import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import eh.project.ems.core.entities.concretes.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tasks")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","team","employees"})
public class Task {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long taskId;
	
	@Column(name="name")
	private long taskName;
	
	@Column(name="time")
	private Date time;
	
	@OneToMany(mappedBy="task")
	private List<Team> team;
	
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
