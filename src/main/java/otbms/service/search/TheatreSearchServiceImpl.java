package otbms.service.search;

import otbms.dao.search.Theatre;
import otbms.dao.search.TheatreSearchRepository;
import otbms.dto.search.TheatreResponse;
import otbms.dto.search.TheatreSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;
import java.util.List;

@Service
public class TheatreSearchServiceImpl implements TheatreSearchService {

    @Value("${index.theatre.geo.distance}")
    String geoDistance;
    @Autowired
    private TheatreSearchRepository theatreSearchRepository;

    @Override
    public List<TheatreResponse> theatreSearch(TheatreSearchRequest request) throws Exception {
        switch (request.getFilterType()) {
            case city:
                return searchByCity(request);
            case geo:
                return searchByGeo(request);
            default:
                throw new IllegalArgumentException(String.format("invalid filterType %s", request.getFilterType()));
        }

    }

    private List<TheatreResponse> searchByGeo(TheatreSearchRequest request) {
        Page<Theatre> pages = theatreSearchRepository.findNearBy(request.getLat(), request.getLng(), geoDistance,
                PageRequest.of(request.getPage(),
                        request.getPageSize()));
        if (pages.isEmpty())
            return Collections.emptyList();
        List<TheatreResponse> theatreResponse = pages.map(r -> new TheatreResponse(r.getTheatreId(), r.getTheatreName(), r.getCityId()
                , r.getCityName(), r.getLatLng())).stream().toList();
        return theatreResponse;
    }

    private List<TheatreResponse> searchByCity(TheatreSearchRequest request) {
        Page<Theatre> pages = theatreSearchRepository.findByCityName(request.getCity(), PageRequest.of(request.getPage(),
                request.getPageSize()));
        if (pages.isEmpty())
            return Collections.emptyList();
        List<TheatreResponse> theatreResponse = pages.map(r -> new TheatreResponse(r.getTheatreId(), r.getTheatreName(), r.getCityId()
                , r.getCityName(), r.getLatLng())).stream().toList();
        return theatreResponse;
    }
}
