package otbms.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaytmCallbackRequest {
    private Integer payementGtwId;
    private Integer payementOptionId;
}
