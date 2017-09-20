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
@Table(name = "DOUBLE_GAME", schema = "MOMENTUM_TOURNAMENT")
public class DoubleGame {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "EVENT_DATE")
	private Date eventDate;

	@ManyToOne(optional = false)
	@JoinColumn(name = "firstPairFirstPlayerId", referencedColumnName = "id")
	private Player firstPairFirstPlayer;

	@ManyToOne(optional = false)
	@JoinColumn(name = "firstPairSecondPlayerId", referencedColumnName = "id")
	private Player firstPairSecondPlayer;

	@ManyToOne(optional = false)
	@JoinColumn(name = "secondPairFirstPlayerId", referencedColumnName = "id")
	private Player secondPairFirstPlayer;

	@ManyToOne(optional = false)
	@JoinColumn(name = "secondPairSecondPlayerId", referencedColumnName = "id")
	private Player secondPairSecondPlayer;

	@Column(name = "FIRST_PAIR_TOTAL_POINTS")
	private int firstPairTotalPoints;

	@Column(name = "SECOND_PAIR_TOTAL_POINTS")
	private int secondPairTotalPoints;

	public DoubleGame() {
		super();
	}

	@Override
	public String toString() {
		return "DoubleGame [id=" + id + ", eventDate=" + eventDate + ", firstPairFirstPlayer=" + firstPairFirstPlayer
				+ ", firstPairSecondPlayer=" + firstPairSecondPlayer + ", secondPairFirstPlayer="
				+ secondPairFirstPlayer + ", secondPairSecondPlayer=" + secondPairSecondPlayer
				+ ", firstPairTotalPoints=" + firstPairTotalPoints + ", secondPairTotalPoints=" + secondPairTotalPoints
				+ "]";
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
		this.eventDate = eventDate;
	}

	public Player getFirstPairFirstPlayer() {
		return firstPairFirstPlayer;
	}

	public void setFirstPairFirstPlayer(Player firstPairFirstPlayer) {
		this.firstPairFirstPlayer = firstPairFirstPlayer;
	}

	public Player getFirstPairSecondPlayer() {
		return firstPairSecondPlayer;
	}

	public void setFirstPairSecondPlayer(Player firstPairSecondPlayer) {
		this.firstPairSecondPlayer = firstPairSecondPlayer;
	}

	public Player getSecondPairFirstPlayer() {
		return secondPairFirstPlayer;
	}

	public void setSecondPairFirstPlayer(Player secondPairFirstPlayer) {
		this.secondPairFirstPlayer = secondPairFirstPlayer;
	}

	public Player getSecondPairSecondPlayer() {
		return secondPairSecondPlayer;
	}

	public void setSecondPairSecondPlayer(Player secondPairSecondPlayer) {
		this.secondPairSecondPlayer = secondPairSecondPlayer;
	}

	public int getFirstPairTotalPoints() {
		return firstPairTotalPoints;
	}

	public void setFirstPairTotalPoints(int firstPairTotalPoints) {
		this.firstPairTotalPoints = firstPairTotalPoints;
	}

	public int getSecondPairTotalPoints() {
		return secondPairTotalPoints;
	}

	public void setSecondPairTotalPoints(int secondPairTotalPoints) {
		this.secondPairTotalPoints = secondPairTotalPoints;
	}

}
