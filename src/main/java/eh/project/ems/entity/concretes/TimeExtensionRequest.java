package eh.project.ems.entity.concretes;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="time_extension_request")
public class TimeExtensionRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long timeExtensionRequestId;
	
	@Column(name="extension_amount")
	private int extensionAmount;
	
	@Column(name="status")
	private boolean status;
	
	@ManyToOne
	@JoinColumn(name="project_manager_id")
	private ProjectManager projectManager;
}
