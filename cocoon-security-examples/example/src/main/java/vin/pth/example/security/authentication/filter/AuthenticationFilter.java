package vin.pth.example.security.authentication.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import vin.pth.authentication.filter.AbstractAuthenticationFilter;
import vin.pth.authentication.service.RbacService;
import vin.pth.base.exception.authentication.AuthenticationException;
import vin.pth.base.service.UserDetailsService;

@Order(99)
@Component
public class AuthenticationFilter extends AbstractAuthenticationFilter {


  protected AuthenticationFilter(UserDetailsService userDetailsService, RbacService rbacService) {
    super(userDetailsService, rbacService);
  }


  @SneakyThrows
  @Override
  protected void fail(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
    Map<String, Object> resp = new HashMap<>();
    resp.put("code", 0);
    resp.put("message", e.getMessage());
    response.setContentType("application/json;charset=UTF-8");
    response.getWriter().write(new ObjectMapper().writeValueAsString(resp));
    response.setStatus(HttpStatus.FORBIDDEN.value());
  }
}
