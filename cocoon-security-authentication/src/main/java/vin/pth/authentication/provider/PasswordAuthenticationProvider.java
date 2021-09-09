package vin.pth.authentication.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import vin.pth.authentication.token.UsernamePasswordToken;
import vin.pth.base.exception.authentication.AuthenticationException;
import vin.pth.base.pojo.Authentication;
import vin.pth.base.pojo.UserDetails;
import vin.pth.base.service.PasswordEncoderService;
import vin.pth.base.service.UserDetailsService;

/**
 * @author xce
 * @date 2020/1/8  10:39
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PasswordAuthenticationProvider implements AuthenticationProvider {

  private final UserDetailsService userDetailsService;

  private final PasswordEncoderService passwordEncoderService;


  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    UsernamePasswordToken token = (UsernamePasswordToken) authentication;
    if (!StringUtils.hasText(token.getUsername())) {
      log.warn("用户名不允许为空!");
      throw new AuthenticationException("用户名不允许为空！");
    }
    UserDetails userDetails = userDetailsService.loadUserByUsername(token.getUsername());
    if (userDetails == null) {
      log.warn("未找到该用户{}!", token.getUsername());
      throw new AuthenticationException("未找到该用户！");
    }
    if (!passwordEncoderService.matches(authentication.getCredentials(), userDetails.getPassword())) {
      log.warn("密码错误!");
      throw new AuthenticationException("密码错误，请重新输入！");
    }
    token.setUserInfo(userDetails);
    token.setAuthenticated(true);
    return authentication;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordToken.class.isAssignableFrom(authentication);
  }
}
