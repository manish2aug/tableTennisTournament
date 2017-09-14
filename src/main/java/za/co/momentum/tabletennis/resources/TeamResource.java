package za.co.momentum.tabletennis.resources;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.momentum.tabletennis.models.Team;
import za.co.momentum.tabletennis.services.TeamService;

@RestController
@RequestMapping(value = "/rest")
public class TeamResource {

	@Autowired
	TeamService service;

	// @CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/teams", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Team> findAll() {
		return service.findAll();
	}
}