package br.com.SmallManager.records;

import jakarta.validation.constraints.NotNull;

public record GenericDTO<T>(@NotNull  T entity) {

    public GenericDTO(@NotNull T entity) {
        this.entity = entity;
    }
}
