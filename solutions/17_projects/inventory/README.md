# Project 2: inventory

This time the files exist and you fill in the gaps. Read the whole project
first, it is small:

    inventory/Main.java                the tests
    inventory/model/Item.java          a record, complete
    inventory/store/Store.java         an interface, complete
    inventory/store/MemoryStore.java   implement this
    inventory/cli/CommandParser.java   implement this

`Store` says what a store can do. `MemoryStore` is one way to do it (in a
HashMap). `CommandParser` turns text commands into calls on any Store. It
only knows the interface, so it would work with a database-backed store too.

Search for `TODO` to find the places to fill in. `Optional` is new: it is a
box that may or may not hold a value, used instead of returning `null`.
`Optional.of(x)`, `Optional.empty()`, `opt.isPresent()`, `opt.get()`.

Run with `java Javalings.java run project_inventory`. Delete the line below
when it passes.

