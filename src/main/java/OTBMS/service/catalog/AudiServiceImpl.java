package OTBMS.service.catalog;

import jakarta.persistence.EntityNotFoundException;
import OTBMS.dao.catalog.Audi;
import OTBMS.dao.catalog.AudiRepository;
import OTBMS.dao.catalog.Theatre;
import OTBMS.dao.catalog.TheatreRepository;
import OTBMS.dto.catalog.AudiResponse;
import OTBMS.dto.catalog.AudiUpsertRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class AudiServiceImpl implements AudiService {
    @Autowired
    private AudiRepository audiRepository;
    @Autowired
    private TheatreRepository theatreRepository;

    @Override
    public AudiResponse saveAudi(AudiUpsertRequest request) throws Exception {
        Optional<Theatre> theatre = theatreRepository.findById(request.getTheatreId());
        if (theatre.isEmpty()) {
            throw new EntityNotFoundException("Theatre not found");
        }
        Theatre theatreDb = theatre.get();
        Audi audiSaved = audiRepository.save(new Audi(null, request.getName(), request.getFrontSeats(),
                request.getMiddleSeats(), request.getBackSeats(), request.getTotalSeats(), theatreDb));

        return new AudiResponse(audiSaved.getId(), audiSaved.getName(), audiSaved.getFrontSeats(),
                audiSaved.getMiddleSeats(), audiSaved.getBackSeats(), audiSaved.getTotalSeats(),
                audiSaved.getTheatre().getId());
    }

    @Override
    public AudiResponse getAudi(Integer id) throws Exception {
        Optional<Audi> audi = audiRepository.findById(id);
        if (audi.isEmpty()) {
            throw new EntityNotFoundException("Audi not found");
        }
        Audi audiDb = audi.get();
        return new AudiResponse(audiDb.getId(), audiDb.getName(), audiDb.getFrontSeats(),
                audiDb.getMiddleSeats(), audiDb.getBackSeats(), audiDb.getTotalSeats(),
                audiDb.getTheatre().getId());
    }

    @Override
    public AudiResponse updateAudi(Integer id, AudiUpsertRequest request) throws Exception {
        Optional<Audi> audi = audiRepository.findById(id);
        if (audi.isEmpty()) {
            throw new EntityNotFoundException("Audi not found");
        }
        Audi audiDb = audi.get();
        Optional<Theatre> theatre = theatreRepository.findById(request.getTheatreId());
        if (theatre.isEmpty()) {
            throw new EntityNotFoundException("Theatre not found");
        }
        Theatre theatreDb = theatre.get();
        audiDb.setName(request.getName());
        audiDb.setTheatre(theatreDb);
        audiDb.setFrontSeats(request.getFrontSeats());
        audiDb.setBackSeats(request.getBackSeats());
        audiDb.setMiddleSeats(request.getMiddleSeats());
        audiDb.setTotalSeats(request.getTotalSeats());
        Audi audiSaved = audiRepository.save(audiDb);

        return new AudiResponse(audiSaved.getId(), audiSaved.getName(), audiSaved.getFrontSeats(),
                audiSaved.getMiddleSeats(), audiSaved.getBackSeats(), audiSaved.getTotalSeats(),
                audiSaved.getTheatre().getId());
    }

    @Override
    public void deleteAudi(Integer id) throws Exception {
        theatreRepository.deleteById(id);
    }
}
