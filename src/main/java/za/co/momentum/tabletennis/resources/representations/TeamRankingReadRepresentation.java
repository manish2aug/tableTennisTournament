package za.co.momentum.tabletennis.resources.representations;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import za.co.momentum.tabletennis.models.DoubleGame;
import za.co.momentum.tabletennis.models.SingleGame;

public class TeamRankingReadRepresentation {

	public TempTeam[] getOrderedCollection(Collection<SingleGame> singles, Collection<DoubleGame> doubles) {
		Collection<SingleGameResultReadRepresentation> singleGames = SingleGameResultReadRepresentation
				.getCollection(singles);
		Collection<DoubleGameResultReadRepresentation> doubleGames = DoubleGameResultReadRepresentation
				.getCollection(doubles);

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

		TempTeam[] teamArray = new TempTeam[] { teamA, teamB, teamC, teamD };
		Arrays.sort(teamArray);
		
		Collections.sort(Arrays.asList(teamArray), new Comparator<TempTeam>() {

			@Override
			public int compare(final TempTeam p1, final TempTeam p2) {
				int diff;
				diff = p2.getPoints() - p1.getPoints();
				if (diff == 0)
					diff = p2.getPointBalance() - p1.getPointBalance();
				return diff;
			}

		});
		
		
		return teamArray;
	}

	private void updatePoints(TempTeam teamA, TempTeam teamB, TempTeam teamC, TempTeam teamD,
			GameResultRepresentation game) {
		if (game.getWinnerTeam().equals("A")) {
			teamA.setPoints(teamA.getPoints() + 2);
			teamA.setPointBalance(teamA.getPointBalance() + game.getWinnerPointBalance());
		} else if (game.getWinnerTeam().equals("B")) {
			teamB.setPoints(teamB.getPoints() + 2);
			teamB.setPointBalance(teamB.getPointBalance() + game.getWinnerPointBalance());
		} else if (game.getWinnerTeam().equals("C")) {
			teamC.setPoints(teamC.getPoints() + 2);
			teamC.setPointBalance(teamC.getPointBalance() + game.getWinnerPointBalance());
		} else if (game.getWinnerTeam().equals("D")) {
			teamD.setPoints(teamD.getPoints() + 2);
			teamD.setPointBalance(teamD.getPointBalance() + game.getWinnerPointBalance());
		}
	}

	class TempTeam implements Comparable<TempTeam> {
		private String name;
		private int points;
		private int pointBalance;

		public TempTeam() {
			super();
		}

		public TempTeam(String name, int points, int pointBalance) {
			super();
			this.name = name;
			this.points = points;
			this.pointBalance = pointBalance;
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

		public int getPointBalance() {
			return pointBalance;
		}

		public void setPointBalance(int pointBalance) {
			this.pointBalance = pointBalance;
		}

		@Override
		public int compareTo(TempTeam o) {
			int comparePoints = ((TempTeam) o).getPoints();
			// descending order
			return comparePoints - this.points;
		}

	}

}
