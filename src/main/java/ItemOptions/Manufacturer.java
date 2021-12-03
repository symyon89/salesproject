package ItemOptions;

import ReadWriteFiles.OptionReadWrite;

import java.util.Map;

public class Manufacturer implements Option<String> {
    private final Map<String, String> manufacturerMap;
    private final static Option<String> manufacturer = new Manufacturer();

    private Manufacturer() {
        manufacturerMap = OptionReadWrite.readFile(OptionType.Manufacturer);
    }

    @Override
    public void create(String sku, String manufacturer) {
        manufacturerMap.put(sku, manufacturer);
    }

    @Override
    public void update(String sku, String newManufacturer) {
        manufacturerMap.replace(sku, newManufacturer);
    }

    @Override
    public void delete(String sku) {
        manufacturerMap.remove(sku);
    }

    @Override
    public String read(String sku) {
        return manufacturerMap.get(sku);
    }

    public static Option<String> getInstance() {
        return manufacturer;
    }
}
