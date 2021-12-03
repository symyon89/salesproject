package ProductOptions;

import BussinessExceptions.InvalidSkuException;
import ReadWriteFiles.PriceReadWrite;

import java.util.HashMap;
import java.util.Map;

public class PriceList implements Option<Price> {

    private final Map<String, Price> prices;
    private final static Option<Price> price = new PriceList();

    private PriceList() {
        this.prices = PriceReadWrite.readPrice();
    }

    @Override
    public void create(String sku, Price price) throws InvalidSkuException {
        checkIfSkuIsUnique(sku);
        prices.put(sku, price);
    }

    @Override
    public void update(String sku, Price newPrice) {
        prices.replace(sku, newPrice);
    }

    @Override
    public void delete(String sku) {
        prices.remove(sku);
    }

    @Override
    public Price read(String sku) {
        return prices.get(sku);
    }

    @Override
    public Map<String, Price> getList() {
        return new HashMap<>(prices);
    }

    public static Option<Price> getInstance() {
        return price;
    }

    private void checkIfSkuIsUnique(String sku) throws InvalidSkuException {
        if (prices.containsKey(sku)) {
            throw new InvalidSkuException();
        }
    }

    @Override
    public void saveChanges() {
        PriceReadWrite.saveFile(prices);
    }
}
