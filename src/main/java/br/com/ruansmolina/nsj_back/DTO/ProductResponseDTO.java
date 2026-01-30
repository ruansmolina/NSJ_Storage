package br.com.ruansmolina.nsj_back.DTO;

import java.math.BigDecimal;

public record ProductResponseDTO(
        Long id,
        String name,
        BigDecimal price
) {
}
