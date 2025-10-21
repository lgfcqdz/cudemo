package com.cuber.lgfw.tests.unit.mapper;

import com.cuber.lgfw.entity.User;
import com.cuber.lgfw.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void testMapperConnection() {
        assertNotNull(userMapper, "UserMapper不应为null");

        // 测试简单的查询
        List<User> userList = userMapper.selectList(null);
        assertNotNull(userList, "查询结果不应为null");
        System.out.println("MyBatis-Plus Mapper连接测试成功，查询到 " + userList.size() + " 条记录");
    }

    @Test
    void testInsertAndSelect() {
        User user = new User();
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setPassword("123456");

        int result = userMapper.insert(user);
        assertEquals(1, result, "插入操作应该成功");

        User selectedUser = userMapper.selectById(user.getId());
        assertNotNull(selectedUser, "应该能查询到刚插入的用户");
        assertEquals("testUser", selectedUser.getUsername());

        // 清理测试数据
        userMapper.deleteById(user.getId());
    }
}
