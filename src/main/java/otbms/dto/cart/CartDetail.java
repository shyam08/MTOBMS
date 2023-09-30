package otbms.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import otbms.utils.Common;

import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CartDetail implements Serializable {
    private String cartId;
    private String sessionId;
    private Integer cityId;
    private Integer theatreId;
    private Integer audiId;
    private Integer showId;
    private Set<Integer> seats;
    private CartBillDetail cartBillDetail;
    private UserDetail userDetail;

    public static CartDetail copy(CartUpsertRequest obj) {
        CartDetail detail = new CartDetail();
        detail.setCartId(Common.getCartId());
        detail.setSessionId(obj.getSessionId());
        detail.setCityId(obj.getCityId());
        detail.setTheatreId(obj.getTheatreId());
        detail.setAudiId(obj.getAudiId());
        detail.setShowId(obj.getShowId());
        detail.setSeats(obj.getSeats());
        return detail;
    }

    public static CartDetail copy(String cartId, CartUpsertRequest obj) {
        CartDetail detail = new CartDetail();
        detail.setCartId(cartId);
        detail.setSessionId(obj.getSessionId());
        detail.setCityId(obj.getCityId());
        detail.setTheatreId(obj.getTheatreId());
        detail.setAudiId(obj.getAudiId());
        detail.setShowId(obj.getShowId());
        detail.setSeats(obj.getSeats());
        return detail;
    }
}
