package otbms.dto.order;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import otbms.dto.payment.PaymentAction;

import java.math.BigInteger;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderProcessResponse {
    private BigInteger orderId;
    private String paymentTransactionId;
    private Integer payementGtwId;
    @NotEmpty
    private PaymentAction nextAction;
    // send payment metadata based on PaymentActions
    private Map<String, Object> metaData;
}
