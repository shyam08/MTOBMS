package otbms.dao.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, BigInteger> {
    @Query("select pt from PaymentTransaction pt where pt.transactionId = ?1")
    Optional<PaymentTransaction> findByTransactionId(String transactionId);
}
