//package com.cubautoqa.server.tests.user;
//
//import com.cubautoqa.server.controller.UserController;
//import com.cubautoqa.server.entity.User;
//import com.cubautoqa.server.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
///***
// * 当只想测试Web层，而不需要加载
// */
//@WebMvcTest(UserController.class)
//public class UserControllerSliceTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    @Test
//    public void testGetUserById() throws Exception {
//        User mockUser = new User(1L, "testuser", "dmtest@example.com");
//        when(userService.findById(1L)).thenReturn(mockUser);
//
//        mockMvc.perform(get("/api/users/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.username").value("testuser"));
//    }
//}
