package za.co.momentum.tabletennis.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.momentum.tabletennis.models.AuthData;

public interface AuthRepository extends JpaRepository<AuthData, Serializable> {

}