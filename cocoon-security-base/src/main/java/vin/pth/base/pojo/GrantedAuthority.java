package vin.pth.base.pojo;

import java.io.Serializable;
import java.util.Set;

/**
 * 授予的权限
 */
public interface GrantedAuthority extends Serializable {


  Set<String> getMethodSet();

  String getUri();

}