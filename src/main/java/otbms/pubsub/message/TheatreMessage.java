package otbms.pubsub.message;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TheatreMessage {
    private Integer theatreId;
    private String theatreName;
    private String cityName;
    private Integer cityId;
    private GeoPoint latLng;
    private Operation operation;
}
