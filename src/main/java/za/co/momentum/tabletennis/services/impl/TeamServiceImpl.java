package za.co.momentum.tabletennis.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.momentum.tabletennis.models.Team;
import za.co.momentum.tabletennis.repositories.TeamRepository;
import za.co.momentum.tabletennis.services.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository repository;

	@Override
	public Collection<Team> findAll() {
		return repository.findAll();
	}

	@Override
	public Team findById(Integer id) {
		return repository.findOne(id);
	}

}
