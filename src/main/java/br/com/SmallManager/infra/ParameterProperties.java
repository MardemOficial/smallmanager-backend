package br.com.SmallManager.infra;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ParameterProperties {

    @Value("${smallmanager.token}")
    private String tokenApplication;

    @Value("${smallmanager.expiretokentime}")
    private Long expireTokenTime;

    @Value("${smallmanager.expirerefreshtokentime}")
    private Long expireRefreshtokenTime;

}
