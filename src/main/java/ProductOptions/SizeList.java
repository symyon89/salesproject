package ProductOptions;

import BussinessExceptions.InvalidSkuException;
import ReadWriteFiles.OptionReadWrite;

import java.util.HashMap;
import java.util.Map;

public class SizeList implements Option<String> {
    private final Map<String, String> sizeMap;
    private final static Option<String> size = new SizeList();

    private SizeList() {
        sizeMap = OptionReadWrite.readFile(OptionType.Size);
    }

    @Override
    public void create(String sku, String size) throws InvalidSkuException {
        checkIfSkuIsUnique(sku);
        sizeMap.put(sku, size);
    }

    @Override
    public void update(String sku, String newSize) {
        sizeMap.replace(sku, newSize);
    }

    @Override
    public void delete(String sku) {
        sizeMap.remove(sku);
    }

    @Override
    public String read(String sku) {
        return sizeMap.get(sku);
    }

    @Override
    public Map<String, String> getList() {
        return new HashMap<>(sizeMap);
    }

    public static Option<String> getInstance() {
        return size;
    }

    private void checkIfSkuIsUnique(String sku) throws InvalidSkuException {
        if (sizeMap.containsKey(sku)) {
            throw new InvalidSkuException();
        }
    }

    @Override
    public void saveChanges() {
        OptionReadWrite.saveFile(sizeMap, OptionType.Size);
    }
}
