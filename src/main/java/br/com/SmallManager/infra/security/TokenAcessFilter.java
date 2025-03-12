package br.com.SmallManager.infra.security;

import br.com.SmallManager.domain.SystemUser;
import br.com.SmallManager.repository.SystemUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.http.HttpTimeoutException;

@Component
public class TokenAcessFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final SystemUserRepository systemUserRepository;

    public TokenAcessFilter(TokenService tokenService, SystemUserRepository systemUserRepository) {
        this.tokenService = tokenService;
        this.systemUserRepository = systemUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenRequest(request);

        if(token != null){
            String name = tokenService.verifyToken(token).orElseThrow();

            if(name.contains("expired")){
                throw new HttpTimeoutException("Requisição expirou!");
            }

            SystemUser systemUser = systemUserRepository.findByName(name).get();

            Authentication authentication = new UsernamePasswordAuthenticationToken(systemUser, null, systemUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        filterChain.doFilter(request, response);
    }

    private String getTokenRequest(HttpServletRequest request) {
        var autorizationHeader =  request.getHeader("Authorization");

        if(autorizationHeader != null){
          return  autorizationHeader.replace("Bearer ","");
        }
        return null;
    }
}
