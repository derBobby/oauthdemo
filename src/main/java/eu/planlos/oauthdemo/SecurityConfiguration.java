package eu.planlos.oauthdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

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

        httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(c -> c.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/actuator/health", "/favicon.ico", "/error", "/webjars/**").permitAll()
                        .requestMatchers("/oauth**").hasAuthority("OIDC_USER")
                        .requestMatchers("/admin").hasRole("admin")
                        .anyRequest().authenticated()
                )
                // Send 401 instead of redirecting
                // - https://spring.io/guides/tutorials/spring-boot-oauth2#github-register-application
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .logout(l -> l
                                // .logoutUrl("/logout")
                                .logoutSuccessUrl("/").permitAll()
                        //TODO how to logout globally?
                )
                .oauth2Login(Customizer.withDefaults())
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

        return httpSecurity.build();
    }
}
