package vin.pth.authorization.provider;

import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import vin.pth.authorization.token.UsernamePasswordToken;
import vin.pth.base.pojo.Authentication;

@Component
public class UsernamePasswordMatcher implements AuthenticationMatcher {

  @Override
  public Authentication match(Map<String, String> map) {
    String username = map.get("username");
    String password = map.get("password");
    if (StringUtils.hasText(username) && StringUtils.hasText(password)) {
      return new UsernamePasswordToken(username, password);
    }
    return null;
  }
}