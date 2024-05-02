package eu.planlos.oauthdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
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

    private final ClientRegistrationRepository clientRegistrationRepository;

    public SecurityConfiguration(ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .cors(Customizer.withDefaults())
//                .cors(c -> c.configurationSource(request -> {
//                    CorsConfiguration config = new CorsConfiguration();
//                    config.setAllowedOrigins(List.of("http://localhost:8080", "https://cloud.planlos.eu"));
//                    config.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS"));
//                    config.setAllowedHeaders(Arrays.asList("x-requested-with", "authorization", "content-type"));
//                    return config;
//                }))
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
                        .logoutSuccessHandler(oidcLogoutSuccessHandler())
//                        .logoutSuccessUrl("/").permitAll()
                )
                .oauth2Login(Customizer.withDefaults())
                .oidcLogout((logout) -> logout
                        .backChannel(Customizer.withDefaults())
                )
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

        return httpSecurity.build();
    }

    private LogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler =
                new OidcClientInitiatedLogoutSuccessHandler(this.clientRegistrationRepository);

        // Sets the location that the End-User's User Agent will be redirected to
        // after the logout has been performed at the Provider
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}");

        return oidcLogoutSuccessHandler;
    }
}
