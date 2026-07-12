// Exceptions3.java
//
// You can define your own exception type: a class that extends Exception.
//
// Java has two kinds. "Unchecked" ones (RuntimeException and its children,
// like IllegalArgumentException) can be thrown anywhere. "Checked" ones
// (everything that extends plain Exception) must be announced in the method
// signature with `throws`, and the caller must catch them or announce them
// too. This has no Python equivalent; it forces you to think about failures.
//
// 1. Make InsufficientFundsException extend Exception.
// 2. Make withdraw throw it (with the message "need 50 more") when the
//    balance is too low. You will need `throws` on the method.
// 3. Make main compile: the call to withdraw must handle the exception.

import javalings.Check;

public class Exceptions3 {
    public static void main(String[] args) throws InsufficientFundsException {
        Wallet w = new Wallet(100);
        w.withdraw(30);
        Check.equals(70, w.balance, "balance after withdrawing 30");

        String message = "no exception";
        try {
            w.withdraw(120);
        } catch (InsufficientFundsException e) {
            message = e.getMessage();
        }
        Check.equals("need 50 more", message, "exception message");
        Check.equals(70, w.balance, "balance unchanged after failed withdraw");
    }
}

class InsufficientFundsException extends Exception {
    InsufficientFundsException(String message) {
        super(message);
    }
}

class Wallet {
    int balance;

    Wallet(int balance) {
        this.balance = balance;
    }

    void withdraw(int amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("need " + (amount - balance) + " more");
        }
        balance -= amount;
    }
}
