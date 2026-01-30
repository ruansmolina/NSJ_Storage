package br.com.ruansmolina.nsj_back.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private Long id;

    @Column(name = "prod_name",nullable = false)
    private String name;

    @Column(precision = 10,scale = 2,name = "prod_price",nullable = false)
    private BigDecimal price;

    @Column(unique = true,name = "\"prod_SKU\"")
    private String codeSKU;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private Storage storage;

}
