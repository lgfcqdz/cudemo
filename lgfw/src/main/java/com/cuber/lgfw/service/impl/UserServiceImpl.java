package com.cuber.lgfw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuber.lgfw.entity.User;
import com.cuber.lgfw.exception.UserNotFoundException;
import com.cuber.lgfw.mapper.UserMapper;
import com.cuber.lgfw.service.UserService;
import com.cuber.lgfw.utils.EasyExcelUtils;
import net.sf.jsqlparser.util.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    // 简单的邮箱格式正则表达式（实际应用中可能需要更复杂的表达式）
    private static final String EMAIL_REGEX = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";

    public User getById(Long id){
        User user = super.getById(id);
        if(null == user){
            throw new UserNotFoundException("用户未找到");
        }
        return user;
    }
    @Override
    public boolean save(User user){
        if(StringUtils.isBlank(user.getUsername())){
            throw new IllegalArgumentException();
        }
        if(!isValidEmail(user.getEmail())){
            throw new ValidationException("邮箱格式不正确!");
        }
        return  super.save(user);
    }
    @Override
    public User findByUsername(String username) {
        return lambdaQuery().eq(User::getUsername, username).one();
    }

    @Override
    public boolean importUsers(String filePath) {
        try {
            // 1. 小文件读取
            // List<User> users = EasyExcelUtils.readFileToList(filePath, User.class);
            // 2. 流式读取大文件
            EasyExcelUtils.streamRead(filePath, User.class, user -> {
                // 处理单个用户
                save(user);
//                createUser(user.getUsername(), user.getPassword(), user.getEmail(), user.getUserType(), user.getVipLevel());
            });
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void importLargeUsers(String filePath) {
    // 流式读取大文件
        EasyExcelUtils.batchRead(filePath, User.class,1000, batch  -> {
            // 处理一批用户（1000条）
            System.out.println("处理批次: " + batch.size() + " 条记录");
            saveBatch(batch);
//            for (User user : batch) {
//                // 处理单个用户
//                createUser(user.getUsername(), user.getPassword(), user.getEmail(), user.getUserType(), user.getVipLevel());
//            }
        });
    }

    public boolean createUser(String username, String password,
                          String email, Integer userType, Integer vipLevel) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setUserType(userType);
        user.setVipLevel(vipLevel);
        return save(user);
    }
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return email.matches(EMAIL_REGEX);
    }
}
