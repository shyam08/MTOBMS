package otbms.dto.catalog;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class TheatreUpsertRequest {
    @NotEmpty
    private String name;

    private Double lat;

    private Double lng;
    @Positive
    private Long contactNumber;

    private String area;
    @Positive
    private Integer cityId;
}
