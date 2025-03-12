package br.com.SmallManager.records;

import br.com.SmallManager.domain.People;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record PeopleDTO(
        @NotNull
        UUID id,
        @NotBlank
        String name,
        @NotBlank
        String cpf,
        @NotNull
        BigDecimal valor) {
    public PeopleDTO (People people){
        this(people.getId(), people.getName(), people.getCpf(), people.getValor());
    }

}
