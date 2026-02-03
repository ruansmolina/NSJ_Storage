package br.com.ruansmolina.nsj_back.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record StorageAddDTO(
        @NotNull Long idItem,
        @NotNull @Min(1) Integer quantity) {
}
