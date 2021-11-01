package vin.pth.example.security.authorization.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import vin.pth.base.pojo.AuthUser;
import vin.pth.base.pojo.UserDetails;

public class AuthUserStorage {

  private static Map<String, UserDetails> userDetailsMap = new HashMap<>();

  static {
    AuthUser admin = new AuthUser();
    admin.setUsername("admin");
    admin.setPassword("$2a$10$MgOrpqBoxaxU4s7BUKB3k.zoI2U5XQxRvuVser0MfSGVnZzqezj4.");
    admin.setAuthority(new ArrayList<>());
    admin.setUserId(1);

    AuthUser anonymous = new AuthUser();
    anonymous.setUserId(2);
    anonymous.setUsername("anonymous");
    anonymous.setPassword("anonymous");
    anonymous.setAuthority(new ArrayList<>());

    userDetailsMap.put("admin",admin);
    userDetailsMap.put("anonymous",anonymous);
  }

  public static UserDetails getByUsername(String username) {
    return userDetailsMap.get(username);
  }

}
