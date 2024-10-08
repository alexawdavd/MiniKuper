package ru.sber.minikuper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sber.minikuper.dto.ProductCreateDto;
import ru.sber.minikuper.dto.ProductUpdateDto;
import ru.sber.minikuper.entity.Product;
import ru.sber.minikuper.enums.ProductStatus;
import ru.sber.minikuper.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Transactional
    public Product createProduct(ProductCreateDto productCreateDto) {
        Product product = new Product(productCreateDto.getTitle(), productCreateDto.getDescription(), productCreateDto.getPrice());
        validateProduct(product);
        return productRepository.save(product);
    }


    @Transactional
    public Product updateProduct(Long id, ProductUpdateDto productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (productDetails.getTitle() != null && !productDetails.getTitle().isEmpty()) {
            product.setTitle(productDetails.getTitle());
        }
        if (productDetails.getDescription() != null && !productDetails.getDescription().isEmpty()) {
            product.setDescription(productDetails.getDescription());
        }
        if (productDetails.getPrice() != null) {
            product.setPrice(productDetails.getPrice());
        }
        if (productDetails.getStatus() != null) {
            product.setStatus(ProductStatus.getById(productDetails.getStatus()));
        }

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAvailableProducts() {
        return productRepository.findByStatus(ProductStatus.ACTIVE);
    }

    private void validateProduct(Product product) {
        if (product.getTitle() == null || product.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Product title cannot be null or empty");
        }
        if (product.getDescription() == null || product.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Product description cannot be null or empty");
        }
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero");
        }

    }
}
