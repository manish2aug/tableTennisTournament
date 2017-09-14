package za.co.momentum.tabletennis.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.momentum.tabletennis.models.AuthData;
import za.co.momentum.tabletennis.repositories.AuthRepository;
import za.co.momentum.tabletennis.resources.representations.AuthRepresentation;
import za.co.momentum.tabletennis.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private AuthRepository repository;
	
	@Override
	public AuthRepresentation checkKey(String guid) throws ParseException {
		
		List<AuthData> AuthData = repository.findAll();
		boolean isValid = false;
		AuthData foundData = null;
		String reason = null;
		
		for (AuthData data : AuthData) {
			if (data.getGuid().equals(guid)) {
				foundData = data;
			}
		}
		
		if (foundData == null) {
			reason = "Invalid Key";
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
			Date currentDate = new Date();
			if (currentDate.after(sdf.parse(foundData.getEventDate()))) {
				reason = "Expired Key";
			}
		}
		
		return new AuthRepresentation(reason, isValid);
	}
	
}
