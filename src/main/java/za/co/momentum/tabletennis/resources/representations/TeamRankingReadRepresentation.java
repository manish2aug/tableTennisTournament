package za.co.momentum.tabletennis.resources.representations;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import za.co.momentum.tabletennis.models.DoubleGame;
import za.co.momentum.tabletennis.models.SingleGame;

public class TeamRankingReadRepresentation {

	public List<TempTeam> getOrderedCollection(Collection<SingleGame> singles, Collection<DoubleGame> doubles) {
		Collection<SingleGameResultReadRepresentation> singleGames = SingleGameResultReadRepresentation.getCollection(singles);
		Collection<DoubleGameResultReadRepresentation> doubleGames = DoubleGameResultReadRepresentation.getCollection(doubles);

		TempTeam teamA = new TempTeam("A", 0, 0);
		TempTeam teamB = new TempTeam("B", 0, 0);
		TempTeam teamC = new TempTeam("C", 0, 0);
		TempTeam teamD = new TempTeam("D", 0, 0);

		for (SingleGameResultReadRepresentation game : singleGames) {
			updatePoints(teamA, teamB, teamC, teamD, game);
		}

		for (DoubleGameResultReadRepresentation game : doubleGames) {
			updatePoints(teamA, teamB, teamC, teamD, game);
		}

		List<TempTeam> teams = new ArrayList<>();
		teams.add(teamA);
		teams.add(teamB);
		teams.add(teamC);
		teams.add(teamD);

		Collections.sort(teams, new Comparator<TempTeam>() {

			@Override
			public int compare(final TempTeam p1, final TempTeam p2) {
				int diff;
				diff = p2.getPoints() - p1.getPoints();
				if (diff == 0)
					diff = Double.compare(p2.getPointBalance(), p1.getPointBalance());
				return diff;
			}

		});

		return teams;
	}

	private void updatePoints(TempTeam teamA, TempTeam teamB, TempTeam teamC, TempTeam teamD, GameResultRepresentation game) {

		if ("A".equals(game.getWinnerTeam()) || "A".equals(game.getLooserTeam())) {
			updateNoOfGames(teamA, game);
		}
		if ("B".equals(game.getWinnerTeam()) || "B".equals(game.getLooserTeam())) {
			updateNoOfGames(teamB, game);
		}
		if ("C".equals(game.getWinnerTeam()) || "C".equals(game.getLooserTeam())) {
			updateNoOfGames(teamC, game);
		}
		if ("D".equals(game.getWinnerTeam()) || "D".equals(game.getLooserTeam())) {
			updateNoOfGames(teamD, game);
		}
		if (game.getWinnerTeam().equals("A")) {
			updateVictoryAndPoints(teamA, game);
		} else if (game.getWinnerTeam().equals("B")) {
			updateVictoryAndPoints(teamB, game);
		} else if (game.getWinnerTeam().equals("C")) {
			updateVictoryAndPoints(teamC, game);
		} else if (game.getWinnerTeam().equals("D")) {
			updateVictoryAndPoints(teamD, game);
		}
	}

	private void updateVictoryAndPoints(TempTeam team, GameResultRepresentation game) {
		team.setPoints(team.getPoints() + 2);
		team.setPointBalance(team.getPointBalance() + game.getWinnerPointBalance());
		if (game instanceof SingleGameResultReadRepresentation) {
			team.setNoOfSingleGamesVictory(team.getNoOfSingleGamesVictory() + 1);
		} else {
			team.setNoOfDoubleGamesVictory(team.getNoOfDoubleGamesVictory() + 1);
		}
	}

	private void updateNoOfGames(TempTeam team, GameResultRepresentation game) {
		team.setNoOfGames(team.getNoOfGames() + 1);
		if (game instanceof SingleGameResultReadRepresentation) {
			team.setNoOfSingleGames(team.getNoOfSingleGames() + 1);
		} else {
			team.setNoOfDoubleGames(team.getNoOfDoubleGames() + 1);
		}
	}

	class TempTeam {
		private String name;
		private int points;
		@JsonIgnore
		private double pointBalance;
		private int noOfGames;
		private int noOfSingleGames;
		private int noOfDoubleGames;
		private int noOfSingleGamesVictory;
		private int noOfDoubleGamesVictory;
		private String displayPoints;
		DecimalFormat df = new DecimalFormat("#.###");

		public TempTeam() {
			super();
			df.setRoundingMode(RoundingMode.CEILING);
		}

		@Override
		public String toString() {
			return "TempTeam [name=" + name + ", points=" + points + ", pointBalance=" + pointBalance + ", noOfGames=" + noOfGames + "]";
		}

		public TempTeam(String name, int points, int pointBalance) {
			super();
			this.name = name;
			this.points = points;
			this.pointBalance = pointBalance;
		}

		public String getDisplayPoints() {
			return df.format(pointBalance / (double) noOfGames);
		}

		public void setDisplayPoints(String displayPoints) {
			this.displayPoints = displayPoints;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getPoints() {
			return points;
		}

		public void setPoints(int points) {
			this.points = points;
		}

		public double getPointBalance() {
			return pointBalance;
		}

		public void setPointBalance(double pointBalance) {
			this.pointBalance = pointBalance;
		}

		public int getNoOfGames() {
			return noOfGames;
		}

		public void setNoOfGames(int noOfGames) {
			this.noOfGames = noOfGames;
		}

		public int getNoOfSingleGames() {
			return noOfSingleGames;
		}

		public void setNoOfSingleGames(int noOfSingleGames) {
			this.noOfSingleGames = noOfSingleGames;
		}

		public int getNoOfDoubleGames() {
			return noOfDoubleGames;
		}

		public void setNoOfDoubleGames(int noOfDoubleGames) {
			this.noOfDoubleGames = noOfDoubleGames;
		}

		public int getNoOfSingleGamesVictory() {
			return noOfSingleGamesVictory;
		}

		public void setNoOfSingleGamesVictory(int noOfSingleGamesVictory) {
			this.noOfSingleGamesVictory = noOfSingleGamesVictory;
		}

		public int getNoOfDoubleGamesVictory() {
			return noOfDoubleGamesVictory;
		}

		public void setNoOfDoubleGamesVictory(int noOfDoubleGamesVictory) {
			this.noOfDoubleGamesVictory = noOfDoubleGamesVictory;
		}

	}

}
