package br.com.ruansmolina.nsj_back.DTO;

import br.com.ruansmolina.nsj_back.model.ItemSale;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SaleCreateDTO(
        @NotNull List<ItemSaleAddDTO> itemsSale
) {
}
