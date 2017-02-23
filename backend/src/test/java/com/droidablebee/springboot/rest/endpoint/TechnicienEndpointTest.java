
package com.droidablebee.springboot.rest.endpoint;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.droidablebee.springboot.rest.domain.Technicien;
import com.droidablebee.springboot.rest.service.TechnicienService;

import net.minidev.json.JSONArray;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class TechnicienEndpointTest extends BaseEndpointTest {

	@Autowired
	EntityManager entityManager;

	@Autowired
	private TechnicienService personService;

	private Technicien testTechnicien;
	private long timestamp;

    @Before
    public void setup() throws Exception {
    	super.setup();

    	timestamp = new Date().getTime();

    	// create test persons
    	/*personService.save(createTechnicien("Jack", "Bauer", "username", "password"));
    	personService.save(createTechnicien("Chloe", "O'Brian", "username", "password"));
    	personService.save(createTechnicien("Kim", "Bauer", "username", "password"));
    	personService.save(createTechnicien("David", "Palmer", "username", "password"));
    	personService.save(createTechnicien("Michelle", "Dessler", "username", "password"));*/

    	Page<Technicien> techniciens = personService.findAll(new PageRequest(0, TechnicienEndpoint.DEFAULT_PAGE_SIZE));
		assertNotNull(techniciens);
		assertEquals(6L, techniciens.getTotalElements());

		testTechnicien = techniciens.getContent().get(0);

		//refresh entity with any changes that have been done during persistence including Hibernate conversion
		//example: java.util.Date field is injected with either with java.sql.Date (if @Temporal(TemporalType.DATE) is used)
		//or java.sql.Timestamp
		entityManager.refresh(testTechnicien);
    }

    @Test
    public void getPersonById() throws Exception {
    	Long id = testTechnicien.getId();

    	MvcResult result = mockMvc.perform(get("/v1/technicien/{id}", id))
    	.andExpect(status().isOk())
    	.andExpect(content().contentType(JSON_MEDIA_TYPE))
    	.andExpect(jsonPath("$.id", is(id.intValue())))
    	.andExpect(jsonPath("$.firstName", is(testTechnicien.getFirstName())))
    	.andExpect(jsonPath("$.lastName", is(testTechnicien.getLastName())))
    	.andReturn()
    	;

    	logger.debug("content="+ result.getResponse().getContentAsString());
    }

    /*
     * Test JSR-303 bean validation.
     */

    @Test
    public void createPersonValidationErrorLastName() throws Exception {

    	//person with missing last name
    	Technicien person = new Technicien();
    	String content = json(person);
		mockMvc.perform(
				put("/v1/technicien")
				.accept(JSON_MEDIA_TYPE)
				.content(content)
				.contentType(JSON_MEDIA_TYPE).header("userId", "totototototo"))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(content().contentType(JSON_MEDIA_TYPE))
		.andExpect(jsonPath("$", isA(JSONArray.class)))
		.andExpect(jsonPath("$.length()", is(4))) // nombre de paramètres obligatoires
    	.andExpect(jsonPath("$.[?(@.field == 'lastName')].message", hasItem("may not be null")))
		;
    }

    /*
     * Test JSR-303 bean object graph validation with nested entities.
     */

    @Test
	@Ignore
    public void createPersonValidationAddress() throws Exception {

    	Technicien technicien = createTechnicien("first", "last", "username", "password");

    	String content = json(technicien);
		mockMvc.perform(
				put("/v1/technicien")
				.accept(JSON_MEDIA_TYPE)
				.content(content)
				.contentType(JSON_MEDIA_TYPE))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(content().contentType(JSON_MEDIA_TYPE))
		.andExpect(jsonPath("$.length()", is(4)))
    	.andExpect(jsonPath("$.[?(@.field == 'addresses[].line1')].message", hasItem("may not be null")))
    	.andExpect(jsonPath("$.[?(@.field == 'addresses[].state')].message", hasItem("may not be null")))
    	.andExpect(jsonPath("$.[?(@.field == 'addresses[].city')].message", hasItem("may not be null")))
    	.andExpect(jsonPath("$.[?(@.field == 'addresses[].zip')].message", hasItem("may not be null")))
		;
    }

    @Test
    public void createPersonValidationToken() throws Exception {

    	Technicien person = createTechnicien("first", "last", "username", "password");

    	String content = json(person);
    	mockMvc.perform(
    			put("/v1/technicien")
    			.header(TechnicienEndpoint.HEADER_USER_ID, UUID.randomUUID())
    			.header(TechnicienEndpoint.HEADER_TOKEN, "1") //invalid token
    			.accept(JSON_MEDIA_TYPE)
    			.content(content)
    			.contentType(JSON_MEDIA_TYPE))
    	.andDo(print())
    	.andExpect(status().isBadRequest())
    	.andExpect(content().contentType(JSON_MEDIA_TYPE))
    	.andExpect(jsonPath("$.length()", is(1)))
    	.andExpect(jsonPath("$.[?(@.field == 'add.arg2')].message", hasItem("token size 2-40")))
    	;
    }

    @Test
    public void createPersonValidationUserId() throws Exception {

    	Technicien person = createTechnicien("first", "last", "username", "password");

    	String content = json(person);
    	mockMvc.perform(
    			put("/v1/technicien")
    			.accept(JSON_MEDIA_TYPE)
    			.content(content)
    			.contentType(JSON_MEDIA_TYPE))
    	.andDo(print())
    	.andExpect(status().isBadRequest())
    	.andExpect(content().contentType(JSON_MEDIA_TYPE))
    	.andExpect(jsonPath("$.message", containsString("Missing request header '"+ TechnicienEndpoint.HEADER_USER_ID)))
    	;
    }

    @Test
    public void createTechnicien() throws Exception {

    	Technicien person = createTechnicien("first", "last", "username", "password");

    	String content = json(person);

    	mockMvc.perform(
				put("/v1/technicien")
    			.header(TechnicienEndpoint.HEADER_USER_ID, UUID.randomUUID())
				.accept(JSON_MEDIA_TYPE)
				.content(content)
				.contentType(JSON_MEDIA_TYPE))
		.andDo(print())
		.andExpect(status().isOk())
    	.andExpect(jsonPath("$.id", isA(Number.class)))
    	.andExpect(jsonPath("$.firstName", is(person.getFirstName())))
    	.andExpect(jsonPath("$.lastName", is(person.getLastName())))
		;
    }

    @Test
    public void createPersonWithDateVerification() throws Exception {

    	Technicien person = createTechnicien("first", "last", "username", "password");

    	String content = json(person);

    	mockMvc.perform(
    			put("/v1/technicien")
    			.header(TechnicienEndpoint.HEADER_USER_ID, UUID.randomUUID())
    			.accept(JSON_MEDIA_TYPE)
    			.content(content)
    			.contentType(JSON_MEDIA_TYPE))
    	.andExpect(status().isOk())
    	.andExpect(jsonPath("$.id", isA(Number.class)))
    	.andExpect(jsonPath("$.firstName", is(person.getFirstName())))
    	.andExpect(jsonPath("$.lastName", is(person.getLastName())))
    	;

    }

    @Test
    @Ignore
    public void requestBodyValidationInvalidJsonValue() throws Exception {

    	String content = json(testTechnicien);
    	//payload with invalid gender
    	content = content.replaceFirst("(\"firstName\":\")(M)(\")", "$1Q$3");

    	mockMvc.perform(
    			put("/v1/technicien")
    			.header(TechnicienEndpoint.HEADER_USER_ID, UUID.randomUUID())
    			.accept(JSON_MEDIA_TYPE)
    			.content(content)
    			.contentType(JSON_MEDIA_TYPE))
    	.andDo(print())
    	.andExpect(status().isBadRequest())
    	.andExpect(content().contentType(JSON_MEDIA_TYPE))
    	.andExpect(jsonPath("$.message", containsString("Could not read document: Can not deserialize value of type com.droidablebee.springboot.rest.domain.Person$Gender")))
    	;
    }

    @Test
    public void requestBodyValidationInvalidJson() throws Exception {

    	String content = json("not valid json");
    	mockMvc.perform(
    			put("/v1/technicien")
    			.header(TechnicienEndpoint.HEADER_USER_ID, UUID.randomUUID())
    			.accept(JSON_MEDIA_TYPE)
    			.content(content)
    			.contentType(JSON_MEDIA_TYPE))
    	.andDo(print())
    	.andExpect(status().isBadRequest())
    	.andExpect(content().contentType(JSON_MEDIA_TYPE))
    	.andExpect(jsonPath("$.message", containsString("Could not read document: Can not construct instance of com.droidablebee.springboot.rest.domain.Technicien")))
    	;
    }

    @Test
    public void handleHttpRequestMethodNotSupportedException() throws Exception {

    	String content = json(testTechnicien);

    	mockMvc.perform(
    			delete("/v1/technicien") //not supported method
    			.header(TechnicienEndpoint.HEADER_USER_ID, UUID.randomUUID())
    			.accept(JSON_MEDIA_TYPE)
    			.content(content)
    			.contentType(JSON_MEDIA_TYPE))
    	.andDo(print())
    	.andExpect(status().isMethodNotAllowed())
    	.andExpect(content().string(""))
    	;
    }

	private Technicien createTechnicien(String firstname, String lastname, String username, String password) {
		Technicien technicien = new Technicien(firstname, lastname, username, password);
		return technicien;
	}

}

