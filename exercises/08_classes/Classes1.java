// Classes1.java
//
// A class in Python:                   The same class in Java:
//
//     class Dog:                        class Dog {
//         def __init__(self, name):         String name;
//             self.name = name              int age;
//             self.age = 0
//                                           Dog(String name) {
//         def bark(self):                       this.name = name;
//             print(self.name + "!")            this.age = 0;
//                                           }
//
//                                           void bark() {
//                                               System.out.println(name + "!");
//                                           }
//                                       }
//
// Differences: the fields are declared up front with a type. The constructor
// has the same name as the class and no return type. `this` is Python's
// `self`, but you only need to write it when a parameter has the same name
// as a field. Creating an object: `Dog d = new Dog("Rex");`
//
// The Counter class is missing a constructor and a field. Add them.

// I AM NOT DONE

import javalings.Check;

public class Classes1 {
    public static void main(String[] args) {
        Counter c = new Counter(10);
        c.increment();
        c.increment();
        Check.equals(12, c.value, "counter after two increments");

        Counter other = new Counter(0);
        Check.equals(0, other.value, "each object has its own value");
    }
}

class Counter {
    void increment() {
        value++;
    }
}
