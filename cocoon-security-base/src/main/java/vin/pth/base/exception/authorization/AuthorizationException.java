package vin.pth.base.exception.authorization;

import lombok.Getter;
import lombok.Setter;

/**
 * 授权异常
 */
@Getter
@Setter
public class AuthorizationException extends Exception {

  private String message;

  public AuthorizationException(String message) {
    super(message);
    this.message = message;
  }
}
