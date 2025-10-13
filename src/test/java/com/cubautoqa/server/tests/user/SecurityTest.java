//package com.cubautoqa.server.tests.user;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
///***
// * 测试安全端点
// */
//@SpringBootTest
//@AutoConfigureMockMvc
//public class SecurityTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    @WithMockUser(username = "admin", roles = {"ADMIN"})
//    public void testAdminEndpointWithAdminRole() throws Exception {
//        mockMvc.perform(get("/api/admin/users"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @WithMockUser(username = "user", roles = {"USER"})
//    public void testAdminEndpointWithUserRole() throws Exception {
//        mockMvc.perform(get("/api/admin/users"))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    public void testUnauthenticatedAccess() throws Exception {
//        mockMvc.perform(get("/api/admin/users"))
//                .andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    @WithMockUser(username = "testuser")
//    public void testWithCustomUser() throws Exception {
//        mockMvc.perform(get("/api/profile"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.username").value("testuser"));
//    }
//}
