package com.cuber.lgfw.tests.integration;

import com.cuber.lgfw.entity.User;
import com.cuber.lgfw.mapper.UserMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/***
 * 数据库测试策略
 */
@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceIntegrationTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    @Order(1)
    void shouldCreateUser() {
        User user = new User("first", "first@email.com");
        int saved = userMapper.insert(user);
        assertNotNull(saved);
    }

    @Test
    @Order(2)
    void shouldFindCreatedUser() {
        List<User> users = userMapper.selectList(null);
        assertEquals(1, users.size()); // 由于事务回滚，实际是独立的
    }
}
