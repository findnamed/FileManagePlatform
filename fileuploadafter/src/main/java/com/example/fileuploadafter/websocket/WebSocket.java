package com.example.fileuploadafter.websocket;

import com.alibaba.fastjson.JSON;
import com.example.fileuploadafter.entity.Message;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@RestController
@ServerEndpoint("/socket/{username}")
public class WebSocket {
    //储存当前对象
    private static Map<String, Session> sessionMap=new ConcurrentHashMap<>();
    //建立连接
    @OnOpen
    public void  onOpen(Session session, @PathParam("username") String username)throws Exception{
        sessionMap.put(username,session);
        System.out.println(username+"连接建立成功");
        sendAllMessage(setUserList());
    }

    //关闭连接
    @OnClose
    public void onClose(@PathParam("username")String username){
        sessionMap.remove(username);
        sendAllMessage(setUserList());
    }

    //发送消息
    @OnMessage
    public void onMessage(String message){
        Message msg= JSON.parseObject(message, Message.class);
        if(StringUtils.isEmpty(msg.getTo())){
            sendAllMessage(JSON.toJSONString(msg));
        }else{
            Session sessionTo=sessionMap.get(msg.getTo());
            sendMessage(message,sessionTo);
        }
    }

    //出错
    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("程序出错了");
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
