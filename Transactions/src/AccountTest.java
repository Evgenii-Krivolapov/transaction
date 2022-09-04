import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private Account account = new Account();

    @Test
    void setMoney() {
        account.setMoney(1000);
        long actual = account.getMoney();
        long expected = 1000L;
        assertEquals(expected,actual);
    }

    @Test
    void setAccNumber() {
        account.setAccNumber("Evgenii");
        String actual = account.getAccNumber();
        String expected = "Evgenii";
        assertEquals(expected,actual);
    }
}