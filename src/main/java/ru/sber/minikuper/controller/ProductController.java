package ru.sber.minikuper.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.sber.minikuper.dto.ProductCreateDto;
import ru.sber.minikuper.dto.ProductUpdateDto;
import ru.sber.minikuper.entity.Product;
import ru.sber.minikuper.service.ProductService;

import javax.validation.Valid;
import java.util.List;

/**
 * REST-контроллер для управления продуктами.
 * Предоставляет методы для выполнения CRUD-операций над продуктами.
 */
@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    private static final Logger logger = Logger.getLogger(ProductController.class);

    private final ProductService productService;

    /**
     * Конструктор ProductController.
     *
     * @param productService сервис для управления продуктами.
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Получает список всех продуктов.
     *
     * @return список всех продуктов с кодом состояния 200 OK.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        logger.info("Запрос на получение всех продуктов");
        List<Product> products = productService.getAllProducts();
        logger.debug("Количество полученных продуктов: " + products.size());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Получает список доступных продуктов.
     *
     * @return список доступных продуктов с кодом состояния 200 OK.
     */
    @GetMapping("")
    public ResponseEntity<List<Product>> getAvailableProducts() {
        logger.info("Запрос на получение доступных продуктов");
        List<Product> products = productService.getAvailableProducts();
        logger.debug("Количество доступных продуктов: " + products.size());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Получает продукт по его идентификатору.
     *
     * @param id идентификатор продукта.
     * @return найденный продукт с кодом состояния 200 OK.
     * @throws ResponseStatusException если продукт не найден, выбрасывается исключение с кодом состояния 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        logger.info("Запрос на получение продукта с ID: " + id);
        try {
            Product product = productService.getProductById(id);
            logger.debug("Продукт найден: " + product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.error("Продукт с ID " + id + " не найден", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    /**
     * Создает новый продукт.
     *
     * @param product объект DTO для создания нового продукта.
     * @return созданный продукт с кодом состояния 201 Created.
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductCreateDto product) {
        logger.info("Запрос на создание нового продукта: " + product.getTitle());
        Product createdProduct = productService.createProduct(product);
        logger.debug("Продукт создан: " + createdProduct);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    /**
     * Обновляет существующий продукт по его идентификатору.
     *
     * @param id            идентификатор продукта, который нужно обновить.
     * @param productDetails объект DTO с деталями обновления продукта.
     * @return обновленный продукт с кодом состояния 200 OK или код состояния 404 Not Found, если продукт не найден.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductUpdateDto productDetails) {
        logger.info("Запрос на обновление продукта с ID: " + id);
        try {
            Product updatedProduct = productService.updateProduct(id, productDetails);
            logger.debug("Продукт обновлен: " + updatedProduct);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.error("Не удалось обновить продукт с ID " + id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Удаляет продукт по его идентификатору.
     *
     * @param id идентификатор продукта, который нужно удалить.
     * @return код состояния 204 No Content, если продукт успешно удален, или код состояния 404 Not Found, если продукт не найден.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        logger.info("Запрос на удаление продукта с ID: " + id);
        try {
            productService.deleteProduct(id);
            logger.debug("Продукт с ID " + id + " был удален");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            logger.error("Не удалось удалить продукт с ID " + id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
