package otbms.dao.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatAllocationStatusRepository extends JpaRepository<SeatAllocationStatus, SeatAllocationStatusId> {
}
