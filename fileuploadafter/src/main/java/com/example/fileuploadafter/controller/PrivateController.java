package com.example.fileuploadafter.controller;


import com.example.fileuploadafter.mapper.UserTest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Map;

@RestController
public class PrivateController {

    @Autowired
    static
    UserTest userTest;

    @GetMapping("createfile")
    public String createfile(){
        String filefold="D:\\FileUploadAndDownload\\fileuploadafter";
        File folder = new File(filefold + "/Allavatar");
        if(!folder.exists()){
            folder.mkdirs();
            return "文件夹创建成功";
        }else{
            return  "文件夹创建失败";
        }


    }

    //以头像查询avatar
//    @RequestMapping("test1")
//    public static String usernametoavatar1(String username){
//        return UserTest.usernametoavatar(username);
//    }
    //查询
//    @GetMapping("test2")
//    public ResponseEntity<JSONObject> test2()throws Exception{
//        return SocketData.insertjson();
//    }

    //修改
//    @GetMapping("test1")
//    public ResponseEntity<JSONObject> test1() throws Exception {
//        return SocketData.readjson();
//    }

}
