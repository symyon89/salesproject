package ItemOptions;

public interface Option<T> {
    void create(String sku, T option);
    void update(String sku, T newOption);
    void delete(String sku);
    T read(String sku);

}
