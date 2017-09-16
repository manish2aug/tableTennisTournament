package za.co.momentum.tabletennis.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.momentum.tabletennis.models.SingleGame;
import za.co.momentum.tabletennis.repositories.SingleGameRepository;
import za.co.momentum.tabletennis.services.SingleGameService;

@Service
public class SingleGameServiceImpl implements SingleGameService {

	@Autowired
	private SingleGameRepository repository;

	@Override
	public Collection<SingleGame> findAll() {
		return repository.findAll();
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

}
