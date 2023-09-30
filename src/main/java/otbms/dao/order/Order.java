package otbms.dao.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;

    @OneToOne
    @JoinColumn(name = "ordr_dtl_id", referencedColumnName = "id")
    private OrderDetail orderDetail;

    @Column(name = "next_action")
    private Integer nextAction;

    @Column(name = "order_payment_id")
    private String orderPaymentId;

    /*0-pending,1-processing, 2-success, 3-failed*/
    @Column(name = "status")
    private Integer status;

    @Column(name = "reason")
    private String reason;

    @Column(name = "created")
    private String created;

    @Column(name = "updated")
    private String updated;

}
