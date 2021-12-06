package productpackage;

import java.util.List;

public class Product {
    private String name;
    private String description;
    private boolean isActive;
    List<String> skuItems;


    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public Product setActive(boolean active) {
        isActive = active;
        return this;
    }

    public List<String> getSkuItems() {
        return skuItems;
    }

    public Product setSkuItems(List<String> skuItems) {
        this.skuItems = skuItems;
        return this;
    }

    public String textProductToWrite() {
        StringBuilder stringBuilder = new StringBuilder();
        skuItems.forEach(item -> stringBuilder.append(",").append(item));
        return name + "," + description + "," + isActive + stringBuilder;
    }

}
