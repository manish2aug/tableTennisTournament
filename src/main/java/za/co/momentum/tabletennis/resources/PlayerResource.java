package za.co.momentum.tabletennis.resources;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import za.co.momentum.tabletennis.models.Player;
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
	
	@RequestMapping(value = "/players", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<PlayerReadRepresentation> findAll(@RequestParam(required = false, name = "teamId") String teamId) {
		Collection<Player> players;
		
		if (teamId != null) {
			Team team = teamService.findById(Integer.getInteger(teamId));
			players = playerService.findByTeam(team);
		}else{
			players = playerService.findAll();
		}
		return PlayerReadRepresentation.getConvertedCollection(players);
	}
}