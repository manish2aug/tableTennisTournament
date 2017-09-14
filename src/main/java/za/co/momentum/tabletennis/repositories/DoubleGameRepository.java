package za.co.momentum.tabletennis.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.momentum.tabletennis.models.DoubleGame;

public interface DoubleGameRepository extends JpaRepository<DoubleGame, Serializable> {

}