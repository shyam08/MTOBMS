package OTBMS.dao.cart;

import lombok.extern.slf4j.Slf4j;
import OTBMS.exception.SeatNotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Repository
@Transactional(isolation = Isolation.SERIALIZABLE)
@Slf4j
public class SeatLockRepositoryImpl implements SeatLockRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CartBookingCacheRepository cartBookingCache;
    private Long totalLocks = null;

    @Override
    public void blockSeat(Integer showId, Set<Integer> seats, String lockOwner) throws SeatNotAvailableException {
        Set<Integer> seatAllocated = new HashSet<>(seats.size());
        Integer seatNotAvailable = null;
        for (Integer seatId : seats) {
            cartBookingCache.checkSeatsLock(showId, seatId);
            BigInteger lockId = getLockId(showId, seatId);
            boolean seatLocked = true;
            StringBuilder queryBuilder = new StringBuilder("insert into ").append(getTableName(showId, seatId, lockId)).
                    append(" (id, insert_time, lock_owner) ").append("values (?,?,?)");
            try {
                jdbcTemplate.update(queryBuilder.toString(), new Object[]{lockId,
                        new Time(System.currentTimeMillis()), lockOwner});
            } catch (DuplicateKeyException dupExp) {
                seatNotAvailable = seatId;
                break;
            }
            cartBookingCache.saveSeatLock(showId, seatId);
            seatAllocated.add(seatId);
        }

        if (seatNotAvailable != null) {
            unblockSeat(showId, seats);
            throw new SeatNotAvailableException(String.format("Seat not available %s", seatNotAvailable));
        }
    }

    @Override
    public void unblockSeat(Integer showId, Set<Integer> seats) {
        for (Integer seatId : seats) {
            BigInteger lockId = getLockId(showId, seatId);
            boolean seatLocked = true;
            StringBuilder queryBuilder = new StringBuilder("delete from ").append(getTableName(showId, seatId, lockId)).
                    append(" where id = ").append(lockId);
            jdbcTemplate.execute(queryBuilder.toString());
            cartBookingCache.deleteSeatLock(showId, seatId);
        }
    }

    /**
     * Generate lockId using showId, seatId and filler i.e. 000. For example showId = 1672 and seatId=23
     * then lockId=167200023
     *
     * @param showId
     * @param seatId
     * @return
     */
    protected BigInteger getLockId(Integer showId, Integer seatId) {
        BigInteger lockId = new BigInteger(new StringBuilder().append(showId).append("000").append(seatId).toString());
        return lockId;
    }

    protected String getTableName(Integer showId, Integer seatId, BigInteger lockId) {
        if (totalLocks == null) {
            totalLocks = Long.valueOf(jdbcTemplate.queryForObject("select locks_availble from seat_lock_metadata",
                    Integer.class));
        }
        return new StringBuilder("seat_lock_").append(lockId.mod(BigInteger.valueOf(totalLocks))).toString();
    }
}
