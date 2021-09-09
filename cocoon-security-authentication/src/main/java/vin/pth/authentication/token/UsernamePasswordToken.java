package vin.pth.authentication.token;


import java.util.Collection;
import lombok.Getter;
import lombok.Setter;
import vin.pth.base.pojo.GrantedAuthority;

@Getter
@Setter
public class UsernamePasswordToken extends AuthenticationToken {

  private String username;
  private String password;
  private Object userInfo;
  private Collection<? extends GrantedAuthority> authorities;
  private boolean authenticated;


  @Override
  public String getPrincipal() {
    return username;
  }

  @Override
  public String getCredentials() {
    return password;
  }

  @Override
  public Object getDetails() {
    return username;
  }

}
