package OTBMS.service.catalog;

import OTBMS.dto.catalog.AudiResponse;
import OTBMS.dto.catalog.AudiUpsertRequest;

public interface AudiService {
    AudiResponse saveAudi(AudiUpsertRequest request) throws Exception;

    AudiResponse getAudi(Integer id) throws Exception;

    AudiResponse updateAudi(Integer id, AudiUpsertRequest request) throws Exception;

    void deleteAudi(Integer id) throws Exception;
}
