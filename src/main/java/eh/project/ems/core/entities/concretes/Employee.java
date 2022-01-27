package eh.project.ems.core.entities.concretes;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import eh.project.ems.entity.concretes.Address;
import eh.project.ems.entity.concretes.Departmant;
import eh.project.ems.entity.concretes.ProjectManager;
import eh.project.ems.entity.concretes.Salary;
import eh.project.ems.entity.concretes.Task;
import eh.project.ems.entity.concretes.TeamLeader;
import eh.project.ems.entity.concretes.TeamMember;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employees")
public class Employee {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long employeeId;
	
	@Column(name="email",columnDefinition = "nvarchar(255)",unique = true)
	private String email;
	
	@Column(name="password",columnDefinition = "nvarchar(255)")
	private String password;
	
	@Column(name="name",columnDefinition = "nvarchar(255)")
	private String name;
	
	@Column(name="last_name",columnDefinition = "nvarchar(255)")
	private String lastName;
	
	@Column(name="phone_number",columnDefinition = "varchar(25)",unique = true)
	private String phoneNumber;
	
	@Column(name="identity_number",columnDefinition = "varchar(20)",unique = true)
	private String identityNumber;
	
	@Column(name="join_at")
	private Date joinAt = new Date();
	
	@Column(name="profile_info")
	private String profileInfo;
	
	@ManyToOne()
	@JoinColumn(name="departmant_id")
	private Departmant departmant;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="salary_id")
	private Salary salary;
	
	@OneToOne(mappedBy = "employee")
	private ProjectManager projectManager;
	
	@OneToOne(mappedBy = "employee")
	private TeamMember teamMember;
	
	@OneToOne(mappedBy = "employee")
	private TeamLeader teamLeader;
	
	@ManyToMany(mappedBy = "employees")
	private List<Claim> claims;
	
	@ManyToMany(mappedBy = "employees")
	private List<Task> tasks;
	
	@OneToMany(mappedBy = "employee")
	private List< Address> addresses;
}
