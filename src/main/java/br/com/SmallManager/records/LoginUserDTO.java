package br.com.SmallManager.records;

import jakarta.validation.constraints.NotBlank;

public record LoginUserDTO(

        @NotBlank(message = "O campo name não pode ser nulo.")
        String name,

        @NotBlank(message = "O campo password não pode ser nulo.")
        String password) {
}
