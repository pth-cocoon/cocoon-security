package vin.pth.base.pojo;

import java.io.Serializable;
import java.util.Collection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUser implements UserDetails {

  private Serializable userId;
  private String username;
  private String password;
  private Collection<? extends GrantedAuthority> authority;


}
