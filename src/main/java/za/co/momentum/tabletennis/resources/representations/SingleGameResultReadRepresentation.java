package za.co.momentum.tabletennis.resources.representations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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
	private String looserTeam;
	private int winnerPointBalance;

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
				rep.setLooserTeam(rep.getSecondPlayerTeamName());
			} else if (firstPlayerVictories < secondPlayerVictories) {
				rep.setWinnerName(rep.getSecondPlayerFullName());
				rep.setWinnerTeam(rep.getSecondPlayerTeamName());
				rep.setLooserTeam(rep.getFirstPlayerTeamName());
			}
		}

	}

	public static Collection<SingleGameResultReadRepresentation> getCollection(Collection<SingleGame> singleGames) {
		List<SingleGameResultReadRepresentation> representations = new ArrayList<>();

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
		updateWinnerPointBalance(representations);
		// sort the collection
		Comparator<SingleGameResultReadRepresentation> comparator = new Comparator<SingleGameResultReadRepresentation>() {
			@Override
			public int compare(SingleGameResultReadRepresentation left, SingleGameResultReadRepresentation right) {
				Date eventDate2 = null;
				Date eventDate3 = null;

				try {
					eventDate2 = new SimpleDateFormat("yyyy-MM-dd").parse(left.getEventDate());
					eventDate3 = new SimpleDateFormat("yyyy-MM-dd").parse(right.getEventDate());
				} catch (ParseException e) {
					e.printStackTrace();
				}

				return eventDate2.compareTo(eventDate3);
			}
		};

		Collections.sort(representations, comparator);

		return representations;
	}

	private static void updateWinnerPointBalance(List<SingleGameResultReadRepresentation> representations) {

		for (SingleGameResultReadRepresentation rep : representations) {
			int firstPlayerTotalPoints = 0;
			int secondPlayerTotalPoints = 0;
			Collection<ScoreReadRepresentation> scores = rep.getScores();

			for (ScoreReadRepresentation rep2 : scores) {
				firstPlayerTotalPoints = firstPlayerTotalPoints + rep2.getFirstPlayerPoints();
				secondPlayerTotalPoints = secondPlayerTotalPoints + rep2.getSecondPlayerPoints();
			}
			rep.setWinnerPointBalance(Math.abs(firstPlayerTotalPoints - secondPlayerTotalPoints));
		}

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

	@Override
	public int getWinnerPointBalance() {
		return winnerPointBalance;
	}

	public void setWinnerPointBalance(int winnerPointBalance) {
		this.winnerPointBalance = winnerPointBalance;
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

	public String getLooserTeam() {
		return looserTeam;
	}

	public void setLooserTeam(String looserTeam) {
		this.looserTeam = looserTeam;
	}
	

}
