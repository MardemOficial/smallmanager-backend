package br.com.SmallManager.controller;

import br.com.SmallManager.model.SystemUser;
import br.com.SmallManager.infra.security.TokenService;
import br.com.SmallManager.records.DataTokenDTO;
import br.com.SmallManager.records.LoginUserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authorization")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody @Valid LoginUserDTO loginUserDTO){
        var authenticationToken = new UsernamePasswordAuthenticationToken(loginUserDTO.name(), loginUserDTO.password());
        var authentication = authenticationManager.authenticate(authenticationToken);

        SystemUser systemUser = new SystemUser();
        BeanUtils.copyProperties(authentication.getPrincipal(), systemUser);
        String tokenAcess = tokenService.generateToken(systemUser);
        String refreshToken = tokenService.generateRefreshToken(systemUser);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new DataTokenDTO(tokenAcess, refreshToken));
    }
}
