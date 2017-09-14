package za.co.momentum.tabletennis.resources.representations;

import java.util.ArrayList;
import java.util.Collection;

import za.co.momentum.tabletennis.models.DoubleGame;
import za.co.momentum.tabletennis.models.Player;

public class DoubleGameResultReadRepresentation implements GameResultRepresentation {

	private String firstPairFirstPlayerFullName;
	private String firstPairFirstPlayerImageName;
	private String firstPairSecondPlayerFullName;
	private String firstPairSecondPlayerImageName;
	private String secondPairFirstPlayerFullName;
	private String secondPairFirstPlayerImageName;
	private String secondPairSecondPlayerFullName;
	private String secondPairSecondPlayerImageName;
	private String firstPairTeamName;
	private String secondPairTeamName;
	private Collection<ScoreReadRepresentation> scores = new ArrayList<>();
	private String winnerTeam;
	private String eventDate;

	public DoubleGameResultReadRepresentation() {
		super();
	}

	@Override
	public String toString() {
		return "DoubleGameResultReadRepresentation [firstPairFirstPlayerFullName=" + firstPairFirstPlayerFullName
				+ ", firstPairFirstPlayerImageName=" + firstPairFirstPlayerImageName
				+ ", firstPairSecondPlayerFullName=" + firstPairSecondPlayerFullName
				+ ", firstPairSecondPlayerImageName=" + firstPairSecondPlayerImageName
				+ ", secondPairFirstPlayerFullName=" + secondPairFirstPlayerFullName
				+ ", secondPairFirstPlayerImageName=" + secondPairFirstPlayerImageName
				+ ", secondPairSecondPlayerFullName=" + secondPairSecondPlayerFullName
				+ ", secondPairSecondPlayerImageName=" + secondPairSecondPlayerImageName + ", firstPairTeamName="
				+ firstPairTeamName + ", secondPairTeamName=" + secondPairTeamName + ", scores=" + scores
				+ ", winnerTeam=" + winnerTeam + ", eventDate=" + eventDate + "]";
	}

	public static Collection<DoubleGameResultReadRepresentation> getCollection(Collection<DoubleGame> doubles) {

		Collection<DoubleGameResultReadRepresentation> representations = new ArrayList<>();

		for (DoubleGame game : doubles) {

			DoubleGameResultReadRepresentation rep = findExisting(representations, game);
			if (rep == null) {
				rep = new DoubleGameResultReadRepresentation();
				rep.setEventDate(game.getEventDate());

				Player firstPairFirstPlayer = game.getFirstPairFirstPlayer();
				rep.firstPairFirstPlayerFullName = firstPairFirstPlayer.getFirstName() + " "
						+ firstPairFirstPlayer.getLastName();
				rep.firstPairFirstPlayerImageName = firstPairFirstPlayer.getFirstName().toLowerCase() + "_"
						+ firstPairFirstPlayer.getLastName().replaceAll(" ", "").toLowerCase() + ".JPG";

				Player firstPairSecondPlayer = game.getFirstPairSecondPlayer();
				rep.firstPairSecondPlayerFullName = firstPairSecondPlayer.getFirstName() + " "
						+ firstPairSecondPlayer.getLastName();
				rep.firstPairSecondPlayerImageName = firstPairSecondPlayer.getFirstName().toLowerCase() + "_"
						+ firstPairSecondPlayer.getLastName().replaceAll(" ", "").toLowerCase() + ".JPG";

				Player secondPairFirstPlayer = game.getSecondPairFirstPlayer();
				rep.secondPairFirstPlayerFullName = secondPairFirstPlayer.getFirstName() + " "
						+ secondPairFirstPlayer.getLastName();
				rep.secondPairFirstPlayerImageName = secondPairFirstPlayer.getFirstName().toLowerCase() + "_"
						+ secondPairFirstPlayer.getLastName().replaceAll(" ", "").toLowerCase() + ".JPG";

				Player secondPairSecondPlayer = game.getSecondPairSecondPlayer();
				rep.secondPairSecondPlayerFullName = secondPairSecondPlayer.getFirstName() + " "
						+ secondPairSecondPlayer.getLastName();
				rep.secondPairSecondPlayerImageName = secondPairSecondPlayer.getFirstName().toLowerCase() + "_"
						+ secondPairSecondPlayer.getLastName().replaceAll(" ", "").toLowerCase() + ".JPG";

				rep.firstPairTeamName = firstPairFirstPlayer.getTeam().getName();
				rep.secondPairTeamName = secondPairFirstPlayer.getTeam().getName();

				rep.scores.add(
						new ScoreReadRepresentation(game.getFirstPairTotalPoints(), game.getSecondPairTotalPoints()));
				representations.add(rep);
			} else {
				rep.scores.add(
						new ScoreReadRepresentation(game.getFirstPairTotalPoints(), game.getSecondPairTotalPoints()));
			}
		}

		for (DoubleGameResultReadRepresentation DoubleGameResultReadRepresentation : representations) {
			int missing = 5 - DoubleGameResultReadRepresentation.scores.size();
			for (int i = 0; i < missing; i++) {
				DoubleGameResultReadRepresentation.scores.add(new ScoreReadRepresentation());
			}
		}

		updateWinnerName(representations);
		return representations;

	}

