package ru.sber.minikuper.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sber.minikuper.entity.Product;
import ru.sber.minikuper.enums.ProductStatus;
import ru.sber.minikuper.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            Product cake = new Product("Кексик", "Вкусный", new BigDecimal(123));
            Product fry = new Product("Гранд Фри большой", "большие, вкусные палочки картофеля", new BigDecimal(321), ProductStatus.HIDE);
            Product mozaR5 = new Product("Moza r5", "Опять забыл коробку в спорт переключить", new BigDecimal(32990));
            Product idk = new Product("Фарингосепт", "Симпоматическое лечение горла", new BigDecimal(290), ProductStatus.ARCHIVED);

            productRepository.saveAll(List.of(cake, fry, mozaR5, idk));
        };
    }

}
