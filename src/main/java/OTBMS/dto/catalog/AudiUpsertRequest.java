package OTBMS.dto.catalog;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AudiUpsertRequest {
    @NotEmpty
    private String name;

    private Integer frontSeats;

    private Integer middleSeats;

    private Integer backSeats;
    @Positive
    private Integer totalSeats;
    @Positive
    private Integer theatreId;
}
