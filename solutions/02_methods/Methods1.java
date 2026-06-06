// Methods1.java
//
// A Python function is called a method in Java when it lives inside a class
// (and in Java, everything lives inside a class).
//
//     def greet():                 static void greet() {
//         print("Hi")                  System.out.println("Hi");
//                                  }
//
// `void` means "returns nothing". `static` means you can call it without
// creating an object first (more on objects later).
//
// main calls a method that does not exist yet. Write it.

public class Methods1 {
    public static void main(String[] args) {
        callMe();
    }

    static void callMe() {
        System.out.println("You called me!");
    }
}
