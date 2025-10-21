package com.cuber.lgfw.tests.unit.controller;

import com.cuber.lgfw.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/***
 * 当需要测试完整的HTTP请求响应周期时，可以使用 TestRestTemplate。
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateUser() {
        User newUser = new User("testuser", "dmtest@example.com");

        ResponseEntity<User> response = restTemplate.postForEntity(
                "/api/users", newUser, User.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();

        // 验证Location头
        URI location = response.getHeaders().getLocation();
        assertThat(location).isNotNull();
    }
}
