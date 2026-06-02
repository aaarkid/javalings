// Variables4.java
//
// `final` makes a variable a constant: it can be given a value once and never
// again. Python has no real constants, it just uses UPPER_CASE names as a
// promise. Java enforces it.
//
// The program should print 3 and then 4. Make it compile by changing as little
// as possible.

public class Variables4 {
    public static void main(String[] args) {
        int number = 3;
        System.out.println("Number is " + number);
        number = 4;
        System.out.println("Number is " + number);
    }
}
