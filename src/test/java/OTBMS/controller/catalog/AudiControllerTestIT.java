package OTBMS.controller.catalog;

import com.fasterxml.jackson.databind.ObjectMapper;
import OTBMS.dao.catalog.Audi;
import OTBMS.dao.catalog.AudiRepository;
import OTBMS.dao.catalog.Theatre;
import OTBMS.dao.catalog.TheatreRepository;
import OTBMS.dto.catalog.AudiResponse;
import OTBMS.dto.catalog.AudiUpsertRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AudiControllerTestIT {

    @Autowired
    private AudiRepository audiRepository;
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveAudi() throws Exception {
        AudiUpsertRequest request = new AudiUpsertRequest("AudiTest", 50, 30, 10, 90, 1);
        ResultActions response = mockMvc.perform(post("/catalog-svc/v1/audies/")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)));
        MvcResult result = response.andDo(print()).andExpect(status().isCreated()).andReturn();
        AudiResponse audiResponse = objectMapper.readValue(result.getResponse().getContentAsString(), AudiResponse.class);
        assertNotNull(audiResponse);
        assertNotNull(audiResponse.getId());
        assertTrue(request.getName().equals(audiResponse.getName()));
        assertTrue(request.getTheatreId().equals(audiResponse.getTheatreId()));
        audiRepository.deleteById(audiResponse.getId());
    }

    @Test
    void getAudi() throws Exception {
        Theatre theatre = theatreRepository.findById(1).get();
        Audi audi = new Audi(null, "AudiTest", 50, 30, 10, 90, theatre);
        Audi audiDb = audiRepository.save(audi);
        ResultActions response = mockMvc.perform(get("/catalog-svc/v1/audies/{id}", audi.getId()));
        MvcResult result = response.andDo(print()).andExpect(status().isOk()).andReturn();
        AudiResponse audiResponse = objectMapper.readValue(result.getResponse().getContentAsString(), AudiResponse.class);
        assertNotNull(audiResponse);
        assertTrue(audiDb.getId().equals(audiResponse.getId()));
        assertTrue(audiDb.getName().equals(audiResponse.getName()));
        assertTrue(audiDb.getTheatre().getId().equals(audiResponse.getTheatreId()));
        audiRepository.deleteById(audiResponse.getId());
    }

    @Test
    void updateAudi() throws Exception {
        Theatre theatre = theatreRepository.findById(1).get();
        Audi audi = new Audi(null, "AudiTest", 50, 30, 10, 90, theatre);
        Audi audiDb = audiRepository.save(audi);
        AudiUpsertRequest request = new AudiUpsertRequest("AudiTest", 50, 40, 10, 100, 1);
        ResultActions response = mockMvc.perform(put("/catalog-svc/v1/audies/{id}", audiDb.getId())
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)));
        MvcResult result = response.andDo(print()).andExpect(status().isOk()).andReturn();
        AudiResponse audiResponse = objectMapper.readValue(result.getResponse().getContentAsString(), AudiResponse.class);
        assertNotNull(audiResponse);
        assertTrue(audiDb.getId().equals(audiResponse.getId()));
        assertTrue(audiDb.getName().equals(audiResponse.getName()));
        assertTrue(audiDb.getTheatre().getId().equals(audiResponse.getTheatreId()));
        assertTrue(request.getMiddleSeats().equals(audiResponse.getMiddleSeats()));
        audiRepository.deleteById(audiResponse.getId());
    }

    @Test
    void deleteAudi() throws Exception {
        Theatre theatre = theatreRepository.findById(1).get();
        Audi audi = new Audi(null, "AudiTest", 50, 30, 10, 90, theatre);
        Audi audiDb = audiRepository.save(audi);
        ResultActions response = mockMvc.perform(delete("/catalog-svc/v1/audies/{id}", audi.getId()));
        response.andDo(print()).andExpect(status().isOk());
    }
}