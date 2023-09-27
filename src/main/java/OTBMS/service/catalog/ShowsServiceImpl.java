package OTBMS.service.catalog;

import jakarta.persistence.EntityNotFoundException;
import OTBMS.dao.catalog.*;
import OTBMS.dto.catalog.ShowResponse;
import OTBMS.dto.catalog.ShowUpsertRequest;
import OTBMS.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class ShowsServiceImpl implements ShowsService {

    @Autowired
    private MovieShowsRepository showsRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private AudiRepository audiRepository;

    @Override
    public ShowResponse saveShow(ShowUpsertRequest request) throws Exception {
        Optional<Movie> movie = movieRepository.findById(request.getMovieId());
        if (movie.isEmpty()) {
            throw new EntityNotFoundException("Movie not found");
        }
        Movie movieDb = movie.get();
        Optional<Audi> audi = audiRepository.findById(request.getAudiId());
        if (audi.isEmpty()) {
            throw new EntityNotFoundException("Audi not found");
        }
        Audi audiDb = audi.get();
        MovieShows movieShowDb = showsRepository.save(new MovieShows(null, movieDb, audiDb, request.getBlockedSeats(),
                request.getBlockedSeats(), new Time(request.getShowDateTime().getTime()),
                new Date(request.getShowDateTime().getTime())));

        return new ShowResponse(movieShowDb.getId(), movieShowDb.getMovie().getId(), movieShowDb.getAudi().getId(),
                movieShowDb.getBlockedSeats(), movieShowDb.getBookedSeats(),
                Common.combineDateTime(movieShowDb.getShowDate(), movieShowDb.getShowTime()));
    }

    @Override
    public ShowResponse getShow(Integer id) throws Exception {
        Optional<MovieShows> movieShow = showsRepository.findById(id);
        if (movieShow.isEmpty()) {
            throw new EntityNotFoundException("Show not found");
        }
        MovieShows movieShowDb = movieShow.get();
        return new ShowResponse(movieShowDb.getId(), movieShowDb.getMovie().getId(), movieShowDb.getAudi().getId(),
                movieShowDb.getBlockedSeats(), movieShowDb.getBookedSeats(),
                Common.combineDateTime(movieShowDb.getShowDate(), movieShowDb.getShowTime()));
    }

    @Override
    public ShowResponse updateShow(Integer id, ShowUpsertRequest request) throws Exception {
        Optional<MovieShows> movieShow = showsRepository.findById(id);
        if (movieShow.isEmpty()) {
            throw new EntityNotFoundException("Show not found");
        }
        MovieShows movieShowDb = movieShow.get();
        Optional<Movie> movie = movieRepository.findById(request.getMovieId());
        if (movie.isEmpty()) {
            throw new EntityNotFoundException("Movie not found");
        }
        Movie movieDb = movie.get();
        Optional<Audi> audi = audiRepository.findById(request.getAudiId());
        if (audi.isEmpty()) {
            throw new EntityNotFoundException("Audi not found");
        }
        Audi audiDb = audi.get();

        movieShowDb.setShowDate(new Date(request.getShowDateTime().getTime()));
        movieShowDb.setShowTime(new Time(request.getShowDateTime().getTime()));
        movieShowDb.setMovie(movieDb);
        movieShowDb.setAudi(audiDb);
        movieShowDb.setBookedSeats(movieShowDb.getBookedSeats() + request.getBookedSeats());
        movieShowDb.setBlockedSeats(movieShowDb.getBlockedSeats() + request.getBlockedSeats());
        return new ShowResponse(movieShowDb.getId(), movieShowDb.getMovie().getId(), movieShowDb.getAudi().getId(),
                movieShowDb.getBlockedSeats(), movieShowDb.getBookedSeats(),
                Common.combineDateTime(movieShowDb.getShowDate(), movieShowDb.getShowTime()));
    }

    @Override
    public void deleteShow(Integer id) throws Exception {
        showsRepository.deleteById(id);
    }

    @Override
    public Integer getFreeSeatsCount(Integer id) throws Exception {
        return showsRepository.getFreeSeatsCount(id);
    }
}
