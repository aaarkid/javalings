// Classes2.java
//
// Fields are usually made `private`, so that only the class itself can touch
// them. Other code goes through methods. This keeps the object in a sane
// state: nobody can set a bank balance to "banana" behind your back.
//
// Complete the BankAccount class:
//   - deposit(amount) adds money
//   - withdraw(amount) removes money, but only if there is enough. It returns
//     true if it worked and false if not.
//   - getBalance() returns the balance

// I AM NOT DONE

import javalings.Check;

public class Classes2 {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount("Ada");
        acc.deposit(100);
        Check.equals(100, acc.getBalance(), "balance after deposit");
        Check.isTrue(acc.withdraw(30), "withdrawing 30 works");
        Check.equals(70, acc.getBalance(), "balance after withdraw");
        Check.isTrue(!acc.withdraw(500), "withdrawing 500 is refused");
        Check.equals(70, acc.getBalance(), "balance unchanged after refused withdraw");
        Check.equals("Ada", acc.getOwner(), "owner");
    }
}

class BankAccount {
    private String owner;
    private int balance;

    BankAccount(String owner) {
        this.owner = owner;
    }

    String getOwner() {
        return owner;
    }

    // your methods here
}
