package <%= packageName %>.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import <%= packageName %>.infrastructure.adapters.repositories.AuthRepository;
import <%= packageName %>.infrastructure.adapters.filter.AuthEntryPointJwt;
import <%= packageName %>.infrastructure.adapters.filter.AuthTokenFilter;
import <%= packageName %>.domain.adapters.services.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {
  
  private AuthEntryPointJwt unauthorizedHandler;

  private AuthRepository authRepository;

  @Bean
  public UserDetailsServiceImpl userDetailsService() {
      return new UserDetailsServiceImpl(authRepository);
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)
      throws Exception {
      return authConfig.getAuthenticationManager();
  }
  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
      return new AuthTokenFilter();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

      authProvider.setUserDetailsService(userDetailsService());
      authProvider.setPasswordEncoder(passwordEncoder());

      return authProvider;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http.cors()
              .and()
              .csrf()
              .disable()
              .exceptionHandling()
              .authenticationEntryPoint(unauthorizedHandler)
              .and()
              .sessionManagement()
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              .and()
              .authorizeRequests()
              .requestMatchers(
                      "/api/auth",
                      "/api/auth/**",
                      "/registration/register",
                      "/swagger-ui/**",
                      "/swagger-ui.html",
                      "/v3/api-docs/**",
                      "/api/test/**")
              .permitAll()
              .anyRequest()
              .authenticated();

      http.authenticationProvider(authenticationProvider());

      http.addFilterBefore(
              authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

      return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
}
