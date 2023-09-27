package OTBMS.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import OTBMS.dto.payment.gateway.DebitResponse;
import OTBMS.dto.payment.gateway.VerifyResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentProcessResponse {
    private String transactionId;
    private Integer payementGtwId;
    private Integer payementOptionId;
    private String status;

    public static PaymentProcessResponse copy(VerifyResponse verifyResponse) {
        PaymentProcessResponse response = new PaymentProcessResponse();
        return response;
    }

    public static PaymentProcessResponse copy(DebitResponse debitResponse) {
        PaymentProcessResponse response = new PaymentProcessResponse();
        return response;
    }
}
