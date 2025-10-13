//package com.cubautoqa.server.tests.user;
//
//
//import com.cubautoqa.server.entity.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.net.URI;
//import java.util.List;
//
///***
// * 当需要测试完整的HTTP请求响应周期时，可以使用 TestRestTemplate。
// */
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class UserControllerIntegrationTest {
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @LocalServerPort
//    private int port;
//
//    @Test
//    public void testGetUserById() {
//        ResponseEntity<User> response = restTemplate.getForEntity(
//                "/api/users/1", User.class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).isNotNull();
//        assertThat(response.getBody().getId()).isEqualTo(1);
//    }
//
//    @Test
//    public void testCreateUser() {
//        User newUser = new User(null, "testuser", "dmtest@example.com");
//
//        ResponseEntity<User> response = restTemplate.postForEntity(
//                "/api/users", newUser, User.class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        assertThat(response.getBody()).isNotNull();
//        assertThat(response.getBody().getId()).isNotNull();
//
//        // 验证Location头
//        URI location = response.getHeaders().getLocation();
//        assertThat(location).isNotNull();
//    }
//
//    @Test
//    public void testGetAllUsers() {
//        ResponseEntity<List> response = restTemplate.getForEntity(
//                "/api/users", List.class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).isNotNull();
//    }
//}
