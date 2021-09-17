package vin.pth.base.pojo;

import java.io.Serializable;
import java.util.Collection;

public interface UserDetails {

  Serializable getUserId();

  String getUsername();

  String getPassword();

  Collection<? extends GrantedAuthority> getAuthority();

}
