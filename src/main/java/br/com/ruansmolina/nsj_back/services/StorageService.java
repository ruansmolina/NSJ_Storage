package br.com.ruansmolina.nsj_back.services;

import br.com.ruansmolina.nsj_back.DTO.ItemSaleAddDTO;
import br.com.ruansmolina.nsj_back.DTO.SaleCreateDTO;
import br.com.ruansmolina.nsj_back.DTO.StorageAddDTO;
import br.com.ruansmolina.nsj_back.DTO.StorageResponseDTO;
import br.com.ruansmolina.nsj_back.exceptions.InsufficientResource;
import br.com.ruansmolina.nsj_back.exceptions.NotFoundException;
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
    public Storage getStorage(Long idProd){
        return storageRepository.findByProductId(idProd).orElseThrow(()-> new NotFoundException("product not found"));
    }
    public Boolean verifyQuantity(Long idProd, int quantity){
        var prod = getStorage(idProd);
        return prod.getQuantity() >= quantity;
    }
    public void  verifyAllQuantity(SaleCreateDTO itemsSale){
        for(var i :itemsSale.itemsSale()){
            if(!verifyQuantity(i.idProd(),i.quantity())){
                throw new InsufficientResource("insufficient quantity");
            }
        }
    }
    public void removeSaleQuantity(Long idProd, int quantity){
        if(!verifyQuantity(idProd,quantity)){
            throw new InsufficientResource("insufficient quantity");
        }
        var prod = getStorage(idProd);
        prod.setQuantity(prod.getQuantity() - quantity);
        storageRepository.save(prod);

    }
    public void removeAllSaleQuantity(SaleCreateDTO itemsSale){
        itemsSale.itemsSale().forEach((i)->removeSaleQuantity(i.idProd(),i.quantity()));
    }


}
