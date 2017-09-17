package za.co.momentum.tabletennis.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import za.co.momentum.tabletennis.models.Player;
import za.co.momentum.tabletennis.models.Team;

public interface PlayerRepository extends JpaRepository<Player, Serializable> {

	List<Player> findByTeam(Team team);

	@Query("SELECT p FROM Player p WHERE p.team.id != :teamId")
	List<Player> findAllPlayersBelongsToOtherTeam(@Param("teamId") int teamId);

}
