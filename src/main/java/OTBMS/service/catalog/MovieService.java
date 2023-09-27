package OTBMS.service.catalog;

import OTBMS.dto.catalog.MovieResponse;
import OTBMS.dto.catalog.MovieUpsertRequest;

public interface MovieService {
    MovieResponse saveMovie(MovieUpsertRequest request) throws Exception;

    MovieResponse getMovie(Integer id) throws Exception;

    MovieResponse updateMovie(Integer id, MovieUpsertRequest request) throws Exception;

    void deleteMovie(Integer id) throws Exception;
}
