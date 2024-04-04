package eu.planlos.oauthdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Sources:
 * - https://medium.com/@iyusubov444/springboot3-oauth2-login-default-config-part-1-c35ca2934818
 * - https://www.baeldung.com/spring-deprecated-websecurityconfigureradapter
 * - https://spring.io/guides/tutorials/spring-boot-oauth2
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/actuator/health", "/favicon.ico").permitAll()
                        .requestMatchers("/oauth").hasAuthority("OIDC_USER")
                        .anyRequest().authenticated()
                ).oauth2Login(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
