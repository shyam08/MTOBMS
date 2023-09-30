package otbms.dao.payment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gw_transaction")
public class PaymentTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;
    @OneToOne()
    @JoinColumn(name = "payment_gateway_id", referencedColumnName = "id")
    private PaymentGtw paymentGtw;
    @OneToOne()
    @JoinColumn(name = "payment_option_id", referencedColumnName = "id")
    private PaymentGtw paymentOptionId;
    @Column(name = "order_payment_id")
    private String transactionId;
    @Column(name = "amt")
    private Double amt;
    @Column(name = "request")
    private String request;
    @Column(name = "response.yaml")
    private String response;
    @Column(name = "retry")
    private Integer retry;
    @Column(name = "reason")
    private String reason;
    /*0-pending,1-processing, 2-success, 3-failed*/
    @Column(name = "status")
    private Integer status;
    @Column(name = "created")
    private Date created;
    @Column(name = "updated")
    private Date updated;
}
