package vin.pth.base.context;

import vin.pth.base.pojo.UserDetails;

public class UserDetailsContext {

  private static final ThreadLocal<UserDetails> LOCAL_USER_DETAILS = new ThreadLocal<>();

  public static UserDetails getUserDetails() {
    return LOCAL_USER_DETAILS.get();
  }

  public static void setUserDetails(UserDetails userDetails) {
    LOCAL_USER_DETAILS.set(userDetails);
  }

  public static void clear() {
    LOCAL_USER_DETAILS.remove();
  }

}