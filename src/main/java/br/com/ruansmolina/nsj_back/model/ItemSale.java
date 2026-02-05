package br.com.ruansmolina.nsj_back.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "sales_products")
@Data
@NoArgsConstructor
public class ItemSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sp_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sp_sales_id",nullable = false)
    @JsonIgnore
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "sp_prod_id",nullable = false)
    private Product product;

    @Column(name = "sp_quantity",nullable = false)
    private Integer quantity;

    @Column(name = "sp_unitprice",nullable = false)
    private BigDecimal unitPrice;

    @Column(name="sp_subtotal",nullable = false)
    private BigDecimal subTotal;
}
