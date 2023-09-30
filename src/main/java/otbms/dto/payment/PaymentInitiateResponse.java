package otbms.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentInitiateResponse {
    private String transactionId;
    private Integer payementGtwId;
    private PaymentAction nextAction = PaymentAction.none;
    // send payment metadata based on PaymentActions
    private Map<String, Object> metaData;

}
