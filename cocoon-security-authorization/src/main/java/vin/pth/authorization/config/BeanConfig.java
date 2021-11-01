package vin.pth.authorization.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vin.pth.authorization.password.BCryptPasswordEncoder;
import vin.pth.base.password.PasswordEncoder;

@Configuration
public class BeanConfig {

  @ConditionalOnMissingBean(PasswordEncoder.class)
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
