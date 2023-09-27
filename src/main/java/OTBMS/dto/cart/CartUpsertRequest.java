package OTBMS.dto.cart;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CartUpsertRequest {
    @NotEmpty
    private String sessionId;
    @Positive
    private Integer cityId;
    @Positive
    private Integer theatreId;
    @Positive
    private Integer audiId;
    @Positive
    private Integer showId;
    @NotEmpty
    private Set<Integer> seats;
}
