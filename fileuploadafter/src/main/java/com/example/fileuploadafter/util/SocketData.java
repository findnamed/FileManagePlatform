package com.example.fileuploadafter.util;

import com.alibaba.fastjson.JSONObject;
import com.example.fileuploadafter.components.Findavatar;
import com.example.fileuploadafter.entity.Person;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.springframework.http.HttpHeaders;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

//@Service
public class SocketData {

    //取得json文件内容
    public static String readjson() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "foo");
        List<JSONObject> js=new ArrayList<>();
        JsonReader reader = new JsonReader(new FileReader("D:\\FileUploadAndDownload\\fileuploadafter\\websocketdata\\roototuser.json"));
        reader.beginArray(); //开始遍历数组
        while (reader.hasNext()) {
            JSONObject jsonObject=new JSONObject();
            Gson gson = new Gson();
            Person person = gson.fromJson(reader, Person.class);
            jsonObject.put("from",person.getFrom());
            jsonObject.put("avatar", Findavatar.nametoavatar(person.getFrom()));
//            String ava=person.getFrom();
//            String atk=Findavatar.nametoavatar("123456");
            //判断是否为空-------------------

//            if(Objects.equals(ava,null)){
//                System.out.println("返回值为空");
//            }else{
////                jsonObject.put("avatar",PrivateController.usernametoavatar1(ava));
//                String ak=userTest.usernametoavatar("123456");
//                System.out.println(ak);
//            }
            //-----------------------------
//            System.out.println(userTest.usernametoavatar(person.getFrom().toString()));
            jsonObject.put("msg",person.getMsg());
            jsonObject.put("to",person.getTo());
            js.add(jsonObject);

        }
        reader.endArray();
        reader.close();
        return js.toString();
    }
    //写入json
    public static void insertjson(String from,String to,String msg)throws Exception{
        HttpHeaders headers=new HttpHeaders();
        headers.add("Custom-Header","foo");


        Gson gson = new Gson();
        BufferedReader reader;
        List<JSONObject> dataList=new ArrayList<>();
        JSONObject jsx=new JSONObject();
        jsx.put("data","4333");
        reader = new BufferedReader(new FileReader("D:\\FileUploadAndDownload\\fileuploadafter\\websocketdata\\roototuser.json"));
        Type listType = new TypeToken<List<JSONObject>>() {}.getType();
            JSONObject jsc=new JSONObject();
            jsc.put("from",from);
            jsc.put("msg",msg);
            jsc.put("to",to);
            //获取到原数据
            dataList = gson.fromJson(reader, listType);
            //追加数据
            dataList.add(jsc);
            //当前数据
            String updatedJsonString = gson.toJson(dataList);

            FileWriter fileWriter = new FileWriter("D:\\FileUploadAndDownload\\fileuploadafter\\websocketdata\\roototuser.json");
            fileWriter.write(updatedJsonString);
            //更新
            fileWriter.flush();

//        return new ResponseEntity<>(jsx,headers, HttpStatus.OK);

    }
    //取得json文件内容(获取私聊)
    public static String readjsonprivate()throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "foo");
        //判断一下房间是否存在
//        Integer homed=chatsTest.homed(from,to);
//        System.out.println(homed);


        List<JSONObject> js=new ArrayList<>();
        JsonReader reader = new JsonReader(new FileReader("D:\\FileUploadAndDownload\\fileuploadafter\\websocketdata\\roototuser.json"));
        reader.beginArray(); //开始遍历数组
        while (reader.hasNext()) {
            JSONObject jsonObject=new JSONObject();
            Gson gson = new Gson();
            Person person = gson.fromJson(reader, Person.class);
//            System.out.println(person);
            jsonObject.put("from",person.getFrom());
            jsonObject.put("msg",person.getMsg());
            jsonObject.put("to",person.getTo());
            js.add(jsonObject);

        }
        reader.endArray();
        reader.close();
        return js.toString();
    }

    // 设置MyMapper实例的方法
//    public static void setMyMapper(UserTest userTest) {
//        SocketData.userTest = userTest;
//    }
}
