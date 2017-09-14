package za.co.momentum.tabletennis.resources;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.momentum.tabletennis.models.DoubleGame;
import za.co.momentum.tabletennis.models.SingleGame;
import za.co.momentum.tabletennis.resources.representations.TeamRankingReadRepresentation;
import za.co.momentum.tabletennis.services.DoubleGameService;
import za.co.momentum.tabletennis.services.SingleGameService;

@RestController
@RequestMapping(value = "/rest")
public class TeamRankingResource {

	@Autowired
	DoubleGameService doublesService;

	@Autowired
	SingleGameService singlesService;

	@GetMapping(value = "/teamrankings", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object findAll() {

		Collection<DoubleGame> doubles = doublesService.findAll();
		Collection<SingleGame> singles = singlesService.findAll();
		return new TeamRankingReadRepresentation().getOrderedCollection(singles, doubles);
	}
}