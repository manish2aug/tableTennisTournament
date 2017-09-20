package za.co.momentum.tabletennis.resources;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import za.co.momentum.tabletennis.models.DoubleGame;
import za.co.momentum.tabletennis.resources.representations.DoubleGameResultReadRepresentation;
import za.co.momentum.tabletennis.resources.representations.DoubleGameWriteRepresentation;
import za.co.momentum.tabletennis.resources.representations.ResponseMessage;
import za.co.momentum.tabletennis.services.DoubleGameService;

@RestController
@RequestMapping(value = "/rest")
public class DoubleGameResource {

	@Autowired
	DoubleGameService service;

	@RequestMapping(value = "/doubles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<DoubleGameResultReadRepresentation> findAll() {
		Collection<DoubleGame> findAll = service.findAll();
		return DoubleGameResultReadRepresentation.getCollection(findAll);
	}

	@RequestMapping(value = "/doubles", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseMessage> save(
			@RequestBody(required = true) Collection<DoubleGameWriteRepresentation> rep) {
		service.save(DoubleGameWriteRepresentation.getEntityCollection(rep));
		return new ResponseEntity<ResponseMessage>(HttpStatus.CREATED);
	}
}