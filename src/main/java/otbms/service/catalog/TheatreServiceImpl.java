package otbms.service.catalog;

import jakarta.persistence.EntityNotFoundException;
import otbms.dao.catalog.City;
import otbms.dao.catalog.CityRepository;
import otbms.dao.catalog.Theatre;
import otbms.dao.catalog.TheatreRepository;
import otbms.dto.catalog.TheatreResponse;
import otbms.dto.catalog.TheatreUpsertRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class TheatreServiceImpl implements TheatreService {
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private CityRepository cityRepository;


    @Override
    public TheatreResponse saveTheatre(TheatreUpsertRequest request) throws Exception {
        Optional<City> city = cityRepository.findById(request.getCityId());
        if (city.isEmpty()) {
            throw new EntityNotFoundException("City not found");
        }
        City cityDb = city.get();
        Theatre theatreDb = theatreRepository.save(new Theatre(null, request.getName(), request.getLat(), request.getLng(), request.getContactNumber(), request.getArea(), cityDb));
        /*publishTheatres(theatreDb.getId(), theatreDb.getName(), theatreDb.getCity().getId(),
                theatreDb.getCity().getName(), theatreDb.getLat(), theatreDb.getLng(), Operation.create);*/
        return new TheatreResponse(theatreDb.getId(), theatreDb.getName(), theatreDb.getLat(), theatreDb.getLng(), theatreDb.getContactNumber(), theatreDb.getArea(), cityDb.getId());
    }

    @Override
    public TheatreResponse getTheatre(Integer id) throws Exception {
        Optional<Theatre> theatre = theatreRepository.findById(id);
        if (theatre.isEmpty()) {
            throw new EntityNotFoundException("Theatre not found");
        }
        Theatre theatreDb = theatre.get();
        return new TheatreResponse(theatreDb.getId(), theatreDb.getName(), theatreDb.getLat(), theatreDb.getLng(), theatreDb.getContactNumber(), theatreDb.getArea(), theatreDb.getCity().getId());
    }

    @Override
    public TheatreResponse updateTheatre(Integer id, TheatreUpsertRequest request) throws Exception {
        Optional<City> city = cityRepository.findById(request.getCityId());
        if (city.isEmpty()) {
            throw new EntityNotFoundException("City not found");
        }
        City cityDb = city.get();
        Optional<Theatre> theatre = theatreRepository.findById(id);
        if (theatre.isEmpty()) {
            throw new EntityNotFoundException("Theatre not found");
        }
        Theatre theatreDb = theatre.get();
        theatreDb.setArea(request.getArea());
        theatreDb.setName(request.getName());
        theatreDb.setCity(cityDb);
        theatreDb.setLat(request.getLat());
        theatreDb.setLng(request.getLng());
        theatreDb.setContactNumber(request.getContactNumber());
       /* publishTheatres(theatreDb.getId(), theatreDb.getName(), theatreDb.getCity().getId(),
                theatreDb.getCity().getName(), theatreDb.getLat(), theatreDb.getLng(), Operation.update);*/
        return new TheatreResponse(theatreDb.getId(), theatreDb.getName(), theatreDb.getLat(), theatreDb.getLng(), theatreDb.getContactNumber(), theatreDb.getArea(), theatreDb.getCity().getId());
    }

    @Override
    public void deleteTheatre(Integer id) throws Exception {
        Optional<Theatre> theatre = theatreRepository.findById(id);
        if (theatre.isEmpty()) {
            throw new EntityNotFoundException("Theatre not found");
        }
        Theatre theatreDb = theatre.get();
       /* publishTheatres(theatreDb.getId(), theatreDb.getName(), theatreDb.getCity().getId(),
                theatreDb.getCity().getName(), theatreDb.getLat(), theatreDb.getLng(), Operation.delete);*/
        theatreRepository.deleteById(id);
    }

   /* protected void publishTheatres(Integer theatreId, String theatreName, Integer cityId, String cityName, Double lat
            , Double lng, Operation operation) {
        theatresPublisherCatalogSvc.publish(theatreName, new TheatreMessage(theatreId, theatreName, cityName, cityId,
                new GeoPoint(lat, lng), operation));*/
  //  }

}
