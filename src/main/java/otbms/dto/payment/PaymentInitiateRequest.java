package otbms.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentInitiateRequest {
    private String transactionId;
    private Integer payementGtwId;
    private Integer payementOptionId;
}
