package vin.pth.authorization.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import vin.pth.authorization.manager.AuthenticationManager;
import vin.pth.authorization.manager.TokenMatcherManager;
import vin.pth.base.exception.authentication.AuthenticationException;
import vin.pth.base.pojo.Authentication;

@RequiredArgsConstructor
public abstract class AbstractAuthorizationFilter implements Filter {

  private final ObjectMapper objectMapper = new ObjectMapper();

  private final TokenMatcherManager tokenMatcherManager;
  private final AuthenticationManager authenticationManager;

  protected boolean isLogin(HttpServletRequest request) {
    if (request.getMethod().equals(HttpMethod.POST.name())) {
      return "/login".equals(request.getRequestURI());
    }
    return false;
  }


  public Authentication attemptAuthentication(HttpServletRequest request)
    throws AuthenticationException, IOException {
    BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(),
      StandardCharsets.UTF_8));
    StringBuilder responseStrBuilder = new StringBuilder();
    String inputStr;
    while ((inputStr = streamReader.readLine()) != null) {
      responseStrBuilder.append(inputStr);
    }
    Map<String, String> params = objectMapper.readValue(responseStrBuilder.toString(), Map.class);

    return authenticationManager.authenticate(tokenMatcherManager.getTokenByParam(params));
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
    try {
      successHandle(httpServletRequest, httpServletResponse, attemptAuthentication(httpServletRequest));
    } catch (AuthenticationException e) {
      exceptionHandler(httpServletRequest, httpServletResponse, e);
    }
  }

  public abstract void exceptionHandler(HttpServletRequest request, HttpServletResponse response,
    AuthenticationException e);

  public abstract void successHandle(HttpServletRequest request, HttpServletResponse response,
    Authentication authenticate);
}
