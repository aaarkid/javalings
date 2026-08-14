package inventory.store;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import inventory.model.Item;

public class MemoryStore implements Store {

    private final Map<String, Item> items = new HashMap<>();

    @Override
    public void add(Item item) {
        Item existing = items.get(item.name());
        if (existing != null) {
            item = item.withQuantity(existing.quantity() + item.quantity());
        }
        items.put(item.name(), item);
    }

    @Override
    public boolean remove(String name) {
        return items.remove(name) != null;
    }

    @Override
    public Optional<Item> find(String name) {
        return Optional.ofNullable(items.get(name));
    }

    @Override
    public long totalValueCents() {
        return items.values().stream().mapToLong(Item::valueCents).sum();
    }

    @Override
    public List<Item> lowStock(int threshold) {
        return items.values().stream()
            .filter(i -> i.quantity() < threshold)
            .sorted(Comparator.comparing(Item::name))
            .toList();
    }
}
