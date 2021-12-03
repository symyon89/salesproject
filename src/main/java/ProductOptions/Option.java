package ProductOptions;

public interface Option {
    void create(String sku, String option);
    void update(String sku, String newOption);
    void delete(String sku);
    String read(String sku);

}
