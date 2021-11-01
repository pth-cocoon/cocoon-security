package vin.pth.example.security.authorization.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import vin.pth.authorization.password.BCryptPasswordEncoder;
import vin.pth.base.password.PasswordEncoder;

@ComponentScan(basePackages = "vin.pth.authorization.**")
@Configuration
public class AuthorizationConfig {

  @ConditionalOnMissingBean(PasswordEncoder.class)
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  public static void main(String[] args) {
    System.out.println(new BCryptPasswordEncoder().encode("admin"));
  }

}
