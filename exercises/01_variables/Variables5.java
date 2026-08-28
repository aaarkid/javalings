// Variables5.java
//
// Here are the types you will use most:
//
//     int      whole numbers            int age = 13;
//     double   decimal numbers          double price = 2.5;
//     boolean  true or false            boolean happy = true;
//     char     one single character     char grade = 'A';     (single quotes!)
//     String   text                     String name = "Ada";  (double quotes!)
//
// Give every variable below the right type so the program compiles.
// Java also has `var`, which guesses the type for you. Do not use it here:
// write every type out.
//
// The `import javalings.Check;` line pulls in a small helper that checks your
// answers. You will see it in most exercises from now on.

// I AM NOT DONE

import javalings.Check;

public class Variables5 {
    public static void main(String[] args) {
        ??? name = "Ada";
        ??? age = 13;
        ??? height = 1.62;
        ??? likesJava = true;
        ??? initial = 'A';

        System.out.println(name + " is " + age + " years old and " + height + " m tall.");
        System.out.println("Initial: " + initial + ". Likes Java: " + likesJava);
        Check.equals(13, age, "age is an int");
        Check.equals(1.62, height, "height is a double");
    }
}
