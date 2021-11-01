package vin.pth.example.security.authorization.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vin.pth.base.exception.authentication.AuthenticationException;
import vin.pth.base.pojo.UserDetails;
import vin.pth.example.security.authorization.storage.AuthUserStorage;

@RequiredArgsConstructor
@Service
public class TokenService {


  private String generateToken() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }

  public String generateToken(UserDetails userDetails) throws JsonProcessingException {
    return userDetails.getUsername();
  }

  public String generateToken(String token, UserDetails userDetails) throws JsonProcessingException {
    return userDetails.getUsername();
  }

  public UserDetails getUserByToken(String token) throws JsonProcessingException, AuthenticationException {
    UserDetails userDetails = AuthUserStorage.getByUsername(token);
    if (userDetails != null) {
      return userDetails;
    }
    throw new AuthenticationException("token无效");
  }

}
