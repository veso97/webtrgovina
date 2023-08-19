package ba.sum.fsre.webtrgovina.services;

import ba.sum.fsre.webtrgovina.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Long id);
}

