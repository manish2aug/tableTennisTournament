package za.co.momentum.tabletennis.services;

import java.util.Collection;

import za.co.momentum.tabletennis.models.Player;
import za.co.momentum.tabletennis.models.Team;

public interface PlayerService {

	Collection<Player> findAll();

	Collection<Player> findByTeam(Team team);
}
