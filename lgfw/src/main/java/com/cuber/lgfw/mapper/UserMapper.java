package com.cuber.lgfw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cuber.lgfw.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface  UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM user WHERE username = #{username}")
    User selectByUsername(@Param("username") String username);

    @Select("<script>" +
            "SELECT * FROM user WHERE vip_level >= #{vipLevel}" +
            "<if dmtest='userType != null'> AND user_type = #{userType}</if>" +
            "</script>")
    List<User> selectByCondition(@Param("vipLevel") Integer vipLevel,
                                 @Param("userType") Integer userType);
}
