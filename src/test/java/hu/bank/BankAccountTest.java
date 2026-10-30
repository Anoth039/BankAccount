package hu.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private static final double DELTA = 0.0001;
    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount();
    }

    // 1. Új számla egyenlege 0
    @Test
    void newAccountHasZeroBalance() {
        assertEquals(0, account.getBalance(), DELTA);
    }

    // 2. deposit(1000) után az egyenleg 1000
    @Test
    void depositIncreasesBalance() {
        account.deposit(1000);
        assertEquals(1000, account.getBalance(), DELTA);
    }

    // 3. Több befizetés összeadódik
    @Test
    void multipleDepositsAddUp() {
        account.deposit(1000);
        account.deposit(500);
        account.deposit(250.5);
        assertEquals(1750.5, account.getBalance(), DELTA);
    }

    // 4. Befizetés után a withdraw csökkenti az egyenleget
    @Test
    void withdrawDecreasesBalance() {
        account.deposit(1000);
        account.withdraw(400);
        assertEquals(600, account.getBalance(), DELTA);
    }

    // 5. A teljes egyenleg felvehető
    @Test
    void fullBalanceCanBeWithdrawn() {
        account.deposit(1000);
        account.withdraw(1000);
        assertEquals(0, account.getBalance(), DELTA);
    }

    // 6. Túl nagy összeg felvétele kivételt dob
    @Test
    void withdrawMoreThanBalanceThrows() {
        account.deposit(1000);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(1000.01));
        assertEquals(1000, account.getBalance(), DELTA);
    }

    // 7. Negatív befizetés kivételt dob
    @Test
    void negativeDepositThrows() {
        account.deposit(1000);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-100));
        assertEquals(1000, account.getBalance(), DELTA);
    }

    // 8. 0 összegű befizetés kivételt dob
    @Test
    void zeroDepositThrows() {
        account.deposit(1000);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
        assertEquals(1000, account.getBalance(), DELTA);
    }

    // 9. Negatív pénzfelvétel kivételt dob
    @Test
    void negativeWithdrawThrows() {
        account.deposit(1000);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-50));
        assertEquals(1000, account.getBalance(), DELTA);
    }

    // 10. 0 összegű pénzfelvétel kivételt dob
    @Test
    void zeroWithdrawThrows() {
        account.deposit(1000);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0));
        assertEquals(1000, account.getBalance(), DELTA);
    }

    // Extra: üres számlán sikertelen felvétel után is 0 az egyenleg
    @Test
    void failedWithdrawOnEmptyAccountKeepsZeroBalance() {
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(1));
        assertEquals(0, account.getBalance(), DELTA);
    }
}