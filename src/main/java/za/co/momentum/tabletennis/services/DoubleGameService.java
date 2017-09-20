package za.co.momentum.tabletennis.services;

import java.util.Collection;

import za.co.momentum.tabletennis.models.DoubleGame;

public interface DoubleGameService {

	Collection<DoubleGame> findAll();

	void save(Collection<DoubleGame> entityCollection);

}
