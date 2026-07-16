// Inheritance1.java
//
// Python: class Dog(Animal):        Java: class Dog extends Animal { ... }
//
// The child class gets every field and method of the parent. It can add its
// own and replace (override) existing ones. `super(...)` calls the parent's
// constructor; in Java it has to be the first line of the child constructor.
//
// Make Dog extend Animal, give it a constructor that passes the name up, and
// override `sound` so a dog says "Woof".

import javalings.Check;

public class Inheritance1 {
    public static void main(String[] args) {
        Animal generic = new Animal("Thing");
        Dog rex = new Dog("Rex");
        Check.equals("...", generic.sound(), "a generic animal makes no real sound");
        Check.equals("Woof", rex.sound(), "a dog says Woof");
        Check.equals("Rex says Woof", rex.speak(), "speak() is inherited and uses the overridden sound()");
    }
}

class Animal {
    protected String name;

    Animal(String name) {
        this.name = name;
    }

    String sound() {
        return "...";
    }

    String speak() {
        return name + " says " + sound();
    }
}

class Dog extends Animal {
    Dog(String name) {
        super(name);
    }

    @Override
    String sound() {
        return "Woof";
    }
}
