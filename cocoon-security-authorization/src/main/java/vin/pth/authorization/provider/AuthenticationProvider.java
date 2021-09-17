package vin.pth.authorization.provider;

import vin.pth.base.exception.authentication.AuthenticationException;
import vin.pth.base.pojo.Authentication;

public interface AuthenticationProvider {

  /**
   * 认证
   *
   * @param authentication 认证对象
   */
  Authentication authenticate(Authentication authentication) throws AuthenticationException;

  /**
   * 是否支持该认证对象
   */
  boolean supports(Class<?> authentication);

}