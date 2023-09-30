package otbms.dao.catalog;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "audi")
public class Audi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "front_seats")
    private Integer frontSeats;

    @Column(name = "middle_seats")
    private Integer middleSeats;

    @Column(name = "back_seats")
    private Integer backSeats;

    @Column(name = "total_seats")
    private Integer totalSeats;

    @OneToOne
    @JoinColumn(name = "theatr_id", referencedColumnName = "id")
    private Theatre theatre;

}
