package com.fatechjsc.identityservice.configurations;

import com.fatechjsc.identityservice.utils.EnumRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
    @Value("${api.prefix}")
    private String apiPrefix;
    private final AuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(
                                        String.format("%s/auth/register", apiPrefix),
                                        String.format("%s/auth/login", apiPrefix),
                                        String.format("%s/auth/validate", apiPrefix),
                                        String.format("%s/auth/csrf/get", apiPrefix)
                                )
                                .permitAll()
                                .requestMatchers(POST, String.format("%s/auth/update/**", apiPrefix)).hasAnyRole(EnumRole.ADMIN.name())
                                .requestMatchers(GET, String.format("%s/auth/find/**", apiPrefix)).hasAnyRole(EnumRole.ADMIN.name())
                                .anyRequest()
                                .authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .build();
    }
}
