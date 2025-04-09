package br.com.SmallManager.records;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record RawMaterialDTO(

        UUID id,

        @NotBlank(message = "Código é necessário")
        String code,

        @NotBlank(message = "Descrição é necessário")
        String description,

        @NotNull
        @Min(value = 0, message = "Não pode ser um valor menor que zero")
        @Max(value = 200, message="Não pode ser maior que duzentos")
        BigDecimal price
        ) {

}
