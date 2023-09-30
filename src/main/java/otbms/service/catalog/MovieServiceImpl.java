package otbms.service.catalog;

import jakarta.persistence.EntityNotFoundException;
import otbms.dao.catalog.Movie;
import otbms.dao.catalog.MovieRepository;
import otbms.dao.catalog.MovieVariant;
import otbms.dao.catalog.MovieVariantRepository;
import otbms.dto.catalog.MovieResponse;
import otbms.dto.catalog.MovieUpsertRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieVariantRepository movieVariantRepository;

    @Override
    public MovieResponse saveMovie(MovieUpsertRequest request) throws Exception {
        Optional<MovieVariant> movieVariant = movieVariantRepository.findById(request.getVariantId());
        if (movieVariant.isEmpty()) {
            throw new EntityNotFoundException("MovieVariant not found");
        }
        MovieVariant movieVariantDb = movieVariant.get();
        Movie movieDb = movieRepository.save(new Movie(null, request.getName(), request.getDescription(),
                request.getDurationHour(), request.getDurationMint(), movieVariantDb));
        return new MovieResponse(movieDb.getId(), movieDb.getName(), movieDb.getDescription(),
                movieDb.getDurationHour(), movieDb.getDurationMint(), movieVariantDb.getId());
    }

    @Override
    public MovieResponse getMovie(Integer id) throws Exception {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty()) {
            throw new EntityNotFoundException("Movie not found");
        }
        Movie movieDb = movie.get();
        return new MovieResponse(movieDb.getId(), movieDb.getName(), movieDb.getDescription(),
                movieDb.getDurationHour(), movieDb.getDurationMint(), movieDb.getVariant().getId());
    }

    @Override
    public MovieResponse updateMovie(Integer id, MovieUpsertRequest request) throws Exception {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty()) {
            throw new EntityNotFoundException("Movie not found");
        }
        Movie movieDb = movie.get();
        Optional<MovieVariant> movieVariant = movieVariantRepository.findById(request.getVariantId());
        if (movieVariant.isEmpty()) {
            throw new EntityNotFoundException("MovieVariant not found");
        }
        MovieVariant movieVariantDb = movieVariant.get();
        movieDb.setName(request.getName());
        movieDb.setDescription(request.getDescription());
        movieDb.setDurationHour(request.getDurationHour());
        movieDb.setDurationMint(request.getDurationMint());
        movieDb.setVariant(movieVariantDb);
        Movie movieSaved = movieRepository.save(movieDb);
        return new MovieResponse(movieSaved.getId(), movieSaved.getName(), movieSaved.getDescription(),
                movieSaved.getDurationHour(), movieSaved.getDurationMint(), movieSaved.getVariant().getId());
    }

    @Override
    public void deleteMovie(Integer id) throws Exception {
        movieRepository.deleteById(id);
    }
}
