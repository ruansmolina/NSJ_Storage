package br.com.ruansmolina.nsj_back.DTO;

import br.com.ruansmolina.nsj_back.model.ItemSale;
import br.com.ruansmolina.nsj_back.model.Sale;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record SaleResponseDTO(
        LocalDateTime date,
        BigDecimal total,
        List<ItemSale> items) {
    public SaleResponseDTO(Sale sale){
        this(sale.getDate(),sale.getTotal(),sale.getItems());
    }
}
