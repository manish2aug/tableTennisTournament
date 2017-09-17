package za.co.momentum.tabletennis.services;

import java.util.Collection;

import za.co.momentum.tabletennis.models.SingleGame;

public interface SingleGameService {

	Collection<SingleGame> findAll();

	void save(Collection<SingleGame> entityCollection);

}
