package com.example.fileuploadafter.mapper;

import com.example.fileuploadafter.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
@Mapper
public interface UserTest {
    List<User> cx();
    //判断用户名是否已存在
    Integer usernamealready(String username);
    //判断密码是否正确
    String login(String username,String password);
    //token登录
    User info(String token);
    //增加用户
    void add(String username,String password,String admin,String token);
    //以token查询name
    String tokentoname(String token);
    //以token查询admin
    String tokentoadmin(String token);
    //查询对应的token是否存在
    Integer cxtotken(String token);
    //以token查id
    Integer tokentoid(String token);
}
