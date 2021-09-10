package vin.pth.authentication.manager;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vin.pth.authentication.provider.AuthenticationMatcher;
import vin.pth.base.exception.authentication.AuthenticationException;
import vin.pth.base.pojo.Authentication;

@RequiredArgsConstructor
@Component
public class TokenMatcherManager {

  private final List<AuthenticationMatcher> matcherList;

  public Authentication getTokenByParam(Map<String, String> param) {
    for (AuthenticationMatcher provider : matcherList) {
      Authentication authentication = provider.match(param);
      if (authentication != null) {
        return authentication;
      }
    }
    throw new AuthenticationException("认证参数非法");
  }

}
