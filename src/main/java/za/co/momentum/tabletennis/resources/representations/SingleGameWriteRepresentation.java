package za.co.momentum.tabletennis.resources.representations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import za.co.momentum.tabletennis.models.Player;
import za.co.momentum.tabletennis.models.SingleGame;

public class SingleGameWriteRepresentation {
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

	public static Collection<SingleGame> getEntityCollection(Collection<SingleGameWriteRepresentation> singles) {
		Collection<SingleGame> collection = new ArrayList<>();
		for (SingleGameWriteRepresentation game : singles) {
			collection.add(game.getEntityObject());
		}
		return collection;
	}
	
	@Override
	public String toString() {
		return "SingleGameWriteRepresentation [firstPlayerId=" + firstPlayerId + ", secondPlayerId=" + secondPlayerId
				+ ", firstPlayerPoints=" + firstPlayerPoints + ", secondPlayerPoints=" + secondPlayerPoints + "]";
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
