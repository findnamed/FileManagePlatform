package com.example.fileuploadafter.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.fileuploadafter.components.Findavatar;
import com.example.fileuploadafter.entity.Message;
import com.example.fileuploadafter.mapper.ChatsTest;
import com.example.fileuploadafter.mapper.UserTest;
import com.example.fileuploadafter.util.SocketData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RestController
@ServerEndpoint("/socket/{username}")
public class WebSocket {

    //储存当前对象
    private static Map<String, Session> sessionMap=new ConcurrentHashMap<>();
    //建立连接
    @OnOpen
    public void  onOpen(Session session, @PathParam("username") String username)throws Exception{

//        //判断用户是否已在线
//        for(String sessioned :sessionMap.keySet()){
//            if(Objects.equals(username,sessioned)){
//                //如果用户已在线
//                onClose(username);
//                sessionMap.get(username).close();
//                return;
//            }
//        }
//        System.out.println(userTest.usernametoavatar(username));
        sessionMap.put(username,session);
        System.out.println(username+"连接建立成功");
        //数据查询
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("data",SocketData.readjson());
        jsonObject.put("userName",setUserList());
        sendAllMessage(jsonObject.toString());

    }

    //关闭连接
    @OnClose
    public void onClose(@PathParam("username")String username) throws Exception {
        sessionMap.remove(username);
        //数据写入
//        SocketData.insertjson("系统通知", "",(username+"离开房间"));
//        sendAllMessage(setUserList());
    }

    //发送消息
    @OnMessage
    public void onMessage(String message) throws Exception {
        Message msg= JSON.parseObject(message, Message.class);
        if(StringUtils.isEmpty(msg.getTo())){
            //群聊
            //数据写入
            SocketData.insertjson(msg.getFrom(), msg.getTo(),msg.getMsg());
            String avatar=Findavatar.nametoavatar(msg.getFrom());
            msg.setAvatar(avatar);
            sendAllMessage(JSON.toJSONString(msg));
        }else{
            //私聊
            //获取谁发给谁的
            Session sessionTo=sessionMap.get(msg.getTo());
            sendMessage(message,sessionTo);
        }
    }

    //出错
    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("未知错误");
        error.printStackTrace();
    }


    //发送在线用户
    private String setUserList(){
        ArrayList<String> list=new ArrayList<>();
        for(String key:sessionMap.keySet()){
            list.add(key);
        }
        Message message=new Message();
        message.setUserName(list);
        return JSON.toJSONString(message);
    }
    //服务端发送消息给指定客户端
    private void sendMessage(String message,Session tosession){
        try {
            tosession.getBasicRemote().sendText(message);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //服务端群发客户端
    private void sendAllMessage(String message){
        try{
            for(Session session:sessionMap.values()){
                session.getBasicRemote().sendText(message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
