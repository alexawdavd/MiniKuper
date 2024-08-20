package ru.sber.minikuper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sber.minikuper.entity.Product;
import ru.sber.minikuper.enums.ProductStatus;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByStatus(ProductStatus status);
}
