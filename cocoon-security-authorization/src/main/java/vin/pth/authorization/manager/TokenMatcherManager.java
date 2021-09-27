package vin.pth.authorization.manager;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vin.pth.authorization.provider.AuthenticationMatcher;
import vin.pth.base.exception.authentication.AuthenticationException;
import vin.pth.base.pojo.Authentication;

@RequiredArgsConstructor
@Component
public class TokenMatcherManager {

  private final List<AuthenticationMatcher> matcherList;

  public Authentication getTokenByParam(Map<String, String> param) throws AuthenticationException {
    for (AuthenticationMatcher provider : matcherList) {
      Authentication authentication = provider.match(param);
      if (authentication != null) {
        return authentication;
      }
    }
    throw new AuthenticationException("认证参数非法");
  }

}
