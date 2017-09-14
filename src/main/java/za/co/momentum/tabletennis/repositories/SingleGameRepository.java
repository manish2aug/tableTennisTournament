package za.co.momentum.tabletennis.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.momentum.tabletennis.models.SingleGame;

public interface SingleGameRepository extends JpaRepository<SingleGame, Serializable> {

}