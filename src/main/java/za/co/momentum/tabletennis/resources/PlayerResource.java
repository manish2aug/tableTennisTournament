package za.co.momentum.tabletennis.resources;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.momentum.tabletennis.models.Team;
import za.co.momentum.tabletennis.resources.representations.PlayerReadRepresentation;
import za.co.momentum.tabletennis.services.PlayerService;
import za.co.momentum.tabletennis.services.TeamService;

@RestController
@RequestMapping(value = "/rest")
public class PlayerResource {

	@Autowired
	PlayerService playerService;

	@Autowired
	TeamService teamService;

	@GetMapping(value = "/players", produces = MediaType.APPLICATION_JSON_VALUE, params = { "teamId" })
	public Collection<PlayerReadRepresentation> findAll(int teamId) {
		Team team = teamService.findById(teamId);
		return PlayerReadRepresentation.getConvertedCollection(playerService.findByTeam(team));
	}
}