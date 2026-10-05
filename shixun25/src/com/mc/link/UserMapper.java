package com.mc.link;

import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO users (username, password, email) VALUES (#{username}, #{password}, #{email})")
    void register(User user);

    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(String username);

    @Update("UPDATE users SET password = #{password} WHERE email = #{email}")
    void updatePassword(@Param("email") String email, @Param("password") String password);

    @Delete("DELETE FROM users WHERE username = #{username}")
    void deleteByUsername(String username);
}