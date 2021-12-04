package ReadWriteFiles;

import ProductPackage.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ProductReadWrite {
    private static final String PRODUCTTXT = "src/main/java/resources/ProductList";

    public static Map<Integer, Product> readProduct() {
        Map<Integer, Product> list = new HashMap<>();
        try {
            Files.lines(Path.of(PRODUCTTXT))
                    .map(details -> details.split(","))
                    .forEach(line -> list.put(Integer.parseInt(line[0]),
                            new Product().setName(line[1])
                                    .setDescription(line[2])
                                    .setActive(Boolean.parseBoolean(line[3]))
                                    .setSkuItems(Arrays.asList(line).subList(4, line.length))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void saveFile(Map<Integer, Product> listToSave) {

        Iterable<String> iterable = listToSave.keySet()
                .stream()
                .map(key -> key + "," + listToSave.get(key).textProductToWrite())
                .toList();

        try {
            Files.write(Path.of(PRODUCTTXT), iterable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
