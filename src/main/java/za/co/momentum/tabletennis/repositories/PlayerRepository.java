package za.co.momentum.tabletennis.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.momentum.tabletennis.models.Player;
import za.co.momentum.tabletennis.models.Team;

public interface PlayerRepository extends JpaRepository<Player, Serializable> {
	List<Player> findByTeam(Team team);
}
