package za.co.momentum.tabletennis.resources.representations;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import za.co.momentum.tabletennis.models.Player;
import za.co.momentum.tabletennis.models.SingleGame;

public class SingleGameResultReadRepresentation implements GameResultRepresentation {

	private String firstPlayerFullName;
	private String firstPlayerImageName;
	private String firstPlayerTeamName;
	private String secondPlayerFullName;
	private String secondPlayerImageName;
	private String secondPlayerTeamName;
	private Collection<ScoreReadRepresentation> scores = new ArrayList<>();
	private String winnerName;
	private String eventDate;
	private String winnerTeam;

	public SingleGameResultReadRepresentation() {
		super();
	}

	private static void updateWinnerName(Collection<SingleGameResultReadRepresentation> reps) {
		for (SingleGameResultReadRepresentation rep : reps) {
			int firstPlayerVictories = 0;
			int secondPlayerVictories = 0;
			Collection<ScoreReadRepresentation> scoreCollection = rep.getScores();
			for (ScoreReadRepresentation score : scoreCollection) {
				int firstPlayerPoints = score.getFirstPlayerPoints();
				int secondPlayerPoints = score.getSecondPlayerPoints();
				if (firstPlayerPoints > secondPlayerPoints) {
					firstPlayerVictories = firstPlayerVictories + 1;
				} else if (firstPlayerPoints < secondPlayerPoints) {
					secondPlayerVictories = secondPlayerVictories + 1;
				}
			}
			if (firstPlayerVictories > secondPlayerVictories) {
				rep.setWinnerName(rep.getFirstPlayerFullName());
				rep.setWinnerTeam(rep.getFirstPlayerTeamName());
			} else if (firstPlayerVictories < secondPlayerVictories) {
				rep.setWinnerName(rep.getSecondPlayerFullName());
				rep.setWinnerTeam(rep.getSecondPlayerTeamName());
			}
		}

	}

	public static Collection<SingleGameResultReadRepresentation> getCollection(Collection<SingleGame> singleGames) {
		Collection<SingleGameResultReadRepresentation> representations = new ArrayList<>();

		for (SingleGame game : singleGames) {

			SingleGameResultReadRepresentation rep = findExisting(representations, game);
			if (rep == null) {
				rep = new SingleGameResultReadRepresentation();
				rep.setEventDate(new SimpleDateFormat("yyyy-MM-dd").format(game.getEventDate()));
				Player firstPlayer = game.getFirstPlayer();
				rep.firstPlayerFullName = firstPlayer.getFirstName() + " " + firstPlayer.getLastName();
				rep.firstPlayerTeamName = firstPlayer.getTeam().getName();
				rep.firstPlayerImageName = firstPlayer.getFirstName().toLowerCase() + "_"
						+ firstPlayer.getLastName().replaceAll(" ", "").toLowerCase() + ".JPG";
				Player secondPlayer = game.getSecondPlayer();
				rep.secondPlayerFullName = secondPlayer.getFirstName() + " " + secondPlayer.getLastName();
				rep.secondPlayerTeamName = secondPlayer.getTeam().getName();
				rep.secondPlayerImageName = secondPlayer.getFirstName().toLowerCase() + "_"
						+ secondPlayer.getLastName().replaceAll(" ", "").toLowerCase() + ".JPG";

				rep.scores.add(new ScoreReadRepresentation(game.getFirstPlayerTotalPoints(),
						game.getSecondPlayerTotalPoints()));
				representations.add(rep);
			} else {
				rep.scores.add(new ScoreReadRepresentation(game.getFirstPlayerTotalPoints(),
						game.getSecondPlayerTotalPoints()));
			}
		}
		for (SingleGameResultReadRepresentation singleGameResultReadRepresentation : representations) {
			int missing = 5 - singleGameResultReadRepresentation.scores.size();
			for (int i = 0; i < missing; i++) {
				singleGameResultReadRepresentation.scores.add(new ScoreReadRepresentation());
			}
		}

		updateWinnerName(representations);
		return representations;
	}

	private static SingleGameResultReadRepresentation findExisting(
			Collection<SingleGameResultReadRepresentation> representations, SingleGame game) {

		for (SingleGameResultReadRepresentation rep : representations) {
			Player firstPlayer = game.getFirstPlayer();
			Player secondPlayer = game.getSecondPlayer();
			if (rep.getFirstPlayerFullName().equals(firstPlayer.getFirstName() + " " + firstPlayer.getLastName())
					&& rep.getSecondPlayerFullName()
							.equals(secondPlayer.getFirstName() + " " + secondPlayer.getLastName())
					&& rep.getEventDate().equals(new SimpleDateFormat("yyyy-MM-dd").format(game.getEventDate()))) {
				return rep;
			}
		}

		return null;
	}

	@Override
	public String toString() {
		return "SingleGameResultReadRepresentation [firstPlayerFullName=" + firstPlayerFullName
				+ ", firstPlayerImageName=" + firstPlayerImageName + ", firstPlayerTeamName=" + firstPlayerTeamName
				+ ", secondPlayerFullName=" + secondPlayerFullName + ", secondPlayerImageName=" + secondPlayerImageName
				+ ", secondPlayerTeamName=" + secondPlayerTeamName + ", scores=" + scores + ", winnerName=" + winnerName
				+ ", eventDate=" + eventDate + "]";
	}

	public String getFirstPlayerFullName() {
		return firstPlayerFullName;
	}

	public void setFirstPlayerFullName(String firstPlayerFullName) {
		this.firstPlayerFullName = firstPlayerFullName;
	}

	public String getFirstPlayerImageName() {
		return firstPlayerImageName;
	}

	public void setFirstPlayerImageName(String firstPlayerImageName) {
		this.firstPlayerImageName = firstPlayerImageName;
	}

	public String getFirstPlayerTeamName() {
		return firstPlayerTeamName;
	}

	public void setFirstPlayerTeamName(String firstPlayerTeamName) {
		this.firstPlayerTeamName = firstPlayerTeamName;
	}

	public String getSecondPlayerFullName() {
		return secondPlayerFullName;
	}

	public void setSecondPlayerFullName(String secondPlayerFullName) {
		this.secondPlayerFullName = secondPlayerFullName;
	}

	public String getSecondPlayerImageName() {
		return secondPlayerImageName;
	}

	public void setSecondPlayerImageName(String secondPlayerImageName) {
		this.secondPlayerImageName = secondPlayerImageName;
	}

	public String getSecondPlayerTeamName() {
		return secondPlayerTeamName;
	}

	public void setSecondPlayerTeamName(String secondPlayerTeamName) {
		this.secondPlayerTeamName = secondPlayerTeamName;
	}

	public Collection<ScoreReadRepresentation> getScores() {
		return scores;
	}

	public void setScores(Collection<ScoreReadRepresentation> scores) {
		this.scores = scores;
	}

	public String getWinnerName() {
		return winnerName;
	}

	public void setWinnerName(String winnerName) {
		this.winnerName = winnerName;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getWinnerTeam() {
		return winnerTeam;
	}

	public void setWinnerTeam(String winnerTeam) {
		this.winnerTeam = winnerTeam;
	}

}
