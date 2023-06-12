package com.example.fileuploadafter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrossConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}


//        200	OK	请求成功。一般用于GET与POST请求
//        201	Created	已创建。成功请求并创建了新的资源
//        202	Accepted	已接受。已经接受请求，但未处理完成
//        400	Bad Request	客户端错误，请求包含语法错误或无法完成请求
//        401	Unauthorized	请求要求用户的身份认证
//        403	Forbidden	服务器理解请求客户端的请求，但是拒绝执行此请求
//        404	Not Found	服务器无法根据客户端的请求找到资源（网页）
//        500	Internal Server Error	服务器内部错误，无法完成请求
//        501	Not Implemented	服务器不支持请求的功能，无法完成请求
//        503	Service Unavailable	由于超载或系统维护，服务器暂时的无法处理客户端的请求
//        public ResponseEntity<String> example() {
//        String message = "This is an example response";
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Custom-Header", "foo");
//        return new ResponseEntity<>(message, headers, HttpStatus.BAD_REQUEST);
//    }
