package com.cuber.lgfw.tests.unit.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuber.lgfw.entity.User;
import com.cuber.lgfw.mapper.UserMapper;
import com.cuber.lgfw.service.impl.UserServiceImpl;
import net.sf.jsqlparser.util.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void createUser_ShouldSaveToRepository() {
        when(userMapper.delete(any(QueryWrapper.class))).thenReturn(1);
        User user = new User("john", "john@email.com");
        boolean result = userService.removeById(user);
        verify(userMapper).deleteById(User.class);

    }


    //以下是基於輸入的測試策略
    @Test
    void createUser_WithValidInput_ShouldSuccess() {
        when(userMapper.insert(any(User.class))).thenReturn(1);
        // 等价类划分：有效输入
        User request = new User("john", "john@email.com");
        boolean result = userService.save(request);
        assertTrue(result);
    }

    @Test
    void createUser_WithInvalidEmail_ShouldThrowException() {
        // 边界值分析：无效邮箱格式
        User request = new User("john", "Invalidemail.com");
        assertThrows(ValidationException.class, () -> userService.save(request));
    }

    @Test
    void createUser_WithNullUsername_ShouldThrowException() {
        // 特殊值测试：null 值
        User request = new User(null, "test@email.com");
        assertThrows(IllegalArgumentException.class,
                () -> userService.save(request));
    }
}
