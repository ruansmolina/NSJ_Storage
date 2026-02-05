package br.com.ruansmolina.nsj_back.repositories;

import br.com.ruansmolina.nsj_back.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository  extends JpaRepository<Sale,Long> {
}
