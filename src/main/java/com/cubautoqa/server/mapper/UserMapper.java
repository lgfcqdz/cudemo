package com.cubautoqa.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cubautoqa.server.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface  UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE id = #{id}")
    User selectById(@Param("id") Integer id);

    @Select("SELECT * FROM user WHERE username = #{username}")
    User selectByUsername(@Param("username") String username);

    @Select("SELECT * FROM user")
    List<User> selectAll();

    @Insert("INSERT INTO user(username, password, email, user_type, vip_level) " +
            "VALUES(#{username}, #{password}, #{email}, #{userType}, #{vipLevel})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    @Update("UPDATE user SET username=#{username}, password=#{password}, " +
            "email=#{email}, user_type=#{userType}, vip_level=#{vipLevel} WHERE id=#{id}")
    int update(User user);

    @Delete("DELETE FROM user WHERE id=#{id}")
    int deleteById(Integer id);

    @Select("<script>" +
            "SELECT * FROM user WHERE vip_level >= #{vipLevel}" +
            "<if dmtest='userType != null'> AND user_type = #{userType}</if>" +
            "</script>")
    List<User> selectByCondition(@Param("vipLevel") Integer vipLevel,
                                 @Param("userType") Integer userType);
}
