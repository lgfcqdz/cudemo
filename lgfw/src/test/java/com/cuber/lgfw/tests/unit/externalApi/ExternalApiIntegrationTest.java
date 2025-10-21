//package com.cubautoqa.lgfw.tests.user;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.ResponseEntity;
//
///***
// * 当你的代码需要调用外部HTTP API时，可以使用 MockServer 来模拟这些外部服务。
// *
// * <dependency>
// *     <groupId>org.mock-lgfw</groupId>
// *     <artifactId>mockserver-netty</artifactId>
// *     <version>5.13.2</version>
// *     <scope>dmtest</scope>
// * </dependency>
// * <dependency>
// *     <groupId>org.mock-lgfw</groupId>
// *     <artifactId>mockserver-client-java</artifactId>
// *     <version>5.13.2</version>
// *     <scope>dmtest</scope>
// * </dependency>
// */
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class ExternalApiIntegrationTest {
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    private ClientAndServer mockServer;
//
//    @BeforeEach
//    public void startMockServer() {
//        mockServer = ClientAndServer.startClientAndServer(1080);
//    }
//
//    @AfterEach
//    public void stopMockServer() {
//        mockServer.stop();
//    }
//
//    @Test
//    public void testCallExternalApi() {
//        // 设置MockServer期望的请求和响应
//        mockServer.when(
//                request()
//                        .withMethod("GET")
//                        .withPath("/external/users/1")
//        ).respond(
//                response()
//                        .withStatusCode(200)
//                        .withHeader("Content-Type", "application/json")
//                        .withBody("{\"id\": 1, \"name\": \"External User\"}")
//        );
//
//        // 调用自己的API，它会调用外部API
//        ResponseEntity<String> response = restTemplate.getForEntity(
//                "/api/external/users/1", String.class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).contains("External User");
//    }
//}
