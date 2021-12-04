package ProductPackage;

import ReadWriteFiles.ProductReadWrite;

import java.util.HashMap;
import java.util.Map;

public class ProductList {
    private final Map<Integer, Product> productMap;
    private final static ProductList productList = new ProductList();

    private ProductList() {
        productMap = ProductReadWrite.readProduct();
    }

    public void create(Product product) {
        productMap.put(productMap.size(), product);
    }


    public void update(int id, Product newProduct) {
        productMap.replace(id, newProduct);
    }


    public void delete(int id) {
        productMap.remove(id);
    }


    public Product read(int id) {
        return productMap.get(id);
    }


    public Map<Integer, Product> getList() {
        return new HashMap<>(productMap);
    }

    public static ProductList getInstance() {
        return productList;
    }

    public void saveChanges() {
        ProductReadWrite.saveFile(productMap);
    }
}
