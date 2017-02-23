package com.droidablebee.springboot.rest.endpoint;

import com.droidablebee.springboot.rest.domain.Parametrage;
import com.droidablebee.springboot.rest.service.ParametrageService;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@Validated //required for @Valid on method parameters such as @RequesParam, @PathVariable, @RequestHeader
public class ParametrageEndpoint extends BaseEndpoint {

	static final int DEFAULT_PAGE_SIZE = 10;
	static final String HEADER_TOKEN = "token";
	static final String HEADER_USER_ID = "userId";
	
	@Autowired 
	ParametrageService parametrageService;

	@GetMapping("/v1/parametrage")
    public List<Parametrage> getAll(
    		@ApiParam("Crit�re de tri") @RequestParam(required = false) String _sort, 
    		@ApiParam("Ordre de tri") @RequestParam(required = false) String _order, 
    		@ApiParam("Filtre sur le libelle") @RequestParam(required = false) String libelle_like, 
    		@ApiParam("The size of the page to be returned") @RequestParam(required = false) Integer size,
    		@ApiParam("Zero-based page index") @RequestParam(required = false) Integer page) {

		if (size == null) {
			size = DEFAULT_PAGE_SIZE;
		}
		if (page == null) {
			page = 0;
		}
		
		PageRequest request =
	            new PageRequest(page, size, Direction.ASC, "id");
		
		if (_sort != null && _order != null) {
			request = new PageRequest(page, size, Direction.fromString(_order), _sort);
		}

		if (libelle_like != null) {
			return parametrageService.findParametreWithLibelleLike(libelle_like);
		} else {
			return parametrageService.findAll(request).getContent();
		}
    }

	@PutMapping(path="/v1/parametrage", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Parametrage> add(@Valid @RequestBody Parametrage parametrage,
			@Valid @Size(max = 40, min = 8, message = "user id size 8-40") @RequestHeader(name = HEADER_USER_ID) String userId,
			@Valid @Size(max = 40, min = 2, message = "token size 2-40") @RequestHeader(name = HEADER_TOKEN, required = false) String token) {

		parametrage = parametrageService.save(parametrage);
		return ResponseEntity.ok().body(parametrage);
	}

	@DeleteMapping("/v1/parametrage/{id}")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Param�tre not found") })
	public ResponseEntity<Long> delete(@ApiParam("Parametrage id") @PathVariable("id") Long id) {

		parametrageService.delete(id);
		return ResponseEntity.ok().body(id);
	}
}