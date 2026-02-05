package br.com.ruansmolina.nsj_back.controllers;

import br.com.ruansmolina.nsj_back.DTO.SaleCreateDTO;
import br.com.ruansmolina.nsj_back.DTO.SaleResponseDTO;
import br.com.ruansmolina.nsj_back.model.Sale;
import br.com.ruansmolina.nsj_back.services.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sale")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService){
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity<SaleResponseDTO> createSale(@RequestBody SaleCreateDTO dto){
        var response = saleService.createSale(dto);
        return ResponseEntity.ok(response);
    }

}
