package ProductOptions;

import ReadWriteFiles.FileReadWrite;

import java.util.Map;

public class Manufacturer implements Option {
    private final Map<String, String> manufacturerMap;
    private final static Option manufacturer = new Manufacturer();

    private Manufacturer() {
        manufacturerMap = FileReadWrite.readFile(OptionType.Manufacturer);
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

    public static Option getInstance() {
        return manufacturer;
    }
}
