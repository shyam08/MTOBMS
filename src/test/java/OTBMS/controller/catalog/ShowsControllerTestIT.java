package OTBMS.controller.catalog;

import com.fasterxml.jackson.databind.ObjectMapper;
import OTBMS.dao.catalog.*;
import OTBMS.dto.catalog.ShowResponse;
import OTBMS.dto.catalog.ShowUpsertRequest;
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

import java.sql.Time;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ShowsControllerTestIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MovieShowsRepository showsRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private AudiRepository audiRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveMovieShow() throws Exception {
        ShowUpsertRequest request = new ShowUpsertRequest(1, 1, 0, 0, new Date());
        ResultActions response = mockMvc.perform(post("/catalog-svc/v1/shows/")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)));
        MvcResult result = response.andDo(print()).andExpect(status().isCreated()).andReturn();
        ShowResponse showResponse = objectMapper.readValue(result.getResponse().getContentAsString(), ShowResponse.class);
        assertNotNull(showResponse);
        assertNotNull(showResponse.getShowId());
        assertTrue(showResponse.getMovieId().equals(request.getMovieId()));
        assertTrue(showResponse.getAudiId().equals(request.getAudiId()));
        assertTrue(showResponse.getShowDateTime().toString().equals(request.getShowDateTime().toString()));
        showsRepository.deleteById(showResponse.getShowId());
    }

    @Test
    void getMovieShow() throws Exception {
        Audi audi = audiRepository.findById(1).get();
        Movie movie = movieRepository.findById(1).get();
        Date showDateTime = new Date();
        MovieShows movieShow = showsRepository.save(new MovieShows(null, movie, audi, 0, 0, new Time(showDateTime.getTime()),
                new java.sql.Date(showDateTime.getTime())));
        ResultActions response = mockMvc.perform(get("/catalog-svc/v1/shows/{id}", movieShow.getId()));
        MvcResult result = response.andDo(print()).andExpect(status().isOk()).andReturn();
        ShowResponse showResponse = objectMapper.readValue(result.getResponse().getContentAsString(), ShowResponse.class);
        assertNotNull(showResponse);
        assertNotNull(showResponse.getShowId());
        assertTrue(showResponse.getShowId().equals(movieShow.getId()));
        assertTrue(showResponse.getMovieId().equals(movieShow.getMovie().getId()));
        assertTrue(showResponse.getAudiId().equals(movieShow.getAudi().getId()));
        showsRepository.deleteById(showResponse.getShowId());
    }

    @Test
    void updateMovieShow() throws Exception {
        Audi audi = audiRepository.findById(1).get();
        Movie movie = movieRepository.findById(1).get();
        Date showDateTime = new Date();
        MovieShows movieShow = showsRepository.save(new MovieShows(null, movie, audi, 0, 0, new Time(showDateTime.getTime()),
                new java.sql.Date(showDateTime.getTime())));
        ShowUpsertRequest request = new ShowUpsertRequest(1, 1, 12, 10, new Date());
        ResultActions response = mockMvc.perform(put("/catalog-svc/v1/shows/{id}", movieShow.getId())
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)));
        MvcResult result = response.andDo(print()).andExpect(status().isOk()).andReturn();
        ShowResponse showResponse = objectMapper.readValue(result.getResponse().getContentAsString(), ShowResponse.class);
        assertNotNull(showResponse);
        assertNotNull(showResponse.getShowId());
        assertTrue(showResponse.getMovieId().equals(request.getMovieId()));
        assertTrue(showResponse.getAudiId().equals(request.getAudiId()));
        assertTrue(showResponse.getShowDateTime().toString().equals(request.getShowDateTime().toString()));
        assertTrue(showResponse.getBookedSeats().equals(request.getBookedSeats()));
        assertTrue(showResponse.getBlockedSeats().equals(request.getBlockedSeats()));
        showsRepository.deleteById(showResponse.getShowId());
    }

    @Test
    void deleteMovieShow() throws Exception {
        Audi audi = audiRepository.findById(1).get();
        Movie movie = movieRepository.findById(1).get();
        Date showDateTime = new Date();
        MovieShows movieShow = showsRepository.save(new MovieShows(null, movie, audi, 0, 0, new Time(showDateTime.getTime()),
                new java.sql.Date(showDateTime.getTime())));
        ResultActions response = mockMvc.perform(delete("/catalog-svc/v1/shows/{id}", movieShow.getId()));
        response.andDo(print()).andExpect(status().isOk());

    }

    @Test
    void getFreeSeatsCount() throws Exception {
        Audi audi = audiRepository.findById(1).get();
        Movie movie = movieRepository.findById(1).get();
        Date showDateTime = new Date();
        MovieShows movieShow = showsRepository.save(new MovieShows(null, movie, audi, 10, 30,
                new Time(showDateTime.getTime()),
                new java.sql.Date(showDateTime.getTime())));
        ResultActions response = mockMvc.perform(get("/catalog-svc/v1/shows/{id}/free-seats", movieShow.getId()));
        MvcResult result = response.andDo(print()).andExpect(status().isOk()).andReturn();
        Integer freeSeats = objectMapper.readValue(result.getResponse().getContentAsString(), Integer.class);
        assertNotNull(freeSeats);
        assertTrue(freeSeats == (audi.getTotalSeats() - (movieShow.getBlockedSeats() + movieShow.getBookedSeats())));
        showsRepository.deleteById(movieShow.getId());
    }
}