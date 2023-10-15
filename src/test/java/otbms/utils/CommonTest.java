package otbms.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CommonTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getKey() {
    }

    @Test
    void getKeyWithFiller() {
    }

    @Test
    void getCartId() {
    }

    @Test
    void getTransactionId() {
    }

    @Test
    void combineDateTime() throws ParseException {
        Date originalDateTime = new Date();
        java.sql.Date sqlDate = new java.sql.Date(originalDateTime.getTime());
        Time sqlTime = new Time(originalDateTime.getTime());
        Date finalDate = Common.combineDateTime(sqlDate, sqlTime);
        assertTrue(originalDateTime.toString().equals(finalDate.toString()));
    }
}