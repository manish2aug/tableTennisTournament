package za.co.momentum.tabletennis.resources;

import java.text.ParseException;
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
import za.co.momentum.tabletennis.resources.representations.AuthRepresentation;
import za.co.momentum.tabletennis.resources.representations.ResponseMessage;
import za.co.momentum.tabletennis.resources.representations.SingleGameResultReadRepresentation;
import za.co.momentum.tabletennis.resources.representations.SingleGameWriteRepresentationContainer;
import za.co.momentum.tabletennis.services.AuthService;
import za.co.momentum.tabletennis.services.SingleGameService;

@RestController
@RequestMapping(value = "/rest")
public class SingleGameResource {

	@Autowired
	SingleGameService service;

	@Autowired
	AuthService authService;

	@RequestMapping(value = "/singles", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<SingleGameResultReadRepresentation> findAll() {
		Collection<SingleGame> findAll = service.findAll();
		return SingleGameResultReadRepresentation.getCollection(findAll);
	}

	@RequestMapping(value = "/singles", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseMessage> save(@RequestBody(required = true) SingleGameWriteRepresentationContainer obj) throws ParseException {
		
		String captainId = obj.getCaptainId();
		if(captainId != null) {
			AuthRepresentation auth = authService.validate(captainId);
			if(!auth.isValid()) {
				return new ResponseEntity<ResponseMessage>(HttpStatus.UNAUTHORIZED);
			}
		}else {
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("No Credential", "Captain Key not supplied"), HttpStatus.BAD_REQUEST);
		}
		
		service.save(obj.getCollection());
		return new ResponseEntity<ResponseMessage>(HttpStatus.CREATED);
	}
}