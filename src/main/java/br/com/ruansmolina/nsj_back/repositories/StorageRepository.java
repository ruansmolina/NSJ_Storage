package br.com.ruansmolina.nsj_back.repositories;

import br.com.ruansmolina.nsj_back.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<Storage,Long> {
    Optional<Storage> findByProductId(Long id);
}
