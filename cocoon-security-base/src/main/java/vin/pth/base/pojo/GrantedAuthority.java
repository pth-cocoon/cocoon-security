package vin.pth.base.pojo;

import java.io.Serializable;

/**
 * 授予的权限
 */
public interface GrantedAuthority extends Serializable {

  String getAuthority();

}