package com.cuber.lgfw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cuber.lgfw.entity.User;
import org.springframework.stereotype.Service;



public interface UserService extends IService<User> {
    User getById(Long id);
    // 自定义业务方法
    User findByUsername(String username);

    public boolean importUsers(String filePath);

    //从超大文件读取数据并导入到user表
    public void importLargeUsers(String filePath);
}
