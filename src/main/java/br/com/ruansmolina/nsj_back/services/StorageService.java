package br.com.ruansmolina.nsj_back.services;

import br.com.ruansmolina.nsj_back.DTO.StorageAddDTO;
import br.com.ruansmolina.nsj_back.DTO.StorageResponseDTO;
import br.com.ruansmolina.nsj_back.model.Storage;
import br.com.ruansmolina.nsj_back.repositories.StorageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StorageService {

    private final StorageRepository storageRepository;
    private final ProductService productService;
    public StorageService(StorageRepository storageRepository,ProductService productService){
        this.storageRepository = storageRepository;
        this.productService = productService;
    }
    public List<StorageResponseDTO> getProductsFromStorage(){
        return storageRepository.findAll().stream().map(StorageResponseDTO::new).toList();
    }

    public Long addItemToStorage(StorageAddDTO dto){
        var product = productService.getProductById(dto.idItem());
        var storage = storageRepository.findByProductId(dto.idItem());
        Storage storageResult;
        if(storage.isPresent()){
            storageResult = storage.get();
            storageResult.setQuantity(storageResult.getQuantity() + dto.quantity());
        }else {
            storageResult  = new Storage();
            storageResult.setProduct(product);
            storageResult.setQuantity(dto.quantity());
        }

        storageResult.setLastUpdate(LocalDateTime.now());
        return storageRepository.save(storageResult).getId();

    }

}
