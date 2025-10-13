package com.cubautoqa.server.tests.user;

import com.cubautoqa.server.entity.User;
import com.cubautoqa.server.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/***
 * 用于测试控制器层而不需要启动完整的服务器
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest{
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetUserByName() throws Exception {
        // 模拟服务层返回
        User mockUser = new User("anna", "liugf.anna@outlook.com");
//        when(userService.getUserByName("anna")).thenReturn(mockUser);
        // 执行请求并验证响应
        mockMvc.perform(post("/user/1/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("anna"))
                .andExpect(jsonPath("$.email").value("liugf.anna@outlook.com"));
    }

//    @Test
//    public void testCreateUser() throws Exception {
//        User newUser = new User(null, "newuser", "new@example.com");
//        User savedUser = new User(1L, "newuser", "new@example.com");
//
//        when(userService.save(any(User.class))).thenReturn(savedUser);
//
//        mockMvc.perform(post("/api/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(newUser)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(header().exists("Location"));
//    }

//    @Test
//    public void testDeleteUser() throws Exception {
//        doNothing().when(userService).deleteById(1L);
//
//        mockMvc.perform(delete("/api/users/1"))
//                .andExpect(status().isNoContent());
//    }

    // 辅助方法：将对象转换为JSON字符串
    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
