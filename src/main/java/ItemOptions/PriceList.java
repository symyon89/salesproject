package ItemOptions;

import ReadWriteFiles.PriceReadWrite;

import java.util.Map;

public class PriceList implements Option<Price> {

    private final Map<String,Price> prices;
    private final static Option<Price> price = new PriceList();

    private PriceList() {
        this.prices = PriceReadWrite.readPrice();
    }

    @Override
    public void create(String sku, Price price) {
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

    public static Option<Price> getInstance() {
        return price;
    }

}
