package com.cuber.lgfw.tests.integration;

import com.cuber.lgfw.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase
public class UserIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createUserThroughAllLayers() {
//        UserCreateRequest request = new UserCreateRequest("test", "test@email.com");
//
//        ResponseEntity<User> response = restTemplate.postForEntity(
//                "/api/users", request, User.class);
//
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertNotNull(response.getBody().getId());
    }
}
