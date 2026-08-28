// Inheritance3.java
//
// A Shape that is neither a rectangle nor a circle makes no sense, and
// `area()` returning 0 for it is a lie. Java lets you say so:
//
//     abstract class Shape {
//         abstract double area();     // no body: every child MUST implement it
//     }
//
// You cannot write `new Shape()` any more, only `new Rectangle(...)` etc.
//
// Make Vehicle abstract with an abstract `wheels()` method. Then `Bike`
// will not compile until you give it a wheels() method. Add it (2 wheels).

// I AM NOT DONE

import javalings.Check;

public class Inheritance3 {
    public static void main(String[] args) {
        Vehicle[] fleet = {new Car(), new Bike(), new Car()};
        int wheels = 0;
        for (Vehicle v : fleet) {
            wheels += v.wheels();
        }
        Check.equals(10, wheels, "4 + 2 + 4 wheels");
        Check.equals("Bike with 2 wheels", fleet[1].describe(), "describe a bike");
        Check.isTrue(java.lang.reflect.Modifier.isAbstract(Vehicle.class.getModifiers()), "Vehicle is abstract");
    }
}

class Vehicle {
    int wheels() {
        return 0;
    }

    String describe() {
        return getClass().getSimpleName() + " with " + wheels() + " wheels";
    }
}

class Car extends Vehicle {
    @Override
    int wheels() {
        return 4;
    }
}

class Bike extends Vehicle {
}
