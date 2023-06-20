package com.example.fileuploadafter.components;

import com.example.fileuploadafter.mapper.UserTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Findavatar {

    private static Findavatar findavatar;

    @Autowired
    private UserTest userTest;

    @PostConstruct
    public void init(){
        findavatar = this;
        findavatar.userTest=this.userTest;
    }
    public static String nametoavatar(String username){
        return findavatar.userTest.usernametoavatar(username);
    }

}
