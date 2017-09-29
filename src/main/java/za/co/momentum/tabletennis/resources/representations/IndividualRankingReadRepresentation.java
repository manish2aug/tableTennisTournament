package za.co.momentum.tabletennis.resources.representations;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import za.co.momentum.tabletennis.models.SingleGame;

public class IndividualRankingReadRepresentation {

	public List<Participant> getOrderedCollection(Collection<SingleGame> singles) {
		Collection<SingleGameResultReadRepresentation> singleGames = SingleGameResultReadRepresentation
				.getCollection(singles);

		List<Participant> participants = new ArrayList<>();
		for (SingleGameResultReadRepresentation game : singleGames) {
			getParticipant(participants, game);
		}

		calculateParticipantPoints(participants, singleGames);
		
		Collections.sort(participants, new Comparator<Participant>() {

			@Override
			public int compare(final Participant p1, final Participant p2) {
				int diff;
				diff = p2.getVictories() - p1.getVictories();
				if (diff == 0)
					diff = Double.compare(p2.getPoints(), p1.getPoints());
				return diff;
			}

		});
		return participants;
	}

	private void calculateParticipantPoints(Collection<Participant> participants, Collection<SingleGameResultReadRepresentation> games) {

		for (Participant p : participants) {
			int noOfGames = 0;
			String name = p.getName();
			int points = 0;

			for (SingleGameResultReadRepresentation g : games) {
				boolean isParticipantFirstPlayer = g.getFirstPlayerFullName().equals(name);

				if (isParticipantFirstPlayer || g.getSecondPlayerFullName().equals(name)) {
					Collection<ScoreReadRepresentation> scores = g.getScores();
					noOfGames = noOfGames + getNoOfGames(scores);

					for (ScoreReadRepresentation s : scores) {
						if (isParticipantFirstPlayer) {
							points = points + s.getFirstPlayerPoints() - s.getSecondPlayerPoints();
						} else {
							points = points + s.getSecondPlayerPoints() - s.getFirstPlayerPoints();
						}
					}
				}
			}
			
			p.setPoints(points/(double)noOfGames);
		}
	}

	private int getNoOfGames(Collection<ScoreReadRepresentation> scores) {
		int size = 0;
		for (ScoreReadRepresentation s : scores) {
			if(s.getFirstPlayerPoints() != 0 && s.getSecondPlayerPoints() != 0) {
				size = size + 1;
			}
		}
		return size;
	}

	private void getParticipant(Collection<Participant> participants, SingleGameResultReadRepresentation game) {
		String gameWinner = game.getWinnerName();
		boolean isFirstPlayerWon = (game.getFirstPlayerFullName().equals(game.getWinnerName())) ? true : false;
		String wonAgainst = (isFirstPlayerWon) ? game.getSecondPlayerFullName() : game.getFirstPlayerFullName();
		Participant participant = findExisting(participants, gameWinner);

		if (participant == null) {
			participants.add(
					new Participant(gameWinner, getPointBalance(isFirstPlayerWon, game.getScores()), 1, wonAgainst));
		} else {
			participant.setVictories(participant.getVictories() + 1);
			participant.setPoints(participant.getPoints() + getPointBalance(isFirstPlayerWon, game.getScores()));
			List<String> wonAgainstCollection = participant.getWonAgainst();
			if (!wonAgainstCollection.contains(wonAgainst)) {
				wonAgainstCollection.add(wonAgainst);
			}
		}
	}

	private int getPointBalance(boolean isFirstPlayerWon, Collection<ScoreReadRepresentation> scores) {
		int winnerAccumulatedPoints = 0;
		for (ScoreReadRepresentation score : scores) {
			if (isFirstPlayerWon) {
				winnerAccumulatedPoints = winnerAccumulatedPoints + score.getFirstPlayerPoints() - score.getSecondPlayerPoints();
			} else {
				winnerAccumulatedPoints = winnerAccumulatedPoints + score.getSecondPlayerPoints() - score.getFirstPlayerPoints();
			}
		}
		return winnerAccumulatedPoints;
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
		@JsonIgnore
		private double points;
		private String displayPoints;
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

		public Participant(String name, double points, int victories, String wonAgainst) {
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

		public double getPoints() {
			return points;
		}

		public void setPoints(double points) {
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

		public String getDisplayPoints() {
			DecimalFormat df = new DecimalFormat("#.###");
			df.setRoundingMode(RoundingMode.CEILING);
			return df.format(this.points);
		}

		public void setDisplayPoints(String displayPoints) {
			this.displayPoints = displayPoints;
		}

	}

}
