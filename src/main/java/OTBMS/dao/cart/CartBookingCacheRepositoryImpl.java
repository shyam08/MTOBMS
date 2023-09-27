package OTBMS.dao.cart;

import jakarta.persistence.EntityNotFoundException;
import OTBMS.dto.cart.CartDetail;
import OTBMS.exception.SeatNotAvailableException;
import OTBMS.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class CartBookingCacheRepositoryImpl implements CartBookingCacheRepository {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Value("${cart.booking.detail.namespace}")
    private String cartDetailNamespace;
    @Value("${cart.booking.detail.ttl.ms}")
    private long cartDetailTtl;
    @Value("${cart.booking.seats.lock.namespace}")
    private String seatLockNamespace;
    @Value("${cart.booking.seats.lock.ttl.ms}")
    private long seatLockTtl;


    @Override
    public void saveSeatLock(Integer showId, Integer seatId) {
        redisTemplate.opsForValue().set(Common.getKey(seatLockNamespace, showId, seatId), "1", seatLockTtl);
    }

    @Override
    public void deleteSeatLock(Integer showId, Integer seatId) {
        redisTemplate.opsForValue().set(Common.getKey(seatLockNamespace, showId, seatId), "", 1, TimeUnit.MILLISECONDS);
    }

    @Override
    public void checkSeatsLock(Integer showId, Integer seatId) throws SeatNotAvailableException {
        String val = (String) redisTemplate.opsForValue().get(Common.getKey(seatLockNamespace, showId, seatId));
        if (val != null)
            throw new SeatNotAvailableException(String.format("Seat not available %s", seatId));
    }

    @Override
    public void saveBookingDetail(String cartId, CartDetail cartDetail) {
        redisTemplate.opsForValue().set(Common.getKey(cartDetailNamespace, cartId), cartDetail,
                cartDetailTtl, TimeUnit.MILLISECONDS);
    }

    @Override
    public void isBookingDetailExists(String cartId) {
        CartDetail cartDetail = (CartDetail) redisTemplate.opsForValue().get(Common.getKey(cartDetailNamespace, cartId));
        if (cartDetail == null)
            throw new EntityNotFoundException("Cart not exists");
    }

    @Override
    public CartDetail getBookingDetail(String cartId) {
        CartDetail cartDetail = (CartDetail) redisTemplate.opsForValue().get(Common.getKey(cartDetailNamespace, cartId));
        if (cartDetail == null)
            throw new EntityNotFoundException("Cart not exists");
        return cartDetail;
    }

    @Override
    public void deleteBookingDetail(String cartId) {
        redisTemplate.opsForValue().set(Common.getKey(cartDetailNamespace, cartId), "", 1, TimeUnit.MILLISECONDS);
    }

}
