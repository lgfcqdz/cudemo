//package com.cubautoqa.server.tests.user;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
///***
// * 测试文件上传
// */
//@SpringBootTest
//@AutoConfigureMockMvc
//public class FileUploadTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void testFileUpload() throws Exception {
//        MockMultipartFile file = new MockMultipartFile(
//                "file",
//                "dmtest.txt",
//                "text/plain",
//                "Hello World".getBytes()
//        );
//
//        mockMvc.perform(multipart("/api/upload")
//                .file(file)
//                .param("description", "Test file"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("File uploaded successfully"));
//    }
//}
