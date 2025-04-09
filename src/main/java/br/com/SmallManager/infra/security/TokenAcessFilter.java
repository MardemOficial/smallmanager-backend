package br.com.SmallManager.infra.security;

import br.com.SmallManager.model.SystemUser;
import br.com.SmallManager.repository.SystemUserRepository;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class TokenAcessFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final SystemUserRepository systemUserRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            String token = getTokenRequest(request);

            if(token != null){

                String name = tokenService.verifyToken(token).orElseThrow();

                SystemUser systemUser = systemUserRepository.findByName(name).get();

                Authentication authentication = new UsernamePasswordAuthenticationToken(systemUser, null, systemUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }

            filterChain.doFilter(request, response);

        }catch (JWTVerificationException | JWTCreationException jwt){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.getWriter().write(jwt.getMessage());
        }

    }

    private String getTokenRequest(HttpServletRequest request) {
        var autorizationHeader =  request.getHeader("Authorization");

        if(autorizationHeader != null){
          return  autorizationHeader.replace("Bearer ","");
        }
        return null;
    }
}
