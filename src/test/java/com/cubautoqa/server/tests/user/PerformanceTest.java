//package com.cubautoqa.server.tests.user;
//
///***
// * 测试接口性能
// */
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class PerformanceTest {
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    public void testResponseTime() {
//        long startTime = System.currentTimeMillis();
//
//        ResponseEntity<String> response = restTemplate.getForEntity(
//                "/api/users", String.class);
//
//        long endTime = System.currentTimeMillis();
//        long duration = endTime - startTime;
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(duration).isLessThan(1000); // 响应时间应小于1秒
//    }
//    @Test
//    public void testConcurrentRequests() throws InterruptedException {
//        int numberOfThreads = 10;
//        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
//        CountDownLatch latch = new CountDownLatch(numberOfThreads);
//
//        List<CompletableFuture<ResponseEntity<String>>> futures = new ArrayList<>();
//
//        for (int i = 0; i < numberOfThreads; i++) {
//            CompletableFuture<ResponseEntity<String>> future = CompletableFuture.supplyAsync(() -> {
//                try {
//                    return restTemplate.getForEntity("/api/users", String.class);
//                } finally {
//                    latch.countDown();
//                }
//            }, executor);
//
//            futures.add(future);
//        }
//
//        latch.await(5, TimeUnit.SECONDS);
//
//        // 验证所有请求都成功
//        for (CompletableFuture<ResponseEntity<String>> future : futures) {
//            assertThat(future.isDone()).isTrue();
//            ResponseEntity<String> response = future.getNow(null);
//            assertThat(response).isNotNull();
//            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        }
//
//        executor.shutdown();
//    }
//}
