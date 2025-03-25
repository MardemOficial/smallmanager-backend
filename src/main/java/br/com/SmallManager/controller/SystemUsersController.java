package br.com.SmallManager.controller;

import br.com.SmallManager.model.SystemUser;
import br.com.SmallManager.records.SystemUserDTO;
import br.com.SmallManager.service.SystemUserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class SystemUsersController {

    private final SystemUserService systemUserService;

    private SystemUser systemUser = new SystemUser();

    public SystemUsersController(SystemUserService systemUserService, AuthenticationManager authenticationManager) {
        this.systemUserService = systemUserService;
    }

    @PostMapping
    public ResponseEntity<SystemUser> createUser(@RequestBody @Valid SystemUserDTO systemUserDTO){

        BeanUtils.copyProperties(systemUserDTO, systemUser);
        systemUserService.save(systemUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(systemUser);
    }

    @GetMapping("{name}")
    public ResponseEntity<SystemUser> getUser(@PathVariable(value = "name") String name, @AuthenticationPrincipal SystemUser systemUser){

        systemUser = systemUserService.loadUserByUsername(name);

        if(Optional.of(systemUser).isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(systemUser);
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(systemUser);
    }

}
