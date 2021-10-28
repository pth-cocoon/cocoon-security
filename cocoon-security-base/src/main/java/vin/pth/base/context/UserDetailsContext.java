package vin.pth.base.context;

import vin.pth.base.pojo.UserDetails;

public class UserDetailsContext {

  /**
   * 使用可以被继承的线程变量，保证子进程可以获取到当前用户信息
   */
  private static final ThreadLocal<UserDetails> LOCAL_USER_DETAILS = new InheritableThreadLocal<>();

  /**
   * 获取当前用户信息
   *
   * @return UserDetails
   */
  public static UserDetails getUserDetails() {
    return LOCAL_USER_DETAILS.get();
  }

  /**
   * 设置用户信息
   *
   * @param userDetails 用户信息
   */
  public static void setUserDetails(UserDetails userDetails) {
    LOCAL_USER_DETAILS.set(userDetails);
  }

  /**
   * 从当前线程变量中，清除用户信息
   */
  public static void clear() {
    LOCAL_USER_DETAILS.remove();
  }

}