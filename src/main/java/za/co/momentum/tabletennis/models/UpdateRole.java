package za.co.momentum.tabletennis.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "UPDATE_ROLE", schema = "MOMENTUM_TOURNAMENT")
public class UpdateRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "EVENT_DATE")
	private String eventDate;

	@ManyToOne(optional = false)
	@JoinColumn(name = "captainId", referencedColumnName = "id")
	private Player captainId;

	public UpdateRole() {
		super();
	}

	@Override
	public String toString() {
		return "UpdateRole [id=" + id + ", eventDate=" + eventDate + ", captainId=" + captainId + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public Player getCaptainId() {
		return captainId;
	}

	public void setCaptainId(Player captainId) {
		this.captainId = captainId;
	}

}
