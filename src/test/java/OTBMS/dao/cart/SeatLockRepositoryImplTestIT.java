package OTBMS.dao.cart;

import com.google.common.collect.Sets;
import OTBMS.exception.SeatNotAvailableException;
import OTBMS.utils.Common;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
class SeatLockRepositoryImplTestIT {

    @Autowired
    private SeatLockRepository seatLockRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void blockSeat() throws SeatNotAvailableException {
        Integer showId = 1;
        String lockOwner = Common.getCartId();
        Set<Integer> seats = Sets.newHashSet(23, 24, 25);
        seatLockRepository.blockSeat(showId, seats, lockOwner);
    }

    @Test
    void unblockSeat() {
        Integer showId = 1;
        Set<Integer> seats = Sets.newHashSet(23, 24, 25);
        seatLockRepository.unblockSeat(showId, seats);
    }

    @Test
    void getLockId() {
    }

    @Test
    void getTableName() {
    }
}