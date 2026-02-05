package br.com.ruansmolina.nsj_back.Mapper;

import br.com.ruansmolina.nsj_back.DTO.ItemSaleAddDTO;
import br.com.ruansmolina.nsj_back.model.ItemSale;
import br.com.ruansmolina.nsj_back.model.Product;
import br.com.ruansmolina.nsj_back.model.Sale;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@NoArgsConstructor
public class ItemSaleMapper {
    public ItemSale toItemSale(ItemSaleAddDTO dto, Product product, Sale sale){
        var item = new ItemSale();
        item.setQuantity(dto.quantity());
        item.setProduct(product);
        item.setUnitPrice(product.getPrice());
        item.setSubTotal(item.getUnitPrice().multiply(new BigDecimal(item.getQuantity())));
        item.setSale(sale);
        return item;
    }
}
