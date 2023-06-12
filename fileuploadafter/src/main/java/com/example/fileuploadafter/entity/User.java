package com.example.fileuploadafter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String admin;

    @JsonIgnore
    private String token;
    @JsonIgnore
    private String password;
}
