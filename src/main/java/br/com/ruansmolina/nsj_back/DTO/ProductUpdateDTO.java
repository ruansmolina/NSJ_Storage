package br.com.ruansmolina.nsj_back.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductUpdateDTO(
        String name,
        BigDecimal price) {
}
