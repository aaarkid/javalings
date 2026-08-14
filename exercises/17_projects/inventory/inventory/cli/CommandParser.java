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
        // TODO: a switch on `command`. Integer.parseInt for the numbers.
        return "unknown command: " + command;
    }
}
