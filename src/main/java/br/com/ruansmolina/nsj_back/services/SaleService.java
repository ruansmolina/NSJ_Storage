package br.com.ruansmolina.nsj_back.services;

import br.com.ruansmolina.nsj_back.DTO.ItemSaleAddDTO;
import br.com.ruansmolina.nsj_back.DTO.SaleCreateDTO;
import br.com.ruansmolina.nsj_back.DTO.SaleResponseDTO;
import br.com.ruansmolina.nsj_back.Mapper.ItemSaleMapper;
import br.com.ruansmolina.nsj_back.exceptions.NotFoundException;
import br.com.ruansmolina.nsj_back.model.ItemSale;
import br.com.ruansmolina.nsj_back.model.Product;
import br.com.ruansmolina.nsj_back.model.Sale;
import br.com.ruansmolina.nsj_back.repositories.SaleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SaleService {
    private final SaleRepository saleRepository;
    private final ProductService productService;
    private final ItemSaleMapper itemSaleMapper;

    public SaleService(SaleRepository saleRepository,ProductService productService,ItemSaleMapper itemSaleMapper
                       ){
        this.saleRepository = saleRepository;
        this.productService = productService;
        this.itemSaleMapper = itemSaleMapper;
    }

    public SaleResponseDTO createSale(SaleCreateDTO dto){
        var sale = new Sale();
        var ids = dto.itemsSale().stream().map(ItemSaleAddDTO::idProd).toList();
        var prods = productService.getSelectedList(ids);
        Map<Long, Product> map = prods.stream().collect(Collectors.toMap(Product::getId,product->product));
        List<ItemSale> itemSale = new ArrayList<>();
        dto.itemsSale().forEach(resp->{
            var prod = map.get(resp.idProd());
            if(prod== null){
                throw new NotFoundException("product not found;{id}".replace("{id}",resp.idProd().toString()));
            }
            itemSale.add(this.itemSaleMapper.toItemSale(resp,prod,sale));
        });
        sale.setItems(itemSale);
        sale.setDate(LocalDateTime.now());
        sale.defineTotal();
        var result = saleRepository.save(sale);
        return new SaleResponseDTO(result);
    }

}
