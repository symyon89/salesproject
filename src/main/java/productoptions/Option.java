package productoptions;

import bussinessexceptions.InvalidSkuException;

import java.util.Map;

public interface Option<T> {
    void create(String sku, T option) throws InvalidSkuException;

    void update(String sku, T newOption);

    void delete(String sku);

    T read(String sku);

    Map<String, T> getList();

    void saveChanges();

}
