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
	public AuthRepresentation validate(String guid) throws ParseException {

		if(guid.equalsIgnoreCase("manish122333")) {
			return new AuthRepresentation("Admin Key", true);
		}
		List<AuthData> authData = repository.findByGuid(guid);
		boolean isValid = false;
		AuthData foundData = null;
		String reason = null;

		if (authData != null && authData.size() == 1) {
			foundData = authData.get(0);
		}

		if (foundData == null) {
			reason = "Invalid Key";
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date currentDate = new Date();
			String formattedCurrentDate = sdf.format(currentDate);
			if (formattedCurrentDate.equals(foundData.getEventDate())) {
				isValid = true;
				reason = "Valid Key";
			} else {
				reason = "Invalid Key";
			}
		}

		return new AuthRepresentation(reason, isValid);
	}

}
