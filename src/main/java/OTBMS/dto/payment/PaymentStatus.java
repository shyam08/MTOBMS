package OTBMS.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentStatus {
    private String transactionId;
    private Integer status;
    private String reason;
    private Integer retry;
    private Date created;
    private Date updated;
}
