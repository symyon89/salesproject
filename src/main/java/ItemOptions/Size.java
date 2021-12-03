package ItemOptions;

import ReadWriteFiles.OptionReadWrite;

import java.util.HashMap;
import java.util.Map;

public class Size implements Option<String> {
    private final Map<String, String> sizeMap;
    private final static Option<String> size = new Size();

    private Size() {
        sizeMap = OptionReadWrite.readFile(OptionType.Size);
    }

    @Override
    public void create(String sku, String size) {
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
}
