# BankAccount – 3. feladat

Egyszerű bankszámla modell Java nyelven, JUnit 5 egységtesztekkel.

## Funkciók

- `deposit(double amount)` – befizetés
- `withdraw(double amount)` – pénzfelvétel
- `getBalance()` – aktuális egyenleg lekérdezése

Érvénytelen művelet (nem pozitív összeg, vagy fedezet nélküli felvétel) esetén
`IllegalArgumentException` keletkezik, az egyenleg pedig nem változik.

## Követelmények

- JDK 17 vagy újabb
- Apache Maven 3.6+

## Futtatás és tesztelés

A projekt letöltése után a gyökérkönyvtárban:

```bash
mvn test
```

A teszteket a `src/test/java/hu/bank/BankAccountTest.java` tartalmazza.
