package br.com.ruansmolina.nsj_back.controllers;

import br.com.ruansmolina.nsj_back.DTO.StorageAddDTO;
import br.com.ruansmolina.nsj_back.DTO.StorageResponseDTO;
import br.com.ruansmolina.nsj_back.services.StorageService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/storage")
public class StorageController {

    private final StorageService storageService;

    public StorageController(StorageService storageService){
        this.storageService = storageService;
    }

    @GetMapping
    public ResponseEntity<List<StorageResponseDTO>> getProductsOnStorage(){
        var storage = storageService.getProductsFromStorage();
        return ResponseEntity.ok(storage);
    }
    @PostMapping
    public ResponseEntity<Void> addProductOnStorage(@RequestBody @Valid StorageAddDTO dto, UriComponentsBuilder builder){
        Long id = storageService.addItemToStorage(dto);
        var uri = builder.path("/storage/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

}
