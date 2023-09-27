package OTBMS.service.cart;

import OTBMS.clients.CatalogServiceApiClient;
import OTBMS.dto.cart.CartBillDetail;
import OTBMS.dto.cart.CartDetail;
import OTBMS.exception.CartVerificationException;
import OTBMS.exception.SeatNotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CartOperations {
    @Autowired
    private CatalogServiceApiClient catalogServiceApiClient;


    void verifyCart(CartDetail cartDetail) throws CartVerificationException, SeatNotAvailableException {
        //verify cart items
        // check promos
        //check seats capacity
        checkSeatCapacity(cartDetail.getShowId(), cartDetail.getSeats());
    }

    private void checkSeatCapacity(Integer showId, Set<Integer> seats) throws SeatNotAvailableException {
        Integer freeSeatsCount = catalogServiceApiClient.getFreeSeatsCount(showId);
        if (freeSeatsCount < seats.size())
            throw new SeatNotAvailableException(String.format("Sufficient seats not available %s", freeSeatsCount));
    }

    void computeBill(CartDetail cartDetail) {
        //1. compute discounts, taxes, final bill amount
        cartDetail.setCartBillDetail(new CartBillDetail());
    }

}
