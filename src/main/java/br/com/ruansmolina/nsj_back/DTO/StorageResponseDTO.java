package br.com.ruansmolina.nsj_back.DTO;

import br.com.ruansmolina.nsj_back.model.Storage;

import java.time.LocalDateTime;

public record StorageResponseDTO(
        String productName,
        Integer quantity,
        LocalDateTime lastUpdate
) {
    public StorageResponseDTO(Storage storage){
        this(storage.getProduct().getName(),storage.getQuantity(), storage.getLastUpdate());
    }
}
