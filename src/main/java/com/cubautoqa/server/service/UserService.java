package com.cubautoqa.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cubautoqa.server.entity.User;
import org.springframework.stereotype.Service;


@Service
public interface UserService extends IService<User> {
    // 自定义业务方法
    User findByUsername(String username);

    public boolean importUsers(String filePath);

    //从超大文件读取数据并导入到user表
    public void importLargeUsers(String filePath);
}
