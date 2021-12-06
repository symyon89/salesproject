package productoptions;

import bussinessexceptions.InvalidSkuException;
import readwritefiles.OptionReadWrite;

import java.util.HashMap;
import java.util.Map;

public class ColorList implements Option<String> {
    private final Map<String, String> colorMap;
    private final static Option<String> color = new ColorList();

    private ColorList() {
        colorMap = OptionReadWrite.readFile(OptionType.Color);
    }

    @Override
    public void create(String sku, String color) throws InvalidSkuException {
        checkIfSkuIsUnique(sku);
        colorMap.put(sku, color);
    }

    @Override
    public void update(String sku, String newColor) {
        colorMap.replace(sku, newColor);
    }

    @Override
    public void delete(String sku) {
        colorMap.remove(sku);
    }

    @Override
    public String read(String sku) {
        return colorMap.get(sku);
    }

    @Override
    public Map<String, String> getList() {
        return new HashMap<>(colorMap);
    }

    public static Option<String> getInstance() {
        return color;
    }

    private void checkIfSkuIsUnique(String sku) throws InvalidSkuException {
        if (colorMap.containsKey(sku)) {
            throw new InvalidSkuException();
        }
    }

    @Override
    public void saveChanges() {
        OptionReadWrite.saveFile(colorMap, OptionType.Color);
    }
}
