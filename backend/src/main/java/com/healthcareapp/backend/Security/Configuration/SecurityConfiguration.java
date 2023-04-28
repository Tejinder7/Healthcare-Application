package com.healthcareapp.backend.Security.Configuration;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf()
                .disable()
                .cors()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/superadmin/**")
                .hasAuthority("SCOPE_ROLE_SUPER_ADMIN")
                .requestMatchers("/admin/**")
                .hasAuthority("SCOPE_ROLE_ADMIN")
                .requestMatchers("/supervisor/**")
                .hasAuthority("SCOPE_ROLE_SUPERVISOR")
                .requestMatchers("/doctor/**")
                .hasAuthority("SCOPE_ROLE_DOCTOR")
                .requestMatchers("/frontdesk/**")
                .hasAuthority("SCOPE_ROLE_FRONT_DESK")
                .requestMatchers("/fieldworker/**")
                .hasAuthority("SCOPE_ROLE_FIELD_WORKER")
                .requestMatchers("/login/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

        return http.build();
    }
}
