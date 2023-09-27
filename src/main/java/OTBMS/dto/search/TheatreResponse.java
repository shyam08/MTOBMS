package OTBMS.dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class TheatreResponse {
    private Integer theatreId;
    private String theatreName;
    private Integer cityId;
    private String cityName;
    private GeoPoint latLng;
}
