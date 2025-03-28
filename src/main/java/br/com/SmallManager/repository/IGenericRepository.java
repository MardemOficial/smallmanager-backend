package br.com.SmallManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IGenericRepository<T> extends JpaRepository<T, UUID> {
}
