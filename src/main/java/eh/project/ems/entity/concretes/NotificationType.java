package eh.project.ems.entity.concretes;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="notification_types")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","notifications"})
public class NotificationType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long notificationTypeId;
	
	@Column(name="name")
	private String noficationTypeName;

	@Column(name="ending_date")
	private Date endingDate;
	
	@OneToMany(mappedBy = "type")
	private List<Notification> notifications;
}
