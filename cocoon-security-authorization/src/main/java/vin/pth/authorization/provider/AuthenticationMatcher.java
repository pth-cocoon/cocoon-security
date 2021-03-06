package vin.pth.authorization.provider;

import java.util.Map;
import vin.pth.base.pojo.Authentication;

public interface AuthenticationMatcher {

  /**
   * 匹配参数，如果匹配到则返回 Authentication 对象，匹配不到则返回null
   *
   * @param param 参数
   */
  Authentication match(Map<String, String> param);


}
