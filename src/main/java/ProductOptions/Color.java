package ProductOptions;

import ReadWriteFiles.FileReadWrite;

import java.util.Map;

public class Color implements Option {
    private final Map<String, String> colorMap;
    private final static Option color = new Color();

    private Color() {
        colorMap = FileReadWrite.readFile(OptionType.Color);
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


    public static Option getInstance() {
        return color;
    }

}
