package vin.pth.authentication.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
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
    UserDetails userDetails = userDetailsService.getByToken(token);
    try {
      rbacService.checkPermission(httpRequest, userDetails);
      UserDetailsContext.setUserDetails(userDetails);
      chain.doFilter(request, response);
      UserDetailsContext.clear();
    } catch (AuthenticationException e) {
      fail((HttpServletRequest) request, (HttpServletResponse) response, e);
    }

  }

  protected abstract void fail(HttpServletRequest request, HttpServletResponse response, AuthenticationException e);
}
