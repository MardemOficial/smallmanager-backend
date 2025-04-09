package br.com.SmallManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface IGenericRepository<T> extends JpaRepository<T, UUID> {
}
