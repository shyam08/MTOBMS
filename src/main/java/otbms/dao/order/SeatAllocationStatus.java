package otbms.dao.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "seats_allocation_status")
@IdClass(SeatAllocationStatusId.class)
public class SeatAllocationStatus {
    @Id
    @Column(name = "show_id")
    private Integer showId;

    @Id
    @Column(name = "seat_id")
    private Integer seatId;

    @Column(name = "status")
    private Integer status;
}
