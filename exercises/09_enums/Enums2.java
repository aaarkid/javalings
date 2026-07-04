// Enums2.java
//
// Enum values can carry data and have methods, because an enum is a class.
//
//     enum Planet {
//         EARTH(5.97), MARS(0.642);
//
//         final double mass;
//
//         Planet(double mass) { this.mass = mass; }
//     }
//
// Give each Coin a value in cents and implement `total`.

// I AM NOT DONE

import javalings.Check;

public class Enums2 {
    public static void main(String[] args) {
        Check.equals(25, Coin.QUARTER.cents, "a quarter is 25 cents");
        Check.equals(41, total(new Coin[]{Coin.QUARTER, Coin.DIME, Coin.NICKEL, Coin.PENNY}), "25 + 10 + 5 + 1");
        Check.equals(0, total(new Coin[0]), "no coins");
    }

    static int total(Coin[] coins) {
        return 0;
    }
}

enum Coin {
    PENNY, NICKEL, DIME, QUARTER;

    final int cents;
}
