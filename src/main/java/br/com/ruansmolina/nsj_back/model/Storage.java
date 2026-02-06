package br.com.ruansmolina.nsj_back.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="stor_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "stor_prod_id", unique = true,nullable = false)
    private Product product;

    @Column(name="stor_quantity",nullable = false)
    private Integer quantity;

    @Column(name="stor_lastupdate",nullable = false)
    private LocalDateTime lastUpdate;
}
