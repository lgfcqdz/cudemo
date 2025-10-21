package com.cuber.lgfw.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsounUtils {
    // 辅助方法：将对象转换为JSON字符串
    public static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
