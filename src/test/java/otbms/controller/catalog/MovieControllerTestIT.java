package otbms.controller.catalog;

import com.fasterxml.jackson.databind.ObjectMapper;
import otbms.dao.catalog.Movie;
import otbms.dao.catalog.MovieRepository;
import otbms.dao.catalog.MovieVariant;
import otbms.dao.catalog.MovieVariantRepository;
import otbms.dto.catalog.MovieResponse;
import otbms.dto.catalog.MovieUpsertRequest;
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
class MovieControllerTestIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieVariantRepository movieVariantRepository;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveMovie() throws Exception {
        MovieUpsertRequest request = new MovieUpsertRequest("Movietest", "testing the movie", 2, 30, 1);
        ResultActions response = mockMvc.perform(post("/catalog-svc/v1/movies/")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)));
        MvcResult result = response.andDo(print()).andExpect(status().isCreated()).andReturn();
        MovieResponse movieResponse = objectMapper.readValue(result.getResponse().getContentAsString(), MovieResponse.class);
        assertNotNull(movieResponse);
        assertNotNull(movieResponse.getId());
        assertTrue(request.getName().equals(movieResponse.getName()));
        assertTrue(request.getVariantId().equals(movieResponse.getVariantId()));
        movieRepository.deleteById(movieResponse.getId());
    }

    @Test
    void getMovie() throws Exception {
        MovieVariant movieVariant = movieVariantRepository.findById(1).get();
        Movie movieDb = movieRepository.save(new Movie(null, "Movietest", "testing the movie", 2, 30, movieVariant));
        ResultActions response = mockMvc.perform(get("/catalog-svc/v1/movies/{id}", movieDb.getId()));
        MvcResult result = response.andDo(print()).andExpect(status().isOk()).andReturn();
        MovieResponse movieResponse = objectMapper.readValue(result.getResponse().getContentAsString(), MovieResponse.class);
        assertNotNull(movieResponse);
        assertTrue(movieDb.getId().equals(movieResponse.getId()));
        assertTrue(movieDb.getName().equals(movieResponse.getName()));
        assertTrue(movieDb.getVariant().getId().equals(movieResponse.getVariantId()));
        movieRepository.deleteById(movieResponse.getId());
    }

    @Test
    void updateMovie() throws Exception {
        MovieVariant movieVariant = movieVariantRepository.findById(1).get();
        Movie movieDb = movieRepository.save(new Movie(null, "Movietest", "testing the movie", 2, 30, movieVariant));
        MovieUpsertRequest request = new MovieUpsertRequest("Movietest", "testing the movie", 3, 10, 1);
        ResultActions response = mockMvc.perform(put("/catalog-svc/v1/movies/{id}", movieDb.getId())
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)));
        MvcResult result = response.andDo(print()).andExpect(status().isOk()).andReturn();
        MovieResponse movieResponse = objectMapper.readValue(result.getResponse().getContentAsString(), MovieResponse.class);
        assertNotNull(movieResponse);
        assertNotNull(movieResponse.getId());
        assertTrue(request.getName().equals(movieResponse.getName()));
        assertTrue(request.getVariantId().equals(movieResponse.getVariantId()));
        assertTrue(request.getDurationHour().equals(movieResponse.getDurationHour()));
        assertTrue(request.getDurationMint().equals(movieResponse.getDurationMint()));
        movieRepository.deleteById(movieResponse.getId());
    }

    @Test
    void deleteMovie() throws Exception {
        MovieVariant movieVariant = movieVariantRepository.findById(1).get();
        Movie movieDb = movieRepository.save(new Movie(null, "Movietest", "testing the movie", 2, 30, movieVariant));
        ResultActions response = mockMvc.perform(delete("/catalog-svc/v1/movies/{id}", movieDb.getId()));
        response.andDo(print()).andExpect(status().isOk());
    }
}