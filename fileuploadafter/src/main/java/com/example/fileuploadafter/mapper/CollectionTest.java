package com.example.fileuploadafter.mapper;

import com.example.fileuploadafter.entity.Collectioned;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CollectionTest {
    //新增记录
    void add(String filename,String username);
    //删除记录
    void del(String filename,String username);
    //查询(本数据)
    List<Collectioned> cx(Integer idx,String username);
    //查询收藏的总长度
    Integer cxlen(String username);
    //模糊匹配(找数据)
    List<Collectioned> cxsearch(Integer idx,String param,String username);
    //模糊匹配找长度
    Integer cxsearchlen(String username,String param);
    //判断是否已经收藏
    Integer pdcollect(String username,String filename);
    //判断文件是否已经被删除了
    Integer pddelfile(String filename);
}
