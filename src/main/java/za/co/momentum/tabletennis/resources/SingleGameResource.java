package za.co.momentum.tabletennis.resources;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.momentum.tabletennis.models.SingleGame;
import za.co.momentum.tabletennis.resources.representations.SingleGameResultReadRepresentation;
import za.co.momentum.tabletennis.services.SingleGameService;

@RestController
@RequestMapping(value = "/rest")
public class SingleGameResource {

	@Autowired
	SingleGameService service;

	@GetMapping(value = "/singles", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<SingleGameResultReadRepresentation> findAll() {
		Collection<SingleGame> findAll = service.findAll();
		return SingleGameResultReadRepresentation.getCollection(findAll);
	}
}