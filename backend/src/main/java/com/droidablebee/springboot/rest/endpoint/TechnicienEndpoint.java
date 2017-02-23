package com.droidablebee.springboot.rest.endpoint;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.droidablebee.springboot.rest.domain.InfosAuth;
import com.droidablebee.springboot.rest.domain.Technicien;
import com.droidablebee.springboot.rest.service.TechnicienService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@Validated // required for @Valid on method parameters such as @RequesParam,
			// @PathVariable, @RequestHeader
public class TechnicienEndpoint extends BaseEndpoint {

	static final int DEFAULT_PAGE_SIZE = 10;
	static final String HEADER_TOKEN = "token";
	static final String HEADER_USER_ID = "userId";

	@Autowired
	TechnicienService technicienService;

	@CrossOrigin
	@RequestMapping(path = "/v1/technicien", method = RequestMethod.GET)
	@ApiOperation(value = "R?cup?ration de tous les techniciens", notes = "R?cup?ration de tous les techniciens", response = Page.class)
	public Page<Technicien> getAll(
			@ApiParam("The size of the page to be returned") @RequestParam(required = false) Integer size,
			@ApiParam("Zero-based page index") @RequestParam(required = false) Integer page) {

		if (size == null) {
			size = DEFAULT_PAGE_SIZE;
		}
		if (page == null) {
			page = 0;
		}

		Pageable pageable = new PageRequest(page, size);
		Page<Technicien> techniciens = technicienService.findAll(pageable);
		
		System.out.println(techniciens.getContent().get(0).toString());

		return techniciens;
	}

	@CrossOrigin
	@RequestMapping(path = "/v1/technicien/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "R?cup?re un technicien par son ID", notes = "R?cup?re un technicien par son ID", response = Technicien.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Person not found") })
	public ResponseEntity<Technicien> get(@ApiParam("Technicien id") @PathVariable("id") Long id) {

		Technicien technicien = technicienService.findOne(id);
		return (technicien == null ? ResponseEntity.status(HttpStatus.NOT_FOUND) : ResponseEntity.ok()).body(technicien);
	}

	@CrossOrigin
	@RequestMapping(path = "/v1/technicien", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Cr?ation/mise ? jour d'un technicien", notes = "Cr?ation/mise ? jour d'un technicien", response = Technicien.class)
	public ResponseEntity<Technicien> add(@Valid @RequestBody Technicien technicien,
			@Valid @Size(max = 40, min = 8, message = "user id size 8-40") @RequestHeader(name = HEADER_USER_ID) String userId,
			@Valid @Size(max = 40, min = 2, message = "token size 2-40") @RequestHeader(name = HEADER_TOKEN, required = false) String token) {

		technicien = technicienService.save(technicien);
		return ResponseEntity.ok().body(technicien);
	}
	
	@CrossOrigin
	@RequestMapping(path = "/v1/technicien/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Supprime un technicien par son id", notes = "Supprime un technicien par son id", response = Technicien.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Technicien not found") })
	public ResponseEntity<Long> delete(@ApiParam("Technicien id") @PathVariable("id") Long id) {

		technicienService.delete(id);
		return ResponseEntity.ok().body(id);
	}
	
	@CrossOrigin
	@RequestMapping(path = "/v1/login", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Authentification d'un technicien", notes = "Authentification d'un technicien", response = Technicien.class)
	public ResponseEntity<Technicien> connexion(@Valid @RequestBody InfosAuth infosAuth,
			@Valid @Size(max = 40, min = 8, message = "user id size 8-40") @RequestHeader(name = HEADER_USER_ID) String userId,
			@Valid @Size(max = 40, min = 2, message = "token size 2-40") @RequestHeader(name = HEADER_TOKEN, required = false) String token) {

		Technicien technicien = technicienService.findByUserNameAndPassword(infosAuth.getUserName(), infosAuth.getPassword());
		System.out.println(technicien.toString());
		return ResponseEntity.ok().body(technicien);
	}

	@InitBinder("person")
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new TechnicienValidator());
	}
}