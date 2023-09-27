package OTBMS.dao.cart;

import OTBMS.dto.cart.CartDetail;
import OTBMS.exception.SeatNotAvailableException;

public interface CartBookingCacheRepository {

    void saveSeatLock(Integer showId, Integer seatId);

    void deleteSeatLock(Integer showId, Integer seatId);

    void checkSeatsLock(Integer showId, Integer seatId) throws SeatNotAvailableException;

    void saveBookingDetail(String cartId, CartDetail cartDetail);

    void isBookingDetailExists(String cartId);

    CartDetail getBookingDetail(String cartId);

    void deleteBookingDetail(String cartId);
}
