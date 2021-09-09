package vin.pth.base.service;

public interface PasswordEncoderService {

  String encode(String rawPassword);

  boolean matches(String rawPassword, String encodedPassword);

}
