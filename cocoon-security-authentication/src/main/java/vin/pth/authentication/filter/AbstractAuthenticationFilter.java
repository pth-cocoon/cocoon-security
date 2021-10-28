package vin.pth.authentication.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import vin.pth.authentication.service.RbacService;
import vin.pth.base.context.UserDetailsContext;
import vin.pth.base.exception.authentication.AuthenticationException;
import vin.pth.base.pojo.UserDetails;
import vin.pth.base.service.UserDetailsService;


public abstract class AbstractAuthenticationFilter implements Filter {

  private static final String TOKEN_KEY = "token";

  private final UserDetailsService userDetailsService;
  private final RbacService rbacService;

  protected AbstractAuthenticationFilter(UserDetailsService userDetailsService,
    RbacService rbacService) {
    this.userDetailsService = userDetailsService;
    this.rbacService = rbacService;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String token = httpRequest.getHeader(TOKEN_KEY);
    try {
      if (StringUtils.hasText(token)) {
        UserDetails userDetails = userDetailsService.getByToken(token);
        UserDetailsContext.setUserDetails(userDetails);
      }
      rbacService.checkPermission(httpRequest, UserDetailsContext.getUserDetails());
      chain.doFilter(request, response);
    } catch (AuthenticationException e) {
      fail((HttpServletRequest) request, (HttpServletResponse) response, e);
    } finally {
      // 无论过程如何，都需要清理掉当前线程变量中的用户信息
//      UserDetailsContext.clear();
      System.out.println(UserDetailsContext.getUserDetails());
    }
  }

  protected abstract void fail(HttpServletRequest request, HttpServletResponse response, AuthenticationException e);
}
