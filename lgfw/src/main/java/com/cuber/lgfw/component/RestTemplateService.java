package com.cuber.lgfw.component;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class RestTemplateService {
    private final RestTemplate restTemplate;

    public RestTemplateService() {
        this.restTemplate = new RestTemplate();
        // 配置超时时间等
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000);
        factory.setReadTimeout(5000);
        restTemplate.setRequestFactory(factory);
    }

    /**
     * GET 请求
     */
    public <T> T get(String url, Class<T> responseType) {
        return restTemplate.getForObject(url, responseType);
    }

    /**
     * GET 请求带参数
     */
    public <T> T get(String url, Class<T> responseType, Map<String, Object> params) {
        return restTemplate.getForObject(url, responseType, params);
    }

    /**
     * POST 请求
     */
    public <T> T post(String url, Object request, Class<T> responseType) {
        return restTemplate.postForObject(url, request, responseType);
    }

    /**
     * POST 请求带完整响应
     */
    public <T> ResponseEntity<T> postWithResponse(String url, Object request, Class<T> responseType) {
        return restTemplate.postForEntity(url, request, responseType);
    }

    /**
     * PUT 请求
     */
    public void put(String url, Object request) {
        restTemplate.put(url, request);
    }

    /**
     * DELETE 请求
     */
    public void delete(String url) {
        restTemplate.delete(url);
    }

    /**
     * 带认证的请求
     */
    public <T> T getWithAuth(String url, String token, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, entity, responseType);
        return response.getBody();
    }
}