	private static DoubleGameResultReadRepresentation findExisting(
			Collection<DoubleGameResultReadRepresentation> representations, DoubleGame game) {

		for (DoubleGameResultReadRepresentation rep : representations) {
			Player firstPairFirstPlayer = game.getFirstPairFirstPlayer();
			Player firstPairSecondPlayer = game.getFirstPairSecondPlayer();
			Player secondPairFirstPlayer = game.getSecondPairFirstPlayer();
			Player secondPairSecondPlayer = game.getSecondPairSecondPlayer();
			String eventDate = game.getEventDate();

			if (rep.getFirstPairFirstPlayerFullName()
					.equals(firstPairFirstPlayer.getFirstName() + " " + firstPairFirstPlayer.getLastName())
					&& rep.getFirstPairSecondPlayerFullName()
							.equals(firstPairSecondPlayer.getFirstName() + " " + firstPairSecondPlayer.getLastName())
					&& rep.getSecondPairFirstPlayerFullName()
							.equals(secondPairFirstPlayer.getFirstName() + " " + secondPairFirstPlayer.getLastName())
					&& rep.getSecondPairSecondPlayerFullName()
							.equals(secondPairSecondPlayer.getFirstName() + " " + secondPairSecondPlayer.getLastName())
					&& rep.getEventDate().equals(eventDate)) {
				return rep;
			}
		}

		return null;
	}

	private static void updateWinnerName(Collection<DoubleGameResultReadRepresentation> reps) {

		for (DoubleGameResultReadRepresentation rep : reps) {
			int firstPairVictories = 0;
			int secondPairVictories = 0;
			Collection<ScoreReadRepresentation> scoreCollection = rep.getScores();

			for (ScoreReadRepresentation score : scoreCollection) {
				int firstPlayerPoints = score.getFirstPlayerPoints();
				int secondPlayerPoints = score.getSecondPlayerPoints();
				if (firstPlayerPoints > secondPlayerPoints) {
					firstPairVictories = firstPairVictories + 1;
				} else if (firstPlayerPoints < secondPlayerPoints) {
					secondPairVictories = secondPairVictories + 1;
				}
			}
			String winner = "";
			if (firstPairVictories > secondPairVictories) {
				winner = rep.getFirstPairTeamName();
			} else if (firstPairVictories < secondPairVictories) {
				winner = rep.getSecondPairTeamName();
			}
			rep.setWinnerTeam(winner);
		}

	}

	public String getFirstPairFirstPlayerFullName() {
		return firstPairFirstPlayerFullName;
	}

	public void setFirstPairFirstPlayerFullName(String firstPairFirstPlayerFullName) {
		this.firstPairFirstPlayerFullName = firstPairFirstPlayerFullName;
	}

	public String getFirstPairFirstPlayerImageName() {
		return firstPairFirstPlayerImageName;
	}

	public void setFirstPairFirstPlayerImageName(String firstPairFirstPlayerImageName) {
		this.firstPairFirstPlayerImageName = firstPairFirstPlayerImageName;
	}

	public String getFirstPairSecondPlayerFullName() {
		return firstPairSecondPlayerFullName;
	}

	public void setFirstPairSecondPlayerFullName(String firstPairSecondPlayerFullName) {
		this.firstPairSecondPlayerFullName = firstPairSecondPlayerFullName;
	}

	public String getFirstPairSecondPlayerImageName() {
		return firstPairSecondPlayerImageName;
	}

	public void setFirstPairSecondPlayerImageName(String firstPairSecondPlayerImageName) {
		this.firstPairSecondPlayerImageName = firstPairSecondPlayerImageName;
	}

	public String getSecondPairFirstPlayerFullName() {
		return secondPairFirstPlayerFullName;
	}

	public void setSecondPairFirstPlayerFullName(String secondPairFirstPlayerFullName) {
		this.secondPairFirstPlayerFullName = secondPairFirstPlayerFullName;
	}

	public String getSecondPairFirstPlayerImageName() {
		return secondPairFirstPlayerImageName;
	}

	public void setSecondPairFirstPlayerImageName(String secondPairFirstPlayerImageName) {
		this.secondPairFirstPlayerImageName = secondPairFirstPlayerImageName;
	}

	public String getSecondPairSecondPlayerFullName() {
		return secondPairSecondPlayerFullName;
	}

	public void setSecondPairSecondPlayerFullName(String secondPairSecondPlayerFullName) {
		this.secondPairSecondPlayerFullName = secondPairSecondPlayerFullName;
	}

	public String getSecondPairSecondPlayerImageName() {
		return secondPairSecondPlayerImageName;
	}

	public void setSecondPairSecondPlayerImageName(String secondPairSecondPlayerImageName) {
		this.secondPairSecondPlayerImageName = secondPairSecondPlayerImageName;
	}

	public String getFirstPairTeamName() {
		return firstPairTeamName;
	}

	public void setFirstPairTeamName(String firstPairTeamName) {
		this.firstPairTeamName = firstPairTeamName;
	}

	public String getSecondPairTeamName() {
		return secondPairTeamName;
	}

	public void setSecondPairTeamName(String secondPairTeamName) {
		this.secondPairTeamName = secondPairTeamName;
	}

	public Collection<ScoreReadRepresentation> getScores() {
		return scores;
	}

	public void setScores(Collection<ScoreReadRepresentation> scores) {
		this.scores = scores;
	}

	public String getWinnerTeam() {
		return winnerTeam;
	}

	public void setWinnerTeam(String winnerTeam) {
		this.winnerTeam = winnerTeam;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

}
