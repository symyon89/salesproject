package ProductOptions;

import ReadWriteFiles.FileReadWrite;

import java.util.Map;

public class Size implements Option {
    private final Map<String, String> sizeMap;
    private final static Option size = new Size();

    private Size() {
        sizeMap = FileReadWrite.readFile(OptionType.Size);
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


    public static Option getInstance() {
        return size;
    }
}
