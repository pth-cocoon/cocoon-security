package vin.pth.authorization.manager;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vin.pth.authorization.provider.AuthenticationProvider;
import vin.pth.base.exception.authentication.AuthenticationException;
import vin.pth.base.pojo.Authentication;

@RequiredArgsConstructor
@Component
public class AuthenticationManager {

  private final List<AuthenticationProvider> matcherList;

  public Authentication authenticate(Authentication authentication) {
    for (AuthenticationProvider provider : matcherList) {
      if (provider.supports(authentication.getClass())) {
        return provider.authenticate(authentication);
      }
    }
    throw new AuthenticationException("没有匹配的Provider");
  }


}
