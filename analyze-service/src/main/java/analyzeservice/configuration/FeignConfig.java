package analyzeservice.configuration;

import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

public class FeignConfig {
    @Bean
    public RequestInterceptor authForwardInterceptor() {

        return template -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication != null && authentication.getCredentials() != null) {
                template.header("Authorization", "Bearer " + authentication.getCredentials());
            }
        };
    }
}
