package za.co.momentum.tabletennis.resources;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.momentum.tabletennis.resources.representations.AuthRepresentation;
import za.co.momentum.tabletennis.services.AuthService;

@RestController
@RequestMapping(value = "/rest")
public class AuthResource {

	@Autowired
	AuthService service;

	@RequestMapping(value = "/auth/{guid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AuthRepresentation findAll(@PathVariable(name = "guid") String guid) throws ParseException {
		return service.validate(guid);
	}
}