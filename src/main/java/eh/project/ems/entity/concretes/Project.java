package eh.project.ems.entity.concretes;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="projects")
public class Project {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long projectId;
	
	@Column(name="name", unique = true, columnDefinition = "nvarchar(255)")
	private String projectName;
	
	@Column(name="project_description", columnDefinition = "text")
	private String projectDescription;
	
	@Column(name="ending_date")
	private Date endingDate;
	
	@Column(name="starting_date")
	private Date startingDate;
	
	@OneToMany(mappedBy = "project")
	private List<Team> teams;
	
	@ManyToMany(mappedBy = "projects")
	private List<ProjectManager> projectManagers;
}
