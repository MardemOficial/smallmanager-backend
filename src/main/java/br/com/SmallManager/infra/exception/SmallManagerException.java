package br.com.SmallManager.infra.exception;


import br.com.SmallManager.records.ExceptionResponseDTO;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class SmallManagerException {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity exception400(MethodArgumentNotValidException ex){
        List<FieldError> erros = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(erros.stream().map(ExceptionResponseDTO::new).toList());
    }

    @ExceptionHandler({BadCredentialsException.class, TokenExpiredException.class})
    public ResponseEntity exception403(BadCredentialsException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity exception404(){
        return ResponseEntity.notFound().build();
    }




}
