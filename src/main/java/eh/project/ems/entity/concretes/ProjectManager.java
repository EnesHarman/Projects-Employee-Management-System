package eh.project.ems.entity.concretes;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
@Table(name="project_managers")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","projects","timeExtensionRequests"})
public class ProjectManager {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long projectManagerId;
	
	@Column(name="manager_since")
	private Date managerSince = new Date();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@OneToMany(mappedBy = "projectManager")
	private List<TimeExtensionRequest> timeExtensionRequests;
	
	@ManyToMany()
	@JoinTable(
			name="managers_of_projects",
			joinColumns = @JoinColumn(name="project_manager_id"),
			inverseJoinColumns = @JoinColumn(name="project_id")
			)
	private List<Project> projects;
}
