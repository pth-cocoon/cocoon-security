package vin.pth.authentication.service;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import vin.pth.base.exception.authentication.AuthenticationException;
import vin.pth.base.pojo.GrantedAuthority;
import vin.pth.base.pojo.UserDetails;

@Slf4j
@Service
public class RbacDefaultService implements RbacService {

  private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();
  private static final String ADMIN_USERNAME = "admin";


  @Override
  public void checkPermission(HttpServletRequest request, UserDetails userDetails) throws AuthenticationException {

    log.info("权限校验");
    if (StringUtils.hasText(userDetails.getUsername()) && ADMIN_USERNAME.equals(userDetails.getUsername())) {
      log.warn("超管账号无条件放行");
      return;
    }
    for (GrantedAuthority authority : userDetails.getAuthority()) {
      if (ANT_PATH_MATCHER.match(authority.getUri(), request.getRequestURI())) {
        if (authority.getMethodSet().contains(request.getMethod())) {
          return;
        }
      }
    }
    throw new AuthenticationException("您没有权限");

  }
}
