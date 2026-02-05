package br.com.ruansmolina.nsj_back.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sales")
@Data
@NoArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_id")
    private Long id;

    @Column(name = "sales_date",nullable = false)
    private LocalDateTime date;

    @Column(name = "sales_total",nullable = false)
    private BigDecimal total = BigDecimal.ZERO;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<ItemSale> items = new ArrayList<>();

    public void defineTotal(){
        items.forEach(i->{
            total = total.add(i.getSubTotal());
        });
    }
}
