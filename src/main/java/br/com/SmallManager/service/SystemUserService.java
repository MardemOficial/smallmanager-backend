package br.com.SmallManager.service;

import br.com.SmallManager.model.SystemUser;
import br.com.SmallManager.repository.SystemUserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SystemUserService implements UserDetailsService {

    private final SystemUserRepository systemUserRepository;
    private final PasswordEncoder passwordEncoder;

    public SystemUserService(SystemUserRepository systemUserRepository, PasswordEncoder passwordEncoder) {
        this.systemUserRepository = systemUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(SystemUser systemUser){
        encoderPassword(systemUser);
        systemUserRepository.save(systemUser);
    }

    @Override
    public SystemUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return systemUserRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Erro na busca do usuário"));
    }

    public SystemUser loadUserByUsernameAndPassword(String name, String password) {
        return systemUserRepository.findByNameAndPassword(name, password)
                .orElseThrow(() -> new UsernameNotFoundException("Erro na busca do usuário"));
    }

    public void encoderPassword(SystemUser systemUser){
        var password = passwordEncoder.encode(systemUser.getPassword());
        systemUser.setPassword(password);
    }

}
