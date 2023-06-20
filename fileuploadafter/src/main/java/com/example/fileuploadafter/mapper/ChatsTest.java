package com.example.fileuploadafter.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ChatsTest {
    //创建记录
    void adds(String from,String to,String homeid);
    //查询房间(查到则输出房间id)
    String cxhome(String from,String to);
    //判断房间是否存在
    Integer homed(String from,String to);
}
