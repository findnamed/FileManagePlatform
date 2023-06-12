package com.example.fileuploadafter.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.fileuploadafter.entity.User;
import com.example.fileuploadafter.mapper.UserTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Handler;

@RestController
public class UserController {
    @Autowired
    UserTest userTest;


    @GetMapping("hello")
    public JSONObject hello(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("key","这是一个小小的测试");
        return  jsonObject;
    }


    @GetMapping("cx")
    public List<User> cx(){
        return userTest.cx();
    }

    //用户名密码登录
//    @PostMapping("/login1")
//    public JSONObject login(@RequestBody Map<String,String> params) {
//        String username = params.get("username");
//        String password = params.get("password");
//        JSONObject jsonObject = new JSONObject();
//        //判断用户名不存在
//        Integer st = userTest.usernamealready(username);
//        if (st <= 0) {
//            jsonObject.put("msg", "用户名不存在");
//            return jsonObject;
//        } else {
//            String token = userTest.login(username, password);
//            if (token != null) {
//                jsonObject.put("msg", "登录成功");
//                jsonObject.put("token", token);
//                return jsonObject;
//            }
//            else{
//                jsonObject.put("msg", "用户名或密码错误");
//                return jsonObject;
//            }
//        }
//    }


    //token登录
    @GetMapping("/info")
    public Object info(@RequestHeader("Authorization")String token){

        HttpHeaders headers=new HttpHeaders();
        headers.add("Custom-Header", "foo");
        JSONObject jsonObject=new JSONObject();
        if(userTest.cxtotken(token)>0){
            User infos= userTest.info(token);
            if(Objects.equals(infos,null)){
                jsonObject.put("msg","token已过期，请重新登录");
                return new ResponseEntity<>(jsonObject,headers,HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity<>(infos,headers,HttpStatus.OK);
            }
        }else{
            jsonObject.put("msg","token已过期，请重新登录");
            return new ResponseEntity<>(jsonObject,headers,HttpStatus.BAD_REQUEST);
        }

    }
    //注册
    @PostMapping("register")
    public ResponseEntity<JSONObject> register(@RequestBody Map<String,String> params){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "foo");


        String username=params.get("username");
        String password=params.get("password");
        String admin="user";
        JSONObject jsonObject=new JSONObject();
        if(Objects.equals(username,null) || Objects.equals(password,null)){
            jsonObject.put("msg","用户名或密码不能为空");
            return new ResponseEntity<>(jsonObject,headers,HttpStatus.BAD_REQUEST);
        }
        else if(userTest.usernamealready(username)>0){
            jsonObject.put("msg","用户名已存在");
            return new ResponseEntity<>(jsonObject,headers,HttpStatus.BAD_REQUEST);
        }
        else{
            String token= UUID.randomUUID()+"FILE-SPACE";
            userTest.add(username,password,admin,token);
            jsonObject.put("msg","注册成功");
            jsonObject.put("token",token);
            jsonObject.put("username",username);
            return new ResponseEntity<>(jsonObject,headers,HttpStatus.OK);
        }
    }


    //用户名密码登录1
    @PostMapping("/login")
    public ResponseEntity<JSONObject> login1(@RequestBody Map<String,String> params) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "foo");

        String username = params.get("username");
        String password = params.get("password");
        JSONObject jsonObject = new JSONObject();
        //判断用户名不存在
        Integer st = userTest.usernamealready(username);
        if (st <= 0) {
            jsonObject.put("msg", "用户名不存在");
            return new ResponseEntity<>(jsonObject, headers, HttpStatus.BAD_REQUEST);
        } else {
            String token = userTest.login(username, password);
            if (token != null) {
                jsonObject.put("username",username);
                jsonObject.put("msg", "登录成功");
                jsonObject.put("token", token);
                return new ResponseEntity<>(jsonObject, headers, HttpStatus.OK);
            }
            else{
                jsonObject.put("msg", "用户名或密码错误");
                return new ResponseEntity<>(jsonObject, headers, HttpStatus.BAD_REQUEST);
            }
        }
    }

}
