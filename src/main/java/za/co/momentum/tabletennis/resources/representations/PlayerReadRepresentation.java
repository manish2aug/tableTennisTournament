package za.co.momentum.tabletennis.resources.representations;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import za.co.momentum.tabletennis.models.Player;
import za.co.momentum.tabletennis.models.Team;

public class PlayerReadRepresentation {

	private long id;
	private String firstName;
	private String lastName;
	private String photoPath;
	private String teamName;
	private String teamId;
	private String teamColor;
	@JsonProperty(value = "isCaptain")
	private boolean isCaptain;

	@Override
	public String toString() {
		return "PlayerReadRepresentation [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", photoPath=" + photoPath + ", teamName=" + teamName + ", teamColor=" + teamColor + ", isCaptain="
				+ isCaptain + "]";
	}

	public PlayerReadRepresentation() {
	}

	public PlayerReadRepresentation(Player player) {
		this.id = player.getId();
		this.firstName = player.getFirstName();
		this.lastName = player.getLastName();
		this.photoPath = player.getPhotoPath();
		this.isCaptain = player.isCaptain();
		Team team = player.getTeam();
		if (team != null) {
			this.teamName = team.getName();
			this.teamId = String.valueOf(team.getId());
			this.teamColor = team.getColor();
		}
	}

	public static Collection<PlayerReadRepresentation> getConvertedCollection(Collection<Player> players) {
		Collection<PlayerReadRepresentation> collection = new ArrayList<>();
		for (Player player : players) {
			collection.add(new PlayerReadRepresentation(player));
		}
		return collection;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public boolean isCaptain() {
		return isCaptain;
	}

	public void setCaptain(boolean isCaptain) {
		this.isCaptain = isCaptain;
	}

	public String getTeamColor() {
		return teamColor;
	}

	public void setTeamColor(String teamColor) {
		this.teamColor = teamColor;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

}
