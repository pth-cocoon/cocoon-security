package vin.pth.example.security.authorization.storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import vin.pth.base.pojo.AuthUser;
import vin.pth.base.pojo.GrantedAuthority;
import vin.pth.base.pojo.UserDetails;

public class AuthUserStorage {

  private static final Map<String, UserDetails> userDetailsMap = new HashMap<>();

  static {
    AuthUser admin = new AuthUser();
    admin.setUsername("admin");
    admin.setPassword("$2a$10$MgOrpqBoxaxU4s7BUKB3k.zoI2U5XQxRvuVser0MfSGVnZzqezj4.");
    admin.setAuthority(new ArrayList<>());
    admin.getAuthority().add(buildAuthority("/admin/**", "GET", "POST"));
    admin.setUserId(1);

    AuthUser anonymous = new AuthUser();
    anonymous.setUserId(2);
    anonymous.setUsername("anonymous");
    anonymous.setPassword("anonymous");
    anonymous.setAuthority(new ArrayList<>());
    anonymous.getAuthority().add(buildAuthority("/anonymous/**", "GET", "POST"));

    userDetailsMap.put("admin", admin);
    userDetailsMap.put("anonymous", anonymous);
  }

  public static UserDetails getByUsername(String username) {
    return userDetailsMap.get(username);
  }

  private static GrantedAuthority buildAuthority(String uri, String... method) {
    return new GrantedAuthority() {
      @Override
      public Set<String> getMethodSet() {
        return new HashSet<>(Arrays.asList(method));
      }

      @Override
      public String getUri() {
        return uri;
      }
    };
  }

}
