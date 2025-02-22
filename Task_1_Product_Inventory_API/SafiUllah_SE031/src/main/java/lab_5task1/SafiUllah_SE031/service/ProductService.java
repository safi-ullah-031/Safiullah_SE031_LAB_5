package lab_5task1.SafiUllah_SE031.service;

import lab_5task1.SafiUllah_SE031.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private List<Product> productList = new ArrayList<>();
    private Long nextId = 1L;

    // Add Product
    public Product addProduct(Product product) {
        product.setId(nextId++);
        productList.add(product);
        return product;
    }

    // Get All Products
    public List<Product> getAllProducts() {
        return productList;
    }

    // Get Product by ID
    public Product getProductById(Long id) {
        return productList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Update Product
    public Product updateProduct(Long id, Product updatedProduct) {
        for (Product product : productList) {
            if (product.getId().equals(id)) {
                product.setName(updatedProduct.getName());
                product.setCategory(updatedProduct.getCategory());
                product.setPrice(updatedProduct.getPrice());
                product.setQuantity(updatedProduct.getQuantity());
                return product;
            }
        }
        return null;
    }

    // Delete Product by ID
    public boolean deleteProduct(Long id) {
        return productList.removeIf(p -> p.getId().equals(id));
    }

    // Delete All Products
    public void deleteAllProducts() {
        productList.clear();
    }
}
