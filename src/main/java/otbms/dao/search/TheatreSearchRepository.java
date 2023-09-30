package otbms.dao.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreSearchRepository extends ElasticsearchRepository<Theatre, String> {

    Page<Theatre> findByCityName(String name, Pageable pageable);

    @Query("{\"query\": {\"bool\": {\"must\": {\"match_all\": {}},\"filter\": {\"geo_distance\": {\"distance\": " +
            "\"?2\",\"latLng\": {\"lat\": ?0,\"lon\": ?1}}}}}}")
    Page<Theatre> findNearBy(Double lat, Double lng, String distance, Pageable pageable);
}
