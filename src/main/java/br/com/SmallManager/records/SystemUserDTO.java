package br.com.SmallManager.records;

import jakarta.validation.constraints.NotBlank;

public record SystemUserDTO(@NotBlank String name, @NotBlank String password) {
}
