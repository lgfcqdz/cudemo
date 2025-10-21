//package com.cubautoqa.lgfw.tests.user;
//
///***
// * WireMock 是另一个流行的HTTP服务模拟工具。
// * <dependency>
// *     <groupId>com.github.tomakehurst</groupId>
// *     <artifactId>wiremock</artifactId>
// *     <version>2.27.2</version>
// *     <scope>dmtest</scope>
// * </dependency>
// */
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class WireMockTest {
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Rule
//    public WireMockRule wireMockRule = new WireMockRule(8089);
//
//    @Test
//    public void testWithWireMock() {
//        // 配置WireMock
//        stubFor(get(urlEqualTo("/external/users/1"))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", "application/json")
//                        .withBody("{\"id\": 1, \"name\": \"WireMock User\"}")));
//
//        // 调用测试
//        ResponseEntity<String> response = restTemplate.getForEntity(
//                "/api/external/users/1", String.class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).contains("WireMock User");
//    }
//}
