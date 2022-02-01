package eh.project.ems.entity.concretes;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="teams")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","teamMembers"})
public class Team {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long teamId;
	
	@Column(name="team_name")
	private String teamName;
	
	@ManyToOne()
	@JoinColumn(name="project_id")
	private Project project;
	
	@OneToOne(mappedBy = "team")
	private TeamLeader teamLeader;
	
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	private List<TeamMember> teamMembers;
	
	@ManyToOne()
	@JoinColumn(name="task_id")
	private Task task;
	
	public Team(long id) {
		this.teamId = id;
	}
}
