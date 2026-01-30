package br.com.ruansmolina.nsj_back.controllers;

import br.com.ruansmolina.nsj_back.DTO.ProductCreateDTO;
import br.com.ruansmolina.nsj_back.DTO.ProductResponseDTO;
import br.com.ruansmolina.nsj_back.DTO.ProductUpdateDTO;
import br.com.ruansmolina.nsj_back.model.Product;
import br.com.ruansmolina.nsj_back.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("product")
public class ProductsController {
    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        var products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        var product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> saveProduct(@RequestBody ProductCreateDTO dto, UriComponentsBuilder uriBuilder) {
        var response = productService.saveProduct(dto);
        var uri = uriBuilder.path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateDTO dto) {
        productService.updateProduct(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

}
