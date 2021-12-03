package ItemOptions;

import ReadWriteFiles.OptionReadWrite;

import java.util.HashMap;
import java.util.Map;

public class Color implements Option<String> {
    private final Map<String, String> colorMap;
    private final static Option<String> color = new Color();

    private Color() {
        colorMap = OptionReadWrite.readFile(OptionType.Color);
    }

    @Override
    public void create(String sku, String color) {
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


}
