package za.co.momentum.tabletennis.resources.representations;

public class ScoreReadRepresentation {

	private int firstPlayerPoints;
	private int secondPlayerPoints;

	@Override
	public String toString() {
		return "ScoreReadRepresentation [firstPlayerPoints=" + firstPlayerPoints + ", secondPlayerPoints="
				+ secondPlayerPoints + "]";
	}

	public ScoreReadRepresentation() {
		super();
	}

	public ScoreReadRepresentation(int firstPlayerPoints, int secondPlayerPoints) {
		super();
		this.firstPlayerPoints = firstPlayerPoints;
		this.secondPlayerPoints = secondPlayerPoints;
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
