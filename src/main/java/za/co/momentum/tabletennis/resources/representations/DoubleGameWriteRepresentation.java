package za.co.momentum.tabletennis.resources.representations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import za.co.momentum.tabletennis.models.DoubleGame;
import za.co.momentum.tabletennis.models.Player;

public class DoubleGameWriteRepresentation {
	private int team1Player1Id;
	private int team1Player2Id;
	private int team2Player1Id;
	private int team2Player2Id;
	private int team1Points;
	private int team2Points;

	public DoubleGame getEntityObject() {
		DoubleGame game = new DoubleGame();
		game.setEventDate(new Date());
		game.setFirstPairFirstPlayer(new Player(team1Player1Id));
		game.setFirstPairSecondPlayer(new Player(team1Player2Id));
		game.setSecondPairFirstPlayer(new Player(team2Player1Id));
		game.setSecondPairSecondPlayer(new Player(team2Player2Id));
		game.setFirstPairTotalPoints(team1Points);
		game.setSecondPairTotalPoints(team2Points);
		return game;
	}

	public static Collection<DoubleGame> getEntityCollection(Collection<DoubleGameWriteRepresentation> doubles) {
		Collection<DoubleGame> collection = new ArrayList<>();
		for (DoubleGameWriteRepresentation game : doubles) {
			collection.add(game.getEntityObject());
		}
		return collection;
	}

	public int getTeam1Player1Id() {
		return team1Player1Id;
	}

	public void setTeam1Player1Id(int team1Player1Id) {
		this.team1Player1Id = team1Player1Id;
	}

	public int getTeam1Player2Id() {
		return team1Player2Id;
	}

	public void setTeam1Player2Id(int team1Player2Id) {
		this.team1Player2Id = team1Player2Id;
	}

	public int getTeam2Player1Id() {
		return team2Player1Id;
	}

	public void setTeam2Player1Id(int team2Player1Id) {
		this.team2Player1Id = team2Player1Id;
	}

	public int getTeam2Player2Id() {
		return team2Player2Id;
	}

	public void setTeam2Player2Id(int team2Player2Id) {
		this.team2Player2Id = team2Player2Id;
	}

	public int getTeam1Points() {
		return team1Points;
	}

	public void setTeam1Points(int team1Points) {
		this.team1Points = team1Points;
	}

	public int getTeam2Points() {
		return team2Points;
	}

	public void setTeam2Points(int team2Points) {
		this.team2Points = team2Points;
	}

}
