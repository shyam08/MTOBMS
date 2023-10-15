package otbms.controller.catalog;

import com.fasterxml.jackson.databind.ObjectMapper;
import otbms.dao.catalog.City;
import otbms.dao.catalog.CityRepository;
import otbms.dao.catalog.Theatre;
import otbms.dao.catalog.TheatreRepository;
import otbms.dto.catalog.TheatreResponse;
import otbms.dto.catalog.TheatreUpsertRequest;
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
class TheatreCatalogControllerTestIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private CityRepository cityRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveTheatre() throws Exception {
        TheatreUpsertRequest request = new TheatreUpsertRequest("theatreTest", 75.12, 34.12, 9945785632L, "sector19, noida"
                , 1);
        ResultActions response = mockMvc.perform(post("/catalog-svc/v1/theatres/")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)));
        MvcResult result = response.andDo(print()).andExpect(status().isCreated()).andReturn();
        TheatreResponse theatreResponse = objectMapper.readValue(result.getResponse().getContentAsString(), TheatreResponse.class);
        assertNotNull(theatreResponse);
        assertNotNull(theatreResponse.getId());
        assertTrue(request.getName().equals(theatreResponse.getName()));
        assertTrue(request.getCityId().equals(theatreResponse.getCityId()));
        theatreRepository.deleteById(theatreResponse.getId());
    }

    @Test
    void getTheatre() throws Exception {
        City cityDb = cityRepository.findById(1).get();
        Theatre theatreDb = theatreRepository.save(new Theatre(null, "theatreTest", 75.12, 34.12, 9945785632L, "sector19, noida"
                , cityDb));
        ResultActions response = mockMvc.perform(get("/catalog-svc/v1/theatres/{id}", theatreDb.getId()));
        MvcResult result = response.andDo(print()).andExpect(status().isOk()).andReturn();
        TheatreResponse theatreResponse = objectMapper.readValue(result.getResponse().getContentAsString(), TheatreResponse.class);
        assertNotNull(theatreResponse);
        assertNotNull(theatreResponse.getId());
        assertTrue(theatreDb.getName().equals(theatreResponse.getName()));
        assertTrue(theatreDb.getCity().getId().equals(theatreResponse.getCityId()));
        theatreRepository.deleteById(theatreResponse.getId());
    }

    @Test
    void updateTheatre() throws Exception {
        City cityDb = cityRepository.findById(1).get();
        Theatre theatreDb = theatreRepository.save(new Theatre(null, "theatreTest", 75.12, 34.12, 9945785632L, "sector19, noida"
                , cityDb));
        TheatreUpsertRequest request = new TheatreUpsertRequest("theatreTest", 75.12, 34.12, 9945785600L, "sector19, " +
                "noida"
                , 1);
        ResultActions response = mockMvc.perform(put("/catalog-svc/v1/theatres/{id}", theatreDb.getId())
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)));
        MvcResult result = response.andDo(print()).andExpect(status().isOk()).andReturn();
        TheatreResponse theatreResponse = objectMapper.readValue(result.getResponse().getContentAsString(), TheatreResponse.class);
        assertNotNull(theatreResponse);
        assertTrue(theatreDb.getId().equals(theatreResponse.getId()));
        assertTrue(request.getName().equals(theatreResponse.getName()));
        assertTrue(request.getCityId().equals(theatreResponse.getCityId()));
        theatreRepository.deleteById(theatreResponse.getId());
    }

    @Test
    void deleteTheatre() throws Exception {
        City cityDb = cityRepository.findById(1).get();
        Theatre theatreDb = theatreRepository.save(new Theatre(null, "theatreTest", 75.12, 34.12, 9945785632L, "sector19, noida"
                , cityDb));
        ResultActions response = mockMvc.perform(delete("/catalog-svc/v1/theatres/{id}", theatreDb.getId()));
        response.andDo(print()).andExpect(status().isOk());
    }
}