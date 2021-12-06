package ProductOptions;

import BussinessExceptions.InvalidSkuException;

import java.time.LocalDate;

public class Item {
    //TODO De adaugat stoc
    private String sku;
    private boolean isActive;
    private LocalDate dateAdded;
    private int stock;

    public Item(String sku, Price price) {

        this.sku = sku;
        this.dateAdded = LocalDate.now();
        this.isActive = true;
        this.stock = 1;
        try {
            PriceList.getInstance().create(sku, price);
        } catch (InvalidSkuException e) {
            System.out.println(e.getMessage());
        }
    }

    public Item(String sku) {
        this.sku = sku;
        this.dateAdded = LocalDate.now();
        this.isActive = true;
        this.stock = 1;
    }

    public Item setState(boolean state) {
        this.isActive = state;
        return this;
    }

    public Item setStock(int stock) {
        this.stock = stock;
        return this;
    }

    public void changePrice(Price price) {
        PriceList.getInstance().update(sku, price);
    }

    public double getPrice() {
        return PriceList.getInstance().read(sku).getPrice();
    }

    public Item addColor(String color) {
        try {
            ColorList.getInstance().create(sku, color);
        } catch (InvalidSkuException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }

    public void changeColor(String color) {
        ColorList.getInstance().update(sku, color);
    }

    public void deleteColor() {
        ColorList.getInstance().delete(sku);
    }

    public Item addManufacturer(String manufacturer) {
        try {
            ManufacturerList.getInstance().create(sku, manufacturer);
        } catch (InvalidSkuException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }

    public void changeManufacturer(String manufacturer) {
        ManufacturerList.getInstance().update(sku, manufacturer);
    }

    public void getManufacturer() {
        ManufacturerList.getInstance().read(sku);
    }

    public void deleteManufacturer() {
        ManufacturerList.getInstance().delete(sku);
    }

    public Item addSize(String size) {
        try {
            SizeList.getInstance().create(sku, size);
        } catch (InvalidSkuException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }

    public void changeSize(String size) {
        SizeList.getInstance().update(sku, size);
    }

    public String getSize() {
        return SizeList.getInstance().read(sku);
    }

    public void deleteSize() {
        SizeList.getInstance().delete(sku);
    }

    public String getSku() {
        return sku;
    }

    public Item setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
        return this;
    }


    public String itemWriteToFile() {
        return sku + "," + dateAdded + "," + isActive + "," + stock;
    }


}
