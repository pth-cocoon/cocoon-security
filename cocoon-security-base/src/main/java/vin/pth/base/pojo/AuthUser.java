package vin.pth.base.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthUser implements UserDetails {

  private Serializable userId;
  private String username;
  private String password;
  private Collection<? extends GrantedAuthority> authority;

  public static AuthUser anonymousUser() {
    AuthUser user = new AuthUser();
    user.setUserId("anonymous");
    user.setPassword("");
    user.setAuthority(new ArrayList<>());
    return user;
  }


}
