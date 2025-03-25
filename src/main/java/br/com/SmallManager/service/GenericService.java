package br.com.SmallManager.service;

import br.com.SmallManager.repository.IGenericRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GenericService<T> {

    private final IGenericRepository<T> repository;

    public Optional<T> save(T entity){
        return Optional.of(repository.save(entity));
    }

    public Optional<T> findById(UUID id){
        return repository.findById(id);
    }

    public Optional<Page<T>> findAll(Pageable pageable){
        return Optional.of(repository.findAll(pageable));
    }

    public void delete(UUID id){
        repository.deleteById(id);
    }

}
