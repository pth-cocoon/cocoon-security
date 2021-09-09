package vin.pth.authentication.provider;

import vin.pth.base.exception.authentication.AuthenticationException;
import vin.pth.base.pojo.Authentication;

public interface AuthenticationProvider {

  Authentication authenticate(Authentication authentication) throws AuthenticationException;

  boolean supports(Class<?> authentication);

}