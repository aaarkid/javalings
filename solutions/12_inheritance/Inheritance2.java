// Inheritance2.java
//
// A variable of the parent type can hold any child object:
//
//     Animal a = new Dog("Rex");
//     a.sound();     // "Woof": Java picks the method of the real object
//
// This is called polymorphism, and it works the same way as in Python,
// except that Java checks the types up front.
//
// Implement `totalArea` for a list of shapes. Then add a Circle class
// (area = Math.PI * r * r) so the second check passes too.

import javalings.Check;
import java.util.List;

public class Inheritance2 {
    public static void main(String[] args) {
        List<Shape> shapes = List.of(new Rectangle(2, 3), new Rectangle(1, 1));
        Check.equals(7.0, totalArea(shapes), "two rectangles");

        Shape c = new Circle(1);
        Check.isTrue(Math.abs(c.area() - Math.PI) < 0.0001, "circle of radius 1");
        Check.equals("Circle", c.name(), "circle name");
    }

    static double totalArea(List<Shape> shapes) {
        double total = 0;
        for (Shape s : shapes) {
            total += s.area();
        }
        return total;
    }
}

class Shape {
    String name() {
        return "Shape";
    }

    double area() {
        return 0;
    }
}

class Circle extends Shape {
    private final double r;

    Circle(double r) {
        this.r = r;
    }

    @Override
    String name() {
        return "Circle";
    }

    @Override
    double area() {
        return Math.PI * r * r;
    }
}

class Rectangle extends Shape {
    private final double w;
    private final double h;

    Rectangle(double w, double h) {
        this.w = w;
        this.h = h;
    }

    @Override
    String name() {
        return "Rectangle";
    }

    @Override
    double area() {
        return w * h;
    }
}
