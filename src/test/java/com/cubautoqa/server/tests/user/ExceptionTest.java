//package com.cubautoqa.server.tests.user;
//
//import com.cubautoqa.server.entity.User;
//import com.cubautoqa.server.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
///***
// * 异常测试
// */
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ExceptionTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    @Test
//    public void testUserNotFound() throws Exception {
//        when(userService.findById(999L))
//                .thenThrow(new UserNotFoundException("User not found"));
//
//        mockMvc.perform(get("/api/users/999"))
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.message").value("User not found"));
//    }
//
//    @Test
//    public void testValidationError() throws Exception {
//        User invalidUser = new User(null, "", "invalid-email");
//
//        mockMvc.perform(post("/api/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(invalidUser)))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.errors").isArray())
//                .andExpect(jsonPath("$.errors[0].field").exists());
//    }
//}
