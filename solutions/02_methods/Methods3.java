// Methods3.java
//
// A method that gives a value back needs a return type instead of `void`.
//
//     def double(x):               static int twice(int x) {
//         return x * 2                 return x * 2;
//                                  }
//
// Fix `square` so that it compiles.

import javalings.Check;

public class Methods3 {
    public static void main(String[] args) {
        int answer = square(3);
        System.out.println("The square of 3 is " + answer);
        Check.equals(9, answer);
        Check.equals(49, square(7));
    }

    static int square(int num) {
        return num * num;
    }
}
