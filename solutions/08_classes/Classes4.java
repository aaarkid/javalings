// Classes4.java
//
// `static` means "belongs to the class, not to one object". A static field
// is shared by every object. A static method can be called without an object.
// You have been writing static methods all along: main is one.
//
// Give the Ticket class an id that counts up automatically: the first ticket
// gets 1, the second 2, and so on. You need one static field and one normal
// field. Also add a static method `created()` that returns how many tickets
// exist so far.

import javalings.Check;

public class Classes4 {
    public static void main(String[] args) {
        Ticket a = new Ticket("Concert");
        Ticket b = new Ticket("Cinema");
        Ticket c = new Ticket("Museum");
        Check.equals(1, a.id, "first ticket has id 1");
        Check.equals(2, b.id, "second ticket has id 2");
        Check.equals(3, c.id, "third ticket has id 3");
        Check.equals(3, Ticket.created(), "three tickets created");
        Check.equals("Cinema", b.event, "event name is stored");
    }
}

class Ticket {
    static int count = 0;

    String event;
    int id;

    Ticket(String event) {
        this.event = event;
        count++;
        this.id = count;
    }

    static int created() {
        return count;
    }
}
