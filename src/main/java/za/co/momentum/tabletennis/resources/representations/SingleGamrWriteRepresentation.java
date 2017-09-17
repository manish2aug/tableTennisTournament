package za.co.momentum.tabletennis.resources.representations;

import java.util.Date;

import za.co.momentum.tabletennis.models.Player;
import za.co.momentum.tabletennis.models.SingleGame;

public class SingleGamrWriteRepresentation {
	private int firstPlayerId;
	private int secondPlayerId;
	private int firstPlayerPoints;
	private int secondPlayerPoints;

	public SingleGame getEntityObject() {
		SingleGame game = new SingleGame();
		game.setEventDate(new Date());
		game.setFirstPlayer(new Player(firstPlayerId));
		game.setSecondPlayer(new Player(secondPlayerId));
		game.setFirstPlayerTotalPoints(firstPlayerPoints);
		game.setSecondPlayerTotalPoints(secondPlayerPoints);
		return game;
	}

	public int getFirstPlayerId() {
		return firstPlayerId;
	}

	public void setFirstPlayerId(int firstPlayerId) {
		this.firstPlayerId = firstPlayerId;
	}

	public int getSecondPlayerId() {
		return secondPlayerId;
	}

	public void setSecondPlayerId(int secondPlayerId) {
		this.secondPlayerId = secondPlayerId;
	}

	public int getFirstPlayerPoints() {
		return firstPlayerPoints;
	}

	public void setFirstPlayerPoints(int firstPlayerPoints) {
		this.firstPlayerPoints = firstPlayerPoints;
	}

	public int getSecondPlayerPoints() {
		return secondPlayerPoints;
	}

	public void setSecondPlayerPoints(int secondPlayerPoints) {
		this.secondPlayerPoints = secondPlayerPoints;
	}

}
