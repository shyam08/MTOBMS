package otbms.service.search;

import otbms.dto.search.TheatreResponse;
import otbms.dto.search.TheatreSearchRequest;

import java.util.List;

public interface TheatreSearchService {
    List<TheatreResponse> theatreSearch(TheatreSearchRequest request) throws Exception;
}
