package za.co.momentum.tabletennis.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "UPDATE_AUTHORITY", schema = "MOMENTUM_TOURNAMENT")
public class AuthData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "EVENT_DATE")
	private String eventDate;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Player captain;
	
	@Column(name = "GUID")
	private String guid;
	
	@Override
	public String toString() {
		return "AuthData [id=" + id + ", eventDate=" + eventDate + ", captain=" + captain + ", guid=" + guid + "]";
	}
	
	public AuthData() {
		super();
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
	
	public Player getCaptain() {
		return captain;
	}
	
	public void setCaptain(Player captain) {
		this.captain = captain;
	}
	
	public String getGuid() {
		return guid;
	}
	
	public void setGuid(String guid) {
		this.guid = guid;
	}
	
}
