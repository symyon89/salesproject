package ReadWriteFiles;

import ProductOptions.Item;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ItemReadWrite {
    private static final String ITEMTXT = "src/main/java/resources/ItemList.txt";

    public static Map<String, Item> readItems() {
        Map<String, Item> list = new HashMap<>();
        try {
            Files.lines(Path.of(ITEMTXT))
                    .filter(line -> !line.isBlank())
                    .map(details -> details.split(","))
                    .forEach(line -> list.put(line[0],
                            new Item(line[0])
                                    .setDateAdded(LocalDate.parse(line[1]))
                                    .setState(Boolean.parseBoolean(line[2]))
                                    .setStock(Integer.parseInt(line[3]))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void saveFile(Map<String, Item> listToSave) {

        Iterable<String> iterable = listToSave.keySet()
                .stream()
                .map(key -> listToSave.get(key).itemWriteToFile())
                .toList();

        try {
            Files.write(Path.of(ITEMTXT), iterable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
