package com.example.fileuploadafter.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.fileuploadafter.entity.User;
import com.example.fileuploadafter.mapper.FileTest;
import com.example.fileuploadafter.mapper.UserTest;
import com.example.fileuploadafter.util.FileTypeDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import com.example.fileuploadafter.util.FileTypeDetector;

@RestController
public class UserController {
    @Autowired
    UserTest userTest;
    @Autowired
    FileTest fileTest;

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


    //用户名密码登录
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


    //上传头像
    //文件上传接口
    @PostMapping("/uploadavatar")
    public ResponseEntity<JSONObject> upload(@RequestParam("file") MultipartFile file, @RequestHeader("Authorization") String token) throws Exception {
        //-----状态码区域
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "foo");
        //-----
        String filefold="D:\\FileUploadAndDownload\\fileuploadafter";
        File folder = new File(filefold + "/Allavatar");
        if(!folder.exists()){
            folder.mkdirs();
        }
        JSONObject jsonObject=new JSONObject();
        //读取文件名
        String fileName = file.getOriginalFilename();
        //判断token是否存在
        if(userTest.cxtotken(token)<=0){
            jsonObject.put("msg","token已过期，请重新登录");
            return new ResponseEntity<>(jsonObject,headers,HttpStatus.UNAUTHORIZED);
        }
        //判断是否为图片格式
        String filetype=FileTypeDetector.getFileType(fileName);
        if(Objects.equals(filetype,"image")){
            //切割文件名和后缀
            String[] strArr = fileName.split("\\.");
            //生成UUID
            String newfilename=UUID.randomUUID()+"AVATAR."+strArr[1];
            //判断之前头像是否存在
            if(Objects.equals(userTest.tokentoavatar(token),"ace.gif")){
                System.out.println("头像无需清除");
            }else{
                //先清除之前的头像
                String oldavatar=userTest.tokentoavatar(token);
                File folder1 = new File("D:\\FileUploadAndDownload\\fileuploadafter\\Allavatar\\",oldavatar);
                if (folder1.getName().equals(oldavatar)) {
                    folder1.delete();
                }

            }
            //头像链接写入数据库
            userTest.updateavatar(newfilename,token);
            //文件上传
            file.transferTo(new File(folder.getAbsolutePath() + File.separator + newfilename));
            jsonObject.put("msg","头像上传成功：");
            jsonObject.put("avatar",newfilename);
            return new ResponseEntity<>(jsonObject,headers,HttpStatus.OK);


        }else{
            jsonObject.put("msg","上传文件格式错误,头像只支持图片格式");
            return new ResponseEntity<>(jsonObject,headers,HttpStatus.BAD_REQUEST);
        }
    }

}
