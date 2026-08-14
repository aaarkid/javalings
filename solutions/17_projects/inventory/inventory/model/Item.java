package inventory.model;

public record Item(String name, int quantity, int priceCents) {

    public long valueCents() {
        return (long) quantity * priceCents;
    }

    public Item withQuantity(int newQuantity) {
        return new Item(name, newQuantity, priceCents);
    }
}
