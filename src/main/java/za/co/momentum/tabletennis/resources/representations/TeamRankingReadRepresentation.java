package za.co.momentum.tabletennis.resources.representations;

import java.util.Arrays;
import java.util.Collection;

import za.co.momentum.tabletennis.models.DoubleGame;
import za.co.momentum.tabletennis.models.SingleGame;

public class TeamRankingReadRepresentation {

	public TempTeam[] getOrderedCollection(Collection<SingleGame> singles, Collection<DoubleGame> doubles) {
		Collection<SingleGameResultReadRepresentation> singleGames = SingleGameResultReadRepresentation
				.getCollection(singles);
		Collection<DoubleGameResultReadRepresentation> doubleGames = DoubleGameResultReadRepresentation
				.getCollection(doubles);

		TempTeam teamA = new TempTeam("A", 0);
		TempTeam teamB = new TempTeam("B", 0);
		TempTeam teamC = new TempTeam("C", 0);
		TempTeam teamD = new TempTeam("D", 0);

		for (SingleGameResultReadRepresentation game : singleGames) {
			updatePoints(teamA, teamB, teamC, teamD, game);
		}

		for (DoubleGameResultReadRepresentation game : doubleGames) {
			updatePoints(teamA, teamB, teamC, teamD, game);
		}

		TempTeam[] teamArray = new TempTeam[] { teamA, teamB, teamC, teamD };
		Arrays.sort(teamArray);
		return teamArray;
	}

	private void updatePoints(TempTeam teamA, TempTeam teamB, TempTeam teamC, TempTeam teamD,
			GameResultRepresentation game) {
		if (game.getWinnerTeam().equals("A")) {
			teamA.setPoints(teamA.getPoints() + 2);
		} else if (game.getWinnerTeam().equals("B")) {
			teamB.setPoints(teamB.getPoints() + 2);
		} else if (game.getWinnerTeam().equals("C")) {
			teamC.setPoints(teamC.getPoints() + 2);
		} else if (game.getWinnerTeam().equals("D")) {
			teamD.setPoints(teamD.getPoints() + 2);
		}
	}

	class TempTeam implements Comparable<TempTeam> {
		private String name;
		private int points;

		public TempTeam() {
			super();
		}

		public TempTeam(String name, int points) {
			super();
			this.name = name;
			this.points = points;
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

		@Override
		public int compareTo(TempTeam o) {
			int comparePoints = ((TempTeam) o).getPoints();
			// descending order
			return comparePoints - this.points;
		}

	}

}
