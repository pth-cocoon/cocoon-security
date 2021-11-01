package vin.pth.example.security.authorization.service;

import org.springframework.stereotype.Service;
import vin.pth.base.exception.authentication.AuthenticationException;
import vin.pth.base.exception.authorization.AuthorizationException;
import vin.pth.base.pojo.UserDetails;
import vin.pth.base.service.UserDetailsService;
import vin.pth.example.security.authorization.storage.AuthUserStorage;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


  @Override
  public UserDetails loadUserByUsername(String username) {
    return AuthUserStorage.getByUsername(username);
  }

  @Override
  public UserDetails getByToken(String token) throws AuthenticationException {
    return AuthUserStorage.getByUsername(token);
  }

  @Override
  public String generateToken(UserDetails userDetails) throws AuthorizationException {
    return userDetails.getUsername();
  }


}
