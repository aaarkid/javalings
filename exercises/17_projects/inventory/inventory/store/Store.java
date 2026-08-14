package inventory.store;

import java.util.List;
import java.util.Optional;
import inventory.model.Item;

public interface Store {

    /** Adds the item. If an item with the same name exists, the quantities are added up. */
    void add(Item item);

    /** Removes the item with that name. Returns false if there was none. */
    boolean remove(String name);

    Optional<Item> find(String name);

    /** Sum of quantity * price over all items. */
    long totalValueCents();

    /** Items with quantity below the threshold, sorted by name. */
    List<Item> lowStock(int threshold);
}
