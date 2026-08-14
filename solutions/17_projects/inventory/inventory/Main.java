package inventory;

import javalings.Check;
import java.util.List;
import inventory.cli.CommandParser;
import inventory.model.Item;
import inventory.store.MemoryStore;
import inventory.store.Store;

public class Main {
    public static void main(String[] args) {
        Store store = new MemoryStore();
        store.add(new Item("apple", 5, 120));
        store.add(new Item("pear", 2, 150));
        store.add(new Item("apple", 3, 110));

        Check.isTrue(store.find("apple").isPresent(), "apple is there");
        Check.equals(8, store.find("apple").get().quantity(), "apple quantities combined");
        Check.equals(110, store.find("apple").get().priceCents(), "apple price is the newest");
        Check.isTrue(store.find("kiwi").isEmpty(), "no kiwi");
        Check.equals(8L * 110 + 2L * 150, store.totalValueCents(), "total value");
        Check.equals(List.of("pear"), store.lowStock(3).stream().map(Item::name).toList(), "low stock");
        Check.isTrue(store.remove("pear"), "remove pear");
        Check.isTrue(!store.remove("pear"), "pear already gone");
        Check.equals(8L * 110, store.totalValueCents(), "value after removing pear");

        CommandParser cli = new CommandParser(new MemoryStore());
        Check.equals("added milk", cli.handle("add milk 4 99"), "add command");
        Check.equals("added bread", cli.handle("add bread 1 250"), "second add");
        Check.equals("milk: 4 @ 99", cli.handle("find milk"), "find command");
        Check.equals("no such item: eggs", cli.handle("find eggs"), "find missing");
        Check.equals("total value: 646", cli.handle("value"), "value command");
        Check.equals("removed bread", cli.handle("remove bread"), "remove command");
        Check.equals("no such item: bread", cli.handle("remove bread"), "remove missing");
        Check.equals("unknown command: dance", cli.handle("dance now"), "unknown command");
        Check.equals("total value: 396", cli.handle("  value  "), "extra spaces are fine");
    }
}
