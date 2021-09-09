package vin.pth.authentication.provider;

import java.util.Map;
import vin.pth.base.pojo.Authentication;

public interface TokenProvider {

  Authentication match(Map<String, String> param);


}
