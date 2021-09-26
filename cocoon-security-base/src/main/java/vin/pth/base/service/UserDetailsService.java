package vin.pth.base.service;


import vin.pth.base.exception.authentication.AuthenticationException;
import vin.pth.base.exception.authorization.AuthorizationException;
import vin.pth.base.pojo.UserDetails;

public interface UserDetailsService {

  /**
   * 通过用户名获取用户信息
   *
   * @param username 用户名
   * @return 用户信息
   */
  UserDetails loadUserByUsername(String username);

  /**
   * 通过Token获取用户信息
   *
   * @param token 令牌
   * @return 用户信息
   * @throws AuthenticationException 鉴权异常
   */
  UserDetails getByToken(String token) throws AuthenticationException;

  /**
   * 生成Token
   *
   * @param userDetails 用户信息
   * @return 生成的Token
   * @throws AuthorizationException 授权异常
   */
  String generateToken(UserDetails userDetails) throws AuthorizationException;
}
