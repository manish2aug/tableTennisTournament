package za.co.momentum.tabletennis.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.momentum.tabletennis.models.DoubleGame;
import za.co.momentum.tabletennis.repositories.DoubleGameRepository;
import za.co.momentum.tabletennis.services.DoubleGameService;

@Service
public class DoubleGameServiceImpl implements DoubleGameService {

	@Autowired
	private DoubleGameRepository repository;

	@Override
	public Collection<DoubleGame> findAll() {
		return repository.findAll();
	}

	@Override
	public void save(Collection<DoubleGame> entityCollection) {
		repository.save(entityCollection);
	}

}
