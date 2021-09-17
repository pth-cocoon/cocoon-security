package vin.pth.base.service;


import vin.pth.base.pojo.UserDetails;

public interface UserDetailsService {

  UserDetails loadUserByUsername(String username);

  UserDetails getByToken(String token);

  UserDetails setByToken(UserDetails userDetails, String token);
}
