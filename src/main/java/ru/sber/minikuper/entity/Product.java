package ru.sber.minikuper.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sber.minikuper.enums.ProductStatus;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL) // Будем хранить в бд тип INT
    private ProductStatus status;


    public Product(String title, String description, BigDecimal price) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.status = ProductStatus.ACTIVE;
        this.createTime = new Date();
    }

    public Product(String title, String description, BigDecimal price, ProductStatus status) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.status = status;
        this.createTime = new Date();
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }
}
