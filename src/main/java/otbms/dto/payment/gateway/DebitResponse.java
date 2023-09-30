package otbms.dto.payment.gateway;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DebitResponse {
    private String transactionId;
    private double amount;
    private String status;
}
