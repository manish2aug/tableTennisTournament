package za.co.momentum.tabletennis.resources.representations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import za.co.momentum.tabletennis.models.SingleGame;

public class IndividualRankingReadRepresentation {

	public List<Participant> getOrderedCollection(Collection<SingleGame> singles) {
		Collection<SingleGameResultReadRepresentation> singleGames = SingleGameResultReadRepresentation
				.getCollection(singles);

		List<Participant> participants = new ArrayList<>();
		for (SingleGameResultReadRepresentation game : singleGames) {
			participants.add(getParticipant(participants, game));
		}

		Collections.sort(participants, new Comparator<Participant>() {

			@Override
			public int compare(final Participant p1, final Participant p2) {
				int diff;
				diff = p2.getVictories() - p1.getVictories();
				if (diff == 0)
					diff = p2.getPoints() - p1.getPoints();
				return diff;
			}

		});
		return participants;
	}

	private Participant getParticipant(Collection<Participant> participants, SingleGameResultReadRepresentation game) {

		String gameWinner = game.getWinnerName();
		Participant participant = findExisting(participants, gameWinner);
		boolean isFirstPlayerWon = (game.getFirstPlayerFullName().equals(game.getWinnerName())) ? true : false;
		int winnerAccumulatedPoints = 0;
		Collection<ScoreReadRepresentation> scores = game.getScores();
		for (ScoreReadRepresentation score : scores) {
			if (isFirstPlayerWon) {
				winnerAccumulatedPoints = winnerAccumulatedPoints + score.getFirstPlayerPoints()
						- score.getSecondPlayerPoints();
			} else {
				winnerAccumulatedPoints = winnerAccumulatedPoints + score.getSecondPlayerPoints()
						- score.getFirstPlayerPoints();
			}
		}
		String wonAgainst = (isFirstPlayerWon) ? game.getSecondPlayerFullName() : game.getFirstPlayerFullName();

		if (participant == null) {
			return new Participant(gameWinner, winnerAccumulatedPoints, 1, wonAgainst);
		}
		int victories = participant.getVictories();
		victories = victories + 1;
		return participant;

	}

	private Participant findExisting(Collection<Participant> participants, String gameWinner) {
		for (Participant participant : participants) {
			if (participant.getName().equals(gameWinner)) {
				return participant;
			}
		}
		return null;
	}

	class Participant {
		private String name;
		private int points;
		private int victories; // set victory
		private List<String> wonAgainst = new ArrayList<>();

		@Override
		public String toString() {
			return "Participant [name=" + name + ", points=" + points + ", victories=" + victories + ", wonAgainst="
					+ wonAgainst + "]";
		}

		public Participant() {
			super();
		}

		public Participant(String name, int points, int victories, String wonAgainst) {
			super();
			this.name = name;
			this.points = points;
			this.victories = victories;
			this.wonAgainst.add(wonAgainst);
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

		public int getVictories() {
			return victories;
		}

		public void setVictories(int victories) {
			this.victories = victories;
		}

		public List<String> getWonAgainst() {
			return wonAgainst;
		}

		public void setWonAgainst(List<String> wonAgainst) {
			this.wonAgainst = wonAgainst;
		}

		// @Override
		// public int compareTo(Participant o) {
		// int comparePoints = ((Participant) o).getVictories();
		// // descending order
		// return comparePoints - this.victories;
		// }

	}

}
