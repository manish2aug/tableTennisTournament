package za.co.momentum.tabletennis.resources;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.momentum.tabletennis.models.DoubleGame;
import za.co.momentum.tabletennis.resources.representations.DoubleGameResultReadRepresentation;
import za.co.momentum.tabletennis.services.DoubleGameService;

@RestController
@RequestMapping(value = "/rest")
public class DoubleGameResource {

	@Autowired
	DoubleGameService service;

	@GetMapping(value = "/doubles", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<DoubleGameResultReadRepresentation> findAll() {
		Collection<DoubleGame> findAll = service.findAll();
		return DoubleGameResultReadRepresentation.getCollection(findAll);
	}
}