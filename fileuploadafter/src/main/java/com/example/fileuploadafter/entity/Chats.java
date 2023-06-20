package com.example.fileuploadafter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chats {
    private int id;
    private String from;
    private String to;
    private String homeid;
}
