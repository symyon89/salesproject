package menus;

import productoptions.*;
import productpackage.ProductList;
import readwritefiles.ItemReadWrite;
import readwritefiles.OptionReadWrite;
import readwritefiles.PriceReadWrite;
import readwritefiles.ProductReadWrite;

import java.util.Scanner;

public class Menu {

    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            showOptions();
            option = scanner.nextInt();
            actionOptions(option);
        } while (option != 0);
        saveChanges();
    }

    private void showOptions() {
        System.out.println("1.Show All Products");
        System.out.println("2.Add Product");
        System.out.println("0.Exit");
        System.out.print("Choose option : ");
    }

    private void actionOptions(int option) {
        switch (option) {
            case 1 -> showProducts();
            case 2 -> addProduct();
            default -> System.out.println("Invalid option!");
        }
        System.out.println();
    }

    private void showProducts() {
        ProductList.getInstance().getList().forEach((id, product) -> System.out.println( (id + 1) + "." + product.textProductToWrite()));
    }

    private void addProduct() {
        //TODO de completat in continuare
    }

    private void saveChanges() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you whant to save changes? (y/n):");
        if (scanner.next().equalsIgnoreCase("y")) {
            Runnable saveProducts = () -> ProductReadWrite.saveFile(ProductList.getInstance().getList());
            Runnable saveItems = () -> ItemReadWrite.saveFile(ItemList.getInstance().getList());
            Runnable savePrices = () -> PriceReadWrite.saveFile(PriceList.getInstance().getList());
            Runnable saveManufacturers = () -> OptionReadWrite.saveFile(ManufacturerList.getInstance().getList(), OptionType.Manufacturer);
            Runnable saveColors = () -> OptionReadWrite.saveFile(ColorList.getInstance().getList(), OptionType.Color);
            Runnable saveSizes = () -> OptionReadWrite.saveFile(SizeList.getInstance().getList(), OptionType.Size);

            Thread saveProductsThread = new Thread(saveProducts);
            Thread saveItemsThread = new Thread(saveItems);
            Thread savePricesThread = new Thread(savePrices);
            Thread saveManufacturersThread = new Thread(saveManufacturers);
            Thread saveColorsThread = new Thread(saveColors);
            Thread saveSizesThread = new Thread(saveSizes);

            saveProductsThread.start();
            saveItemsThread.start();
            savePricesThread.start();
            saveManufacturersThread.start();
            saveColorsThread.start();
            saveSizesThread.start();
        }
    }

}
