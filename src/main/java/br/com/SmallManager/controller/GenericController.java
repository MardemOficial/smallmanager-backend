package br.com.SmallManager.controller;

import br.com.SmallManager.records.GenericDTO;
import br.com.SmallManager.service.GenericService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
@RestController
public class GenericController<T,R> {

    private final GenericService<T> service;

    private T entity;

    @PostMapping
    public ResponseEntity<T> save(@RequestBody @Valid GenericDTO<R> genericDTO) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        entity = (T) entity.getClass().getDeclaredConstructor().newInstance();
        BeanUtils.copyProperties(genericDTO, entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity).get());

    }

    @PutMapping
    public ResponseEntity<T> update(@RequestBody @Valid GenericDTO<R> genericDTO) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        entity = (T) entity.getClass().getDeclaredConstructor().newInstance();
        BeanUtils.copyProperties(genericDTO, entity);
        return ResponseEntity.status(HttpStatus.OK).body(service.save(entity).get());

    }

    @GetMapping
    public ResponseEntity<Optional<GenericDTO<Page<T>>>> listAll(@PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findAll(pageable)
                .map(GenericDTO::new));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> save(@RequestParam String id) {

        service.delete(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.OK).body("Removido com sucesso");

    }

}
