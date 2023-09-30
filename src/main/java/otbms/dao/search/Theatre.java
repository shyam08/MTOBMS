package otbms.dao.search;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "theatre")
@ToString
public class Theatre {
    @org.springframework.data.annotation.Id
    private String Id;
    @Field(name = "theatre_id", type = FieldType.Integer)
    private Integer theatreId;
    @Field(name = "theatre_name", type = FieldType.Text)
    private String theatreName;
    @Field(name = "city_name", type = FieldType.Text)
    private String cityName;
    @Field(name = "city_id", type = FieldType.Integer)
    private Integer cityId;
    @GeoPointField
    private GeoPoint latLng;
}
