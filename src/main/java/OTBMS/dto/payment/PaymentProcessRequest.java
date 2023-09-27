package OTBMS.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentProcessRequest {
    private String transactionId;
    private Integer payementGtwId;
    private Integer payementOptionId;
    private PaymentAction nextAction;
    private double deductedAmt;
    private Map<String,Object> metaData;
}
