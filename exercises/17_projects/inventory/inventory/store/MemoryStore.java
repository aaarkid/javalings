package inventory.store;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import inventory.model.Item;

public class MemoryStore implements Store {

    private final Map<String, Item> items = new HashMap<>();

    @Override
    public void add(Item item) {
        // TODO: if the name is already there, combine the quantities (keep the new price)
    }

    @Override
    public boolean remove(String name) {
        // TODO
        return false;
    }

    @Override
    public Optional<Item> find(String name) {
        // TODO
        return Optional.empty();
    }

    @Override
    public long totalValueCents() {
        // TODO
        return 0;
    }

    @Override
    public List<Item> lowStock(int threshold) {
        // TODO: a stream over items.values() with filter and sorted works well here
        return List.of();
    }
}
