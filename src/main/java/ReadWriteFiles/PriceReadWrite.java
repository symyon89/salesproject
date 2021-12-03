package ReadWriteFiles;

import BussinessExceptions.InvaildEndDateException;
import ItemOptions.Price;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PriceReadWrite {
    private static final String PRICETXT = "src/main/java/resources/PriceList.txt";

    public static Map<String, Price> readPrice() {
        Map<String, Price> list = new HashMap<>();
        try {
            Files.lines(Path.of(PRICETXT))
                    .map(colorDetails -> colorDetails.split(","))
                    .forEach(line -> {
                        if (line.length > 2) {
                            try {
                                list.put(line[0], new Price()
                                        .setPrice(Double.parseDouble(line[1]), Double.parseDouble(line[2]),
                                                LocalDate.parse(line[3]), LocalDate.parse(line[4])));
                            } catch (InvaildEndDateException e) {
                                System.out.println(e.getMessage());
                            }
                        }else {
                            list.put(line[0], new Price().setPrice(Double.parseDouble(line[1])));
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
