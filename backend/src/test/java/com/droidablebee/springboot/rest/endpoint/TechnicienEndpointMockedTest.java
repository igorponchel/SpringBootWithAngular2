package com.droidablebee.springboot.rest.endpoint;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.NestedServletException;

import com.droidablebee.springboot.rest.domain.Technicien;
import com.droidablebee.springboot.rest.service.TechnicienService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class TechnicienEndpointMockedTest extends BaseEndpointTest {

    @MockBean
    private TechnicienService technicienService;

    private Technicien testTech;

    @Before
    public void setup() throws Exception {

        super.setup();

        testTech = new Technicien(1L, "Igor", "Ponchel", "igouz", "igouz", "igouz@mail.fr", "M",
                null, "Expert", "24 boulevard bonrepos Toulouse", true);
        when(technicienService.findOne(1L)).thenReturn(testTech);

    }

    @Test
    public void getPersonById() throws Exception {

        mockMvc.perform(get("/v1/technicien/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(JSON_MEDIA_TYPE))
                .andExpect(jsonPath("$.id", is(testTech.getId().intValue())))
                .andExpect(jsonPath("$.firstName", is(testTech.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(testTech.getLastName())))
        ;
    }

    @Test(expected = NestedServletException.class)
    public void handleGenericException() throws Exception {

        when(technicienService.findOne(1L)).thenThrow(new RuntimeException("Failed to get person by id"));

        mockMvc.perform(get("/v1/technicien/{id}", 1))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(""))
        ;
    }
}
