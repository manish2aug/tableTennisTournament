package za.co.momentum.tabletennis.services;

import java.text.ParseException;

import za.co.momentum.tabletennis.resources.representations.AuthRepresentation;

public interface AuthService {
	
	AuthRepresentation validate(String guid) throws ParseException;
}
