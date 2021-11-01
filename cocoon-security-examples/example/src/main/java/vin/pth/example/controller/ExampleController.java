package vin.pth.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

  @GetMapping("/admin/get")
  public String adminGet() {
    return "success";
  }

  @GetMapping("/anonymous/get")
  public String anonymousGet() {
    return "success";
  }


}
