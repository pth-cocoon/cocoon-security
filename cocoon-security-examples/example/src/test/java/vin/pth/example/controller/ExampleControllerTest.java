package vin.pth.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExampleControllerTest {


  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  /**
   * 登录函数单元测试
   */
  @Test
  void login() throws JsonProcessingException {
    Map<String, String> paramJson = new HashMap<>();
    paramJson.put("username", "admin");
    paramJson.put("password", "admin");
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> request = new HttpEntity<>(new ObjectMapper().writeValueAsString(paramJson), headers);

    String url = "http://127.0.0.1:" + port + "/login";
    ResponseEntity<Map> responseEntity = restTemplate.postForEntity(url, request, Map.class);
    log.info("body is :{}" + Objects.requireNonNull(responseEntity.getBody()).toString());
    Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
    Assertions.assertThat(responseEntity.getBody().get("code")).isEqualTo(0);
  }


  @Test
  void adminGet() {
    String url = "http://127.0.0.1:" + port + "/admin/get";
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
    log.info("body is :{}" + Objects.requireNonNull(responseEntity.getBody()));
    Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.FORBIDDEN);

  }

  @Test
  void anonymousGet403() {
    String url = "http://127.0.0.1:" + port + "/anonymous/get";
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
    log.info("body is :{}" + Objects.requireNonNull(responseEntity.getBody()));
    Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.FORBIDDEN);
  }

  @Test
  void anonymousGet200() {
    String url = "http://127.0.0.1:" + port + "/anonymous/get";
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("token", "anonymous");
    HttpEntity<String> request = new HttpEntity<>("", headers);
    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
    log.info("body is :{}" + Objects.requireNonNull(responseEntity.getBody()));
    Assertions.assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
  }
}