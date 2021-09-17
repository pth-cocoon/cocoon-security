package vin.pth.authorization.token;


import java.util.Collection;
import lombok.Getter;
import lombok.Setter;
import vin.pth.base.pojo.Authentication;
import vin.pth.base.pojo.GrantedAuthority;
import vin.pth.base.pojo.UserDetails;

@Getter
@Setter
public class UsernamePasswordToken implements Authentication {

  private final String username;
  private final String password;
  Collection<? extends GrantedAuthority> authorities;
  private UserDetails userDetails;
  private boolean authenticated;

  public UsernamePasswordToken(String username, String password) {
    this.username = username;
    this.password = password;
    this.authenticated = false;
  }

  @Override
  public String getPrincipal() {
    return username;
  }

  @Override
  public String getCredentials() {
    return password;
  }

}
