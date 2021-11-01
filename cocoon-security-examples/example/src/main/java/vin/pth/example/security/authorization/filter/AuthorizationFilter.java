package vin.pth.example.security.authorization.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import vin.pth.authorization.filter.AbstractAuthorizationFilter;
import vin.pth.authorization.manager.AuthenticationManager;
import vin.pth.authorization.manager.TokenMatcherManager;
import vin.pth.base.exception.authentication.AuthenticationException;
import vin.pth.base.pojo.Authentication;
import vin.pth.base.pojo.UserDetails;
import vin.pth.example.security.authorization.service.TokenService;

@Order(1)
@Slf4j
@Component
public class AuthorizationFilter extends AbstractAuthorizationFilter {

  @Resource
  private TokenService tokenService;

  public AuthorizationFilter(TokenMatcherManager tokenMatcherManager, AuthenticationManager authenticationManager) {
    super(tokenMatcherManager, authenticationManager);
  }

  @Override
  protected boolean isLogin(HttpServletRequest request) {
    log.info(request.getRequestURI());
    return super.isLogin(request);
  }

  @SneakyThrows
  @Override
  public void exceptionHandler(HttpServletRequest request, HttpServletResponse response,
    AuthenticationException e) {
    log.error("登陆失败");
    Map<String, Object> resp = new HashMap<>();
    resp.put("code", 1);
    resp.put("message", e.getMessage());
    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.getWriter().write(new ObjectMapper().writeValueAsString(resp));

  }

  @SneakyThrows
  @Override
  public void successHandle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
    UserDetails userDetails = authentication.getUserDetails();
    Map<String, Object> resp = new HashMap<>();
    resp.put("code", 0);
    resp.put("message", "成功");
    resp.put("data", tokenService.generateToken(userDetails));
    response.setContentType("application/json;charset=UTF-8");
    response.getWriter().write(new ObjectMapper().writeValueAsString(resp));
    log.info("{} 用户登陆成功！", userDetails.getUsername());
  }
}
