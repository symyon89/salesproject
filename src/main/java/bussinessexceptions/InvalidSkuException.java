package bussinessexceptions;

public class InvalidSkuException extends BussinessExceptions {
    public InvalidSkuException() {
        super("Invalid sku, Product not added");
    }
}
