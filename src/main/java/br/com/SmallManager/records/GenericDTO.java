package br.com.SmallManager.records;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public record GenericDTO<T>(@JsonUnwrapped  T entity) {

    public GenericDTO(T entity) {
        this.entity = entity;
    }

}
