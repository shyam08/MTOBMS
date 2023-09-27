package OTBMS.dao.cart;

import OTBMS.exception.SeatNotAvailableException;

import java.util.Set;

public interface SeatLockRepository {
    void blockSeat(Integer showId, Set<Integer> seats, String lockOwner) throws SeatNotAvailableException;
    void unblockSeat(Integer showId, Set<Integer> seats);
}
