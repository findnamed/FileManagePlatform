package com.example.fileuploadafter.mapper;

import com.example.fileuploadafter.entity.FileTable;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FileTest {
    //创建记录
    void add(String filename,String describle,String upname,String filetype);
    //查询文件名是否存在
    Integer cxname(String filename);
    //删除文件
    void delfile(String filename);
    //查询全部
    List<FileTable> cx(Integer idx);
    //修改描述
    void fileupdate(String filename,String describle);
    //按文件名取出 谁发布的
    String filenametoupname(String filename);
    //搜索（按照文件名）公有
    List<FileTable> filesearch(String param,Integer idx);
    //搜索(长度)公有
    Integer filesearch1(String param);
    //搜索(私有)
    List<FileTable> filesearchps(String param,Integer idx,String username);
    //搜索（私有）长度
    Integer filesearchps1(String param,String username);

    //以upname查询数据

    List<FileTable> upnamesearch(String upname,Integer idx);
    //返回所有数据的数量
    Integer datalength();
    //返回对应用户上传的数据长度
    Integer datalength1(String username);
    //写入json数据
    void insertjson(String ids,String filename);
    //判断数据是否存在(json)
   Integer pdjson(String ids,String filename);
    //删除数据(json)
    void deljson(String ids,String filename);

    //以文件名查询描述
    String cxdescribe(String filename);
    //以文件名查询上传者
    String cxupname(String filename);
    //以文件名查询文件类型
    String cxfiletype(String filename);
}
