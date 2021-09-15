package vin.pth.base.password;

public interface PasswordEncoder {

  String encode(CharSequence rawPassword);

  boolean matches(CharSequence rawPassword, String encodedPassword);

}
