package inventory.cli;

import java.util.Optional;
import inventory.model.Item;
import inventory.store.Store;

/**
 * Turns one line of text into a store operation and returns a reply.
 *
 *   add <name> <quantity> <priceCents>   ->  "added <name>"
 *   remove <name>                        ->  "removed <name>"  or  "no such item: <name>"
 *   find <name>                          ->  "<name>: <quantity> @ <priceCents>"  or  "no such item: <name>"
 *   value                                ->  "total value: <cents>"
 *   anything else                        ->  "unknown command: <first word>"
 */
public class CommandParser {

    private final Store store;

    public CommandParser(Store store) {
        this.store = store;
    }

    public String handle(String line) {
        String[] parts = line.trim().split("\\s+");
        String command = parts[0];
        switch (command) {
            case "add" -> {
                store.add(new Item(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3])));
                return "added " + parts[1];
            }
            case "remove" -> {
                return store.remove(parts[1]) ? "removed " + parts[1] : "no such item: " + parts[1];
            }
            case "find" -> {
                Optional<Item> found = store.find(parts[1]);
                if (found.isPresent()) {
                    Item i = found.get();
                    return i.name() + ": " + i.quantity() + " @ " + i.priceCents();
                }
                return "no such item: " + parts[1];
            }
            case "value" -> {
                return "total value: " + store.totalValueCents();
            }
            default -> {
                return "unknown command: " + command;
            }
        }
    }
}
