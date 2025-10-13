package com.cubautoqa.server.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cubautoqa.server.entity.User;
import com.cubautoqa.server.mapper.UserMapper;
import com.cuber.dmtest.utils.EasyExcelUtils;

public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

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

}
