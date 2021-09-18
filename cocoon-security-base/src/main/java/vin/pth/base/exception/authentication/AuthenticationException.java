package vin.pth.base.exception.authentication;

import lombok.Getter;
import lombok.Setter;

/**
 * 鉴权异常
 */
@Getter
@Setter
public class AuthenticationException extends RuntimeException {

  private String message;

  public AuthenticationException(String message) {
    super(message);
    this.message = message;
  }
}
