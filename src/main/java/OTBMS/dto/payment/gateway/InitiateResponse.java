package OTBMS.dto.payment.gateway;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InitiateResponse {
    private Integer payementGtwId;
    private Integer payementOptionId;
}
