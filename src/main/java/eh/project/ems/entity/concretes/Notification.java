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
@Table(name="notifications")
public class Notification {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long notificationId;
	
	@Column(name="content",columnDefinition = "text")
	private String content;
	
	@Column(name="sending_date")
	private Date sendingDate;
	
	@ManyToOne()
	@JoinColumn(name="type_id")
	private NotificationType type;
	
	@ManyToOne()
	@JoinColumn(name="employee_id")
	private Employee employee;
}
