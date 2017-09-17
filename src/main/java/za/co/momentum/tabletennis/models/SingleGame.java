package za.co.momentum.tabletennis.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SINGLE_GAME", schema = "MOMENTUM_TOURNAMENT")
public class SingleGame {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "EVENT_DATE")
	private Date eventDate;

	@ManyToOne(optional = false)
	@JoinColumn(name = "firstPlayerId", referencedColumnName = "id")
	private Player firstPlayer;

	@ManyToOne(optional = false)
	@JoinColumn(name = "secondPlayerId", referencedColumnName = "id")
	private Player secondPlayer;

	@Column(name = "FIRST_PLAYER_TOTAL_POINTS")
	private int firstPlayerTotalPoints;

	@Column(name = "SECOND_PLAYER_TOTAL_POINTS")
	private int secondPlayerTotalPoints;

	public SingleGame() {
		super();
	}

	@Override
	public String toString() {
		return "SingleGame [id=" + id + ", eventDate=" + eventDate + ", firstPlayer=" + firstPlayer + ", secondPlayer="
				+ secondPlayer + ", firstPlayerTotalPoints=" + firstPlayerTotalPoints + ", secondPlayerTotalPoints="
				+ secondPlayerTotalPoints + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;// new SimpleDateFormat("yyyy-MM-dd").format(eventDate);
	}

	public Player getFirstPlayer() {
		return firstPlayer;
	}

	public void setFirstPlayer(Player firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	public Player getSecondPlayer() {
		return secondPlayer;
	}

	public void setSecondPlayer(Player secondPlayer) {
		this.secondPlayer = secondPlayer;
	}

	public int getFirstPlayerTotalPoints() {
		return firstPlayerTotalPoints;
	}

	public void setFirstPlayerTotalPoints(int firstPlayerTotalPoints) {
		this.firstPlayerTotalPoints = firstPlayerTotalPoints;
	}

	public int getSecondPlayerTotalPoints() {
		return secondPlayerTotalPoints;
	}

	public void setSecondPlayerTotalPoints(int secondPlayerTotalPoints) {
		this.secondPlayerTotalPoints = secondPlayerTotalPoints;
	}

}
