package vin.pth.base.pojo;

import java.util.Collection;

public interface Authentication {

  /**
   * 主要内容,用户定位用户
   *
   * @return String
   */
  String getPrincipal();

  /**
   * 凭证,用户的认证凭证
   *
   * @return String
   */
  String getCredentials();

  /**
   * 用户信息
   *
   * @return Ojbect
   */
  Object getDetails();

  /**
   * 是认证验通过
   *
   * @return true=通过
   */
  boolean isAuthenticated();

  /**
   * 用户具备的权限
   *
   * @return GrantedAuthority
   */
  Collection<? extends GrantedAuthority> getAuthorities();


}
