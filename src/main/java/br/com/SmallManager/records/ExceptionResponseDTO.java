package br.com.SmallManager.records;

import org.springframework.validation.FieldError;

public record ExceptionResponseDTO(String field, String message) {

    public ExceptionResponseDTO(FieldError fieldError){
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
