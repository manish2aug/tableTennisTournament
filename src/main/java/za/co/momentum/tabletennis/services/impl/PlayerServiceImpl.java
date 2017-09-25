package za.co.momentum.tabletennis.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.momentum.tabletennis.models.Player;
import za.co.momentum.tabletennis.models.Team;
import za.co.momentum.tabletennis.repositories.PlayerRepository;
import za.co.momentum.tabletennis.services.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository repository;

	@Override
	public Collection<Player> findAll() {
		return repository.findAll();
	}

	@Override
	public Collection<Player> findByTeam(Team team) {
		return repository.findByTeamOrderByIdAsc(team);
	}

	@Override
	public Collection<Player> findAllPlayersBelogToOtherTeam(int teamId) {
		return repository.findAllPlayersBelongsToOtherTeam(teamId);
	}

}
