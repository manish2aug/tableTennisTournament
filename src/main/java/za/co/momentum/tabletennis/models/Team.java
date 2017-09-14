package za.co.momentum.tabletennis.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "TEAMS", schema = "MOMENTUM_TOURNAMENT")
public class Team extends IdEntity {

	@NotNull
	@NotEmpty
	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@NotNull
	@NotEmpty
	@Column(name = "color", nullable = false, length = 30, unique = true)
	private String color;

	// @OneToMany(mappedBy = "team")
	// @JsonIgnoreProperties("team")
	// private Collection<Player> players = new ArrayList<>();

	public Team() {
	}

	public Team(String name, String color) {
		super();
		this.name = name;
		this.color = color;
	}

	@Override
	public String toString() {
		return "Team [name=" + name + ", color=" + color + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	// public Collection<Player> getPlayers() {
	// return players;
	// }
	//
	// public void setPlayers(Collection<Player> players) {
	// this.players = players;
	// }

}
