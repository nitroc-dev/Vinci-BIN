package be.vinci.ipl.catflix.authentication;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "be.vinci.ipl.catflix.authentication")
public class AuthenticationProperties {
    private String secret;
}
