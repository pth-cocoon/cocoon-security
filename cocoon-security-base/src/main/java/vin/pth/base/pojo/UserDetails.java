package vin.pth.base.pojo;

import java.util.Collection;

public interface UserDetails {

  String getUsername();
  String getPassword();

  Collection<? extends GrantedAuthority> getAuthority();

}
