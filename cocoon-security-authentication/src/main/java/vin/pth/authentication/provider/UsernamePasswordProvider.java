package vin.pth.authentication.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vin.pth.authentication.token.UsernamePasswordToken;
import vin.pth.base.exception.authentication.AuthenticationException;
import vin.pth.base.pojo.Authentication;
import vin.pth.base.pojo.UserDetails;
import vin.pth.base.password.PasswordEncoder;
import vin.pth.base.service.UserDetailsService;

@RequiredArgsConstructor
@Component
public class UsernamePasswordProvider implements AuthenticationProvider {

  private final PasswordEncoder passwordEncoderService;
  private final UserDetailsService userDetailsService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    UsernamePasswordToken token = (UsernamePasswordToken) authentication;
    String username = token.getUsername();
    String password = token.getPassword();
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    if (passwordEncoderService.matches(password, userDetails.getPassword())) {
      token.setAuthenticated(true);
      token.setUserDetails(userDetails);
      token.setAuthorities(userDetails.getAuthority());
      return token;
    }
    throw new AuthenticationException("用户名密码不匹配");

  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordToken.class.isAssignableFrom(authentication);
  }
}
