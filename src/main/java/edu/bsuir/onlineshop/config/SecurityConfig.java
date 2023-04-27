package edu.bsuir.onlineshop.config;

import edu.bsuir.onlineshop.domain.entity.enums.RoleType;
import edu.bsuir.onlineshop.web.security.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    @SneakyThrows
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtFilter jwtFilter) {
        httpSecurity
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST,
                        "/api/v0/authenticate",
                        "/api/v0/register"
                ).permitAll()
                .requestMatchers(HttpMethod.GET,
                        "/api/v0/categories/**",
                        "/api/v0/products/**",
                        "/api/v0/reviews/**",
                        "/api/v0/brands/**"
                ).permitAll()
                .requestMatchers(HttpMethod.GET,
                        "/api/v0/orders/**",
                        "/api/v0/users/**"
                ).fullyAuthenticated()
                .requestMatchers(HttpMethod.POST,
                        "/api/v0/reviews",
                        "/api/v0/orders"
                ).fullyAuthenticated()
                .requestMatchers(HttpMethod.POST,
                        "/api/v0/categories",
                        "/api/v0/products",
                        "/api/v0/brands"
                ).hasRole(RoleType.ADMIN.name())
                .requestMatchers(HttpMethod.PUT,
                        "/api/v0/reviews",
                        "/api/v0/orders"
                ).hasRole(RoleType.USER.name())
                .requestMatchers(HttpMethod.PUT,
                        "/api/v0/categories/**",
                        "/api/v0/products/**",
                        "/api/v0/brands/**"
                ).hasRole(RoleType.ADMIN.name())
                .requestMatchers(HttpMethod.DELETE,
                        "/api/v0/reviews",
                        "/api/v0/orders"
                ).hasRole(RoleType.USER.name())
                .requestMatchers(HttpMethod.DELETE,
                        "/api/v0/categories/**",
                        "/api/v0/products/**",
                        "/api/v0/brands/**"
                ).hasRole(RoleType.ADMIN.name())
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    @SneakyThrows
    public AuthenticationManager authenticationManager(
            HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService) {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}
