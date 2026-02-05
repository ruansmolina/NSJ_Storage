package br.com.ruansmolina.nsj_back.services;

import br.com.ruansmolina.nsj_back.DTO.ProductCreateDTO;
import br.com.ruansmolina.nsj_back.DTO.ProductResponseDTO;
import br.com.ruansmolina.nsj_back.DTO.ProductUpdateDTO;
import br.com.ruansmolina.nsj_back.Mapper.ProductMapper;
import br.com.ruansmolina.nsj_back.exceptions.NotFoundException;
import br.com.ruansmolina.nsj_back.model.Product;
import br.com.ruansmolina.nsj_back.repositories.ProductRepository;
import org.springframework.stereotype.Service;


import java.util.List;



@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository,ProductMapper productMapper){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public List<Product> getSelectedList(List<Long> ids){
        return productRepository.findAllById(ids);
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("product not found"));
    }
    public ProductResponseDTO saveProduct(ProductCreateDTO dto){
        var prod = productMapper.toProduct(dto);
        var saved = productRepository.save(prod);
        return productMapper.toDTO(saved) ;
    }

    public void deleteProductById(Long id){
        var prod = getProductById(id);
        productRepository.delete(prod);
    }

    public void updateProduct(Long id, ProductUpdateDTO dto) {
        var prod = getProductById(id);
        if(dto.name()!=null){
            prod.setName(dto.name());
        }
        if(dto.price()!=null){
            prod.setPrice(dto.price());
        }
        productRepository.save(prod);
    }
}
