package otbms.service.catalog;

import otbms.dto.catalog.TheatreResponse;
import otbms.dto.catalog.TheatreUpsertRequest;

public interface TheatreService {
    TheatreResponse saveTheatre(TheatreUpsertRequest request) throws Exception;

    TheatreResponse getTheatre(Integer id) throws Exception;

    TheatreResponse updateTheatre(Integer id, TheatreUpsertRequest request) throws Exception;

    void deleteTheatre(Integer id) throws Exception;
}
