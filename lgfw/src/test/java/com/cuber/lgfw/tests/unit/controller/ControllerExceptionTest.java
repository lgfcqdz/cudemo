package com.cuber.lgfw.tests.unit.controller;

import com.cuber.lgfw.entity.User;
import com.cuber.lgfw.exception.UserNotFoundException;
import com.cuber.lgfw.service.UserService;
import com.cuber.lgfw.tests.unit.BaseUnitTest;
import com.cuber.lgfw.utils.JsounUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/***
 * 异常测试
 */
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerExceptionTest extends BaseUnitTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Test
    public void testUserNotFound() throws Exception {
        when(userService.getById(anyLong())).thenThrow(new UserNotFoundException("USER_NOT_FOUND"));

        mockMvc.perform(get("/user/123"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("USER_NOT_FOUND"));
    }

    @Test
    public void testValidationError() throws Exception {
        User invalidUser = new User("", "invalid-email");

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsounUtils.asJsonString(invalidUser)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors[0].field").exists());
    }

    @Test
    public void testGetUserById() {
        ResponseEntity<User> response = restTemplate.getForEntity(
                "/api/users/1", User.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(1);
    }
}
