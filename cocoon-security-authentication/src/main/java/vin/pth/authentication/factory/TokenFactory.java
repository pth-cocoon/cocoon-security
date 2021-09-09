package vin.pth.authentication.factory;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vin.pth.authentication.provider.TokenProvider;
import vin.pth.base.exception.authentication.AuthenticationException;
import vin.pth.base.pojo.Authentication;

@Component
public class TokenFactory {

  @Autowired(required = false)
  private List<TokenProvider> providerList;

  public Authentication getTokenByParam(Map<String, String> param) {
    for (TokenProvider provider : providerList) {
      Authentication authentication = provider.match(param);
      if (authentication != null) {
        return authentication;
      }
    }
    throw new AuthenticationException("认证参数非法");
  }

}
