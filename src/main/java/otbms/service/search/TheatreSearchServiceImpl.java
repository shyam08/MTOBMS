package otbms.service.search;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TheatreSearchServiceImpl  {

    @Value("${index.theatre.geo.distance}")
    String geoDistance;


    /*@Override
    public List<TheatreResponse> theatreSearch(TheatreSearchRequest request) throws Exception {
		return switch (request.getFilterType()) {
			case city -> searchByCity(request);
			case geo -> searchByGeo(request);
			default ->
					throw new IllegalArgumentException(String.format("invalid filterType %s", request.getFilterType()));
		};

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
    */
}
