package vin.pth.authentication.service;

import javax.servlet.http.HttpServletRequest;
import vin.pth.base.exception.authentication.AuthenticationException;
import vin.pth.base.pojo.UserDetails;

/**
 * RBAC权限控制服务
 */
public interface RbacService {

  /**
   * 判断用户是否有权限访问服务
   *
   * @param request 请求，通常可以通过request获取URL，Method等信息
   */
  void checkPermission(HttpServletRequest request, UserDetails userDetails) throws AuthenticationException;

}
