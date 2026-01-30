package br.com.ruansmolina.nsj_back.Mapper;

import br.com.ruansmolina.nsj_back.DTO.ProductCreateDTO;
import br.com.ruansmolina.nsj_back.DTO.ProductResponseDTO;
import br.com.ruansmolina.nsj_back.model.Product;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ProductMapper {
    public Product toProduct(ProductCreateDTO dto){
        Product product = new Product();
        product.setName(dto.name());
        product.setPrice(dto.price());
        return product;
    }
    public ProductResponseDTO toDTO(Product product){
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice());
    }
}
