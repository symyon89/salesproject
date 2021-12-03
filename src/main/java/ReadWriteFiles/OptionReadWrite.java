package ReadWriteFiles;

import ProductOptions.OptionType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class OptionReadWrite {
    private static final String COLORTXT = "src/main/java/resources/ColorList.txt";
    private static final String SIZETXT = "src/main/java/resources/SizeList.txt";
    private static final String MANUFACTURERTXT = "src/main/java/resources/ManufacturerList.txt";

    public static Map<String, String> readFile(OptionType type) {
        String txt = selectFileType(type);
        Map<String, String> list = new HashMap<>();
        try {
            Files.lines(Path.of(txt))
                    .map(details -> details.split(","))
                    .forEach(line -> list.put(line[0], line[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void saveFile(Map<String,String> listToSave,OptionType type){
        String txt = selectFileType(type);
        Iterable<String> iterable = listToSave.keySet().stream().map(key -> key + "," +listToSave.get(key)).toList();

        try {
            Files.write(Path.of(txt),iterable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String selectFileType(OptionType type){
        String txt = "";
        switch (type){
            case Size -> txt = SIZETXT;
            case Color -> txt = COLORTXT;
            case Manufacturer -> txt = MANUFACTURERTXT;
        }
        return txt;
    }
}
