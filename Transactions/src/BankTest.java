import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    private Bank bank = new Bank();
    private Account firstAccount = new Account();
    private Account secondAccount = new Account();

    @Test
    @BeforeEach
    void setUp() {
        firstAccount.setMoney(60000);
        firstAccount.setAccNumber("Evgenii");
        secondAccount.setMoney(5000);
        secondAccount.setAccNumber("Anton");
        bank.put("Evgenii", "Anton", firstAccount, secondAccount);
    }

    @Test
    void isFraud() {
        try {
            boolean actual = bank.transfer("Evgenii", "Anton", 50001);
            boolean expected = true;
            assertEquals(expected, actual);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void transfer() {
        try {
            boolean actual = bank.transfer("Evgenii", "Anton", 1000);
            boolean expected = false;
            assertEquals(expected, actual);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void getBalance() {
        long actual = bank.getBalance(firstAccount.getAccNumber());
        long expected = 60000;
        assertEquals(expected, actual);
    }

    @Test
    void getSumAllAccounts() {
        Map actual = bank.getSumAllAccounts();
        Map<String, Long> expected = new HashMap<>();
        expected.put("Evgenii", 60000L);
        expected.put("Anton", 5000L);
        assertEquals(expected, actual);
    }
}