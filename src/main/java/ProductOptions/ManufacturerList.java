package ProductOptions;

import BussinessExceptions.InvalidSkuException;
import ReadWriteFiles.OptionReadWrite;

import java.util.HashMap;
import java.util.Map;

public class ManufacturerList implements Option<String> {
    private final Map<String, String> manufacturerMap;
    private final static Option<String> manufacturer = new ManufacturerList();

    private ManufacturerList() {
        manufacturerMap = OptionReadWrite.readFile(OptionType.Manufacturer);
    }

    @Override
    public void create(String sku, String manufacturer) throws InvalidSkuException {
        checkIfSkuIsUnique(sku);
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

    @Override
    public Map<String, String> getList() {
        return new HashMap<>(manufacturerMap);
    }

    public static Option<String> getInstance() {
        return manufacturer;
    }

    private void checkIfSkuIsUnique(String sku) throws InvalidSkuException {
        if (manufacturerMap.containsKey(sku)) {
            throw new InvalidSkuException();
        }
    }
}
