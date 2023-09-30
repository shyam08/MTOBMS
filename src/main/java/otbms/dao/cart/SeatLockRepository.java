package otbms.dao.cart;

import otbms.exception.SeatNotAvailableException;

import java.util.Set;

public interface SeatLockRepository {
    void blockSeat(Integer showId, Set<Integer> seats, String lockOwner) throws SeatNotAvailableException;
    void unblockSeat(Integer showId, Set<Integer> seats);
}
