package com.example.fileuploadafter.entity;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileTable {
    private int id;
    private String filename;
    private String describle;
    private String upname;
    private JSON collect;
    private String shouchang;
    private String filetype;
}
