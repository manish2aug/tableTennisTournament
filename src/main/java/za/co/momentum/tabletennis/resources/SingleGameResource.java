package za.co.momentum.tabletennis.resources;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import za.co.momentum.tabletennis.models.SingleGame;
import za.co.momentum.tabletennis.resources.representations.ResponseMessage;
import za.co.momentum.tabletennis.resources.representations.SingleGameResultReadRepresentation;
import za.co.momentum.tabletennis.resources.representations.SingleGamrWriteRepresentation;
import za.co.momentum.tabletennis.services.SingleGameService;

@RestController
@RequestMapping(value = "/rest")
public class SingleGameResource {

	@Autowired
	SingleGameService service;

	@RequestMapping(value = "/singles", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<SingleGameResultReadRepresentation> findAll() {
		Collection<SingleGame> findAll = service.findAll();
		return SingleGameResultReadRepresentation.getCollection(findAll);
	}

	@RequestMapping(value = "/singles", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseMessage> save(
			@RequestBody(required = true) Collection<SingleGamrWriteRepresentation> rep) {
		Collection<SingleGame> entityCollection = new ArrayList<>();

		for (SingleGamrWriteRepresentation singleGamrWriteRepresentation : rep) {
			entityCollection.add(singleGamrWriteRepresentation.getEntityObject());
		}

		service.save(entityCollection);
		return new ResponseEntity<ResponseMessage>(HttpStatus.CREATED);
	}
}