package com.cubautoqa.server.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")
public class User {
    @ExcelProperty("用户ID")
    @TableId(type = IdType.AUTO) // 自增主键
    private Integer id;
    @ExcelProperty("姓名")
    private String username;
    @ExcelProperty("密码")
    private String password;
    @ExcelProperty("邮箱")
    private String email;

    /**
     * 1(管理员)，2(开发人员)，3(客户)
     */
    @ExcelProperty("用户类型")
    private Integer userType;
    @ExcelProperty("vip等级")
    private Integer vipLevel;
    @TableLogic // 逻辑删除注解
    private Integer deleted;
    @TableField(fill = FieldFill.INSERT) // 自动填充
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User() {

    }
}
