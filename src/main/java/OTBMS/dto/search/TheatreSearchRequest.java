package OTBMS.dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import OTBMS.controller.search.TheatreSearchFilterType;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class TheatreSearchRequest {
    private TheatreSearchFilterType filterType;
    private String city;
    private Double lat;
    private Double lng;
    private Integer page;
    private Integer pageSize;
}
