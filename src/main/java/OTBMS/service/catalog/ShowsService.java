package OTBMS.service.catalog;

import OTBMS.dto.catalog.ShowResponse;
import OTBMS.dto.catalog.ShowUpsertRequest;

public interface ShowsService {
    ShowResponse saveShow(ShowUpsertRequest request) throws Exception;

    ShowResponse getShow(Integer id) throws Exception;

    ShowResponse updateShow(Integer id, ShowUpsertRequest request) throws Exception;

    void deleteShow(Integer id) throws Exception;

    Integer getFreeSeatsCount(Integer id) throws Exception;
}
