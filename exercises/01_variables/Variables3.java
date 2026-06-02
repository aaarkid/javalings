// Variables3.java
//
// A variable can change its value, but not its type.
// Both lines that assign to `number` are fine: one of the other lines is not.
// Fix it without deleting any of the println calls.

// I AM NOT DONE

public class Variables3 {
    public static void main(String[] args) {
        int number = 3;
        System.out.println("Number is " + number);
        number = "three";
        System.out.println("Number is " + number);
        number = 4;
        System.out.println("Number is " + number);
    }
}
