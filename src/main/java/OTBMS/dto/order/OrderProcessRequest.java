package OTBMS.dto.order;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import OTBMS.controller.order.OrderAction;
import OTBMS.utils.Status;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderProcessRequest {
    private BigInteger orderId;
    @NotEmpty
    private OrderAction action = OrderAction.none;
    private Integer paymentGtwId;
    private Integer payementOptionId;
    private String paymentTransactionId;
    private Status paymentStatus;
    private String reason;
}
