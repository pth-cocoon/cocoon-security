package vin.pth.base.exception.authentication;

/**
 * 鉴权异常
 */
public class AuthenticationException extends RuntimeException {

  public AuthenticationException(String message) {
    super(message);
  }
}
