package br.com.ruansmolina.nsj_back.repositories;

import br.com.ruansmolina.nsj_back.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
