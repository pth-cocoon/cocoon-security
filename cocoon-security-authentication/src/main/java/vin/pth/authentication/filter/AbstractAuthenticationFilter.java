package vin.pth.authentication.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import vin.pth.authentication.factory.TokenFactory;
import vin.pth.authentication.provider.AuthenticationProvider;
import vin.pth.base.exception.authentication.AuthenticationException;
import vin.pth.base.pojo.Authentication;

public abstract class AbstractAuthenticationFilter implements Filter {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Autowired(required = false)
  private List<AuthenticationProvider> providerList;

  @Resource
  private TokenFactory tokenFactory;

  protected boolean isLogin(HttpServletRequest request) {
    if (request.getMethod().equals(HttpMethod.POST.name())) {
      return "/login".equals(request.getRequestURI());
    }
    return false;
  }


  public Authentication authenticate(Authentication authentication) {
    for (AuthenticationProvider provider : providerList) {
      if (!provider.supports(authentication.getClass())) {
        continue;
      }
      return provider.authenticate(authentication);

    }
    throw new AuthenticationException("没有支持的Provider");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
    if (!isLogin(httpServletRequest)) {
      chain.doFilter(request, response);
      return;
    }
    BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(),
      StandardCharsets.UTF_8));
    StringBuilder responseStrBuilder = new StringBuilder();
    String inputStr;
    while ((inputStr = streamReader.readLine()) != null) {
      responseStrBuilder.append(inputStr);
    }
    Map<String, String> params = objectMapper.readValue(responseStrBuilder.toString(), Map.class);
    successHandle(httpServletRequest, httpServletResponse, authenticate(tokenFactory.getTokenByParam(params)));
  }

  public abstract void exceptionHandler(HttpServletRequest request, HttpServletResponse response);

  public abstract void successHandle(HttpServletRequest request, HttpServletResponse response,
    Authentication authenticate);
}
