package productoptions;

import bussinessexceptions.InvalidSkuException;
import readwritefiles.ItemReadWrite;

import java.util.HashMap;
import java.util.Map;

public class ItemList implements Option<Item> {
    private final Map<String, Item> items;
    private static final Option<Item> item = new ItemList();

    public ItemList() {
        this.items = ItemReadWrite.readItems();
    }

    @Override
    public void create(String sku, Item item) throws InvalidSkuException {
        checkIfSkuIsUnique(sku);
        items.put(sku, item);
    }

    @Override
    public void update(String sku, Item newItem) {
        items.replace(sku, newItem);
    }

    @Override
    public void delete(String sku) {
        items.remove(sku);
    }

    @Override
    public Item read(String sku) {
        return items.get(sku);
    }

    @Override
    public Map<String, Item> getList() {
        return new HashMap<>(items);
    }

    public static Option<Item> getInstance() {
        return item;
    }

    private void checkIfSkuIsUnique(String sku) throws InvalidSkuException {
        if (items.containsKey(sku)) {
            throw new InvalidSkuException();
        }
    }

    @Override
    public void saveChanges() {
        ItemReadWrite.saveFile(items);
    }
}
