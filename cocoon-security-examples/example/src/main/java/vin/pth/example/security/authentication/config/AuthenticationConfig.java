package vin.pth.example.security.authentication.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "vin.pth.authentication.**")
@Configuration
public class AuthenticationConfig {

}
