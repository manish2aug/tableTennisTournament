package za.co.momentum.tabletennis.services;

import java.util.Collection;

import za.co.momentum.tabletennis.models.Team;

public interface TeamService {
	Collection<Team> findAll();

	Team findById(Integer id);
}
