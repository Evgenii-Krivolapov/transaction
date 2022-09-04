import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bank {
    private Map<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();
    private volatile boolean fraud;

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
            Thread.sleep(1000);
            fraud = random.nextBoolean();
        return fraud;
    }

    public boolean transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        if (accounts.get(fromAccountNum).getMoney() < amount)
            throw new RuntimeException();

        if (amount > 50000) {
            isFraud(fromAccountNum, toAccountNum, amount);
            if (fraud == true) {
                System.out.println("Ваш счёт заблокирован");
                return true;
            } else {
                synchronized (fromAccountNum) {
                    synchronized (toAccountNum) {
                        translationFrom(fromAccountNum, amount);
                        translationTo(toAccountNum, amount);
                        System.out.println("Успешный перевод");
                        return false;
                    }
                }

            }
        } else {
            synchronized (fromAccountNum) {
                synchronized (toAccountNum) {
                    translationFrom(fromAccountNum, amount);
                    translationTo(toAccountNum, amount);
                    System.out.println("Успешный перевод");
                    return false;
                }
            }
        }
    }

    public void translationFrom(String fromAccountNum, long amount) {
        accounts.get(fromAccountNum).setMoney(accounts.get(fromAccountNum).getMoney() - amount);
    }

    public void translationTo(String toAccountNum, long amount) {
        accounts.get(toAccountNum).setMoney(accounts.get(toAccountNum).getMoney() + amount);
    }

    public void put(String nameFrom, String nameTo, Account accountFrom, Account accountTo) {
        accounts.put(nameFrom, accountFrom);
        accounts.put(nameTo, accountTo);
    }


    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public Map getSumAllAccounts() {
        Map<String, Long> allAccounts = new HashMap<>();
        for (Map.Entry<String, Account> entry : accounts.entrySet()) {
            allAccounts.put(entry.getKey(), entry.getValue().getMoney());
        }
        return allAccounts;
    }
}