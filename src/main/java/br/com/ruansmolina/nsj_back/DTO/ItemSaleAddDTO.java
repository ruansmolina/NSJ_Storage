package br.com.ruansmolina.nsj_back.DTO;

import jakarta.validation.constraints.NotNull;

public record ItemSaleAddDTO(
        @NotNull Long idProd,
        @NotNull Integer quantity
){
}
