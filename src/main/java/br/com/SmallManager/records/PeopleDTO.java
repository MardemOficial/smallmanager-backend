package br.com.SmallManager.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PeopleDTO(@NotNull Long id, @NotNull String name, @NotNull String cpf, @NotNull BigDecimal valor) {
}
