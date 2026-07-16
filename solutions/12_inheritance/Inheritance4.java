// Inheritance4.java
//
// A class can extend only ONE parent. But it can promise to fulfil any number
// of interfaces. An interface is a list of methods without bodies:
//
//     interface Greeter {
//         String greet();
//     }
//     class Robot implements Greeter {
//         public String greet() { return "BEEP"; }   // must be public
//     }
//
// You met interfaces already: List is one, ArrayList implements it.
//
// Make Duck implement both Swimmer and Flyer. Then `describe` in main
// should work for any object that can swim, using the interface type.

import javalings.Check;
import java.util.List;

public class Inheritance4 {
    public static void main(String[] args) {
        List<Swimmer> swimmers = List.of(new Duck(), new Fish());
        Check.equals("Duck swims", swimmers.get(0).swim(), "duck swims");
        Check.equals("Fish swims", swimmers.get(1).swim(), "fish swims");

        Flyer f = new Duck();
        Check.equals("Duck flies", f.fly(), "duck flies");
    }
}

interface Swimmer {
    String swim();
}

interface Flyer {
    String fly();
}

class Fish implements Swimmer {
    public String swim() {
        return "Fish swims";
    }
}

class Duck implements Swimmer, Flyer {
    public String swim() {
        return "Duck swims";
    }

    public String fly() {
        return "Duck flies";
    }
}
