package com.example.fileuploadafter.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.fileuploadafter.entity.Collectioned;
import com.example.fileuploadafter.entity.FileTable;
import com.example.fileuploadafter.mapper.CollectionTest;
import com.example.fileuploadafter.mapper.FileTest;
import com.example.fileuploadafter.mapper.UserTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import com.example.fileuploadafter.util.FileTypeDetector;

@RestController
public class FileController {

    @Autowired
    CollectionTest collectionTest;
    @Autowired
    UserTest userTest;
    @Autowired
    FileTest fileTest;

    //文件上传接口
    @PostMapping("/upload")
    public ResponseEntity<JSONObject> upload(@RequestParam("file") MultipartFile file, @RequestParam("describle") String describle, @RequestParam("upname") String upname) throws Exception {
        //-----状态码区域
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "foo");
        //-----
        // 设置上传至项目文件夹下的uploadFile文件夹中，没有文件夹则创建
        File dir = new File("D:\\FileUploadAndDownload\\fileuploadafter\\uploadFile");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //读取文件名
        String fileName = file.getOriginalFilename();
        //括号替换
        fileName=fileName.replace('[','X');
        fileName=fileName.replace(']','X');
        fileName=fileName.replace('&','X');
        JSONObject jsonObject=new JSONObject();
        Integer tk=fileTest.cxname(fileName);
        //判断文件名是否存在
        if(tk>0){
            jsonObject.put("msg","文件名已存在,请选择其他文件名上传");
            return new ResponseEntity<>(jsonObject, headers, HttpStatus.BAD_REQUEST);
        }else{
            //创建一个 `File` 对象，表示要保存的文件。最后，`file.transferTo()` 方法将上传的文件保存到指定的目录中。File.separator 分割符
//        System.out.println(File.separator);
            file.transferTo(new File(dir.getAbsolutePath() + File.separator + fileName));
            String filetype=FileTypeDetector.getFileType(fileName);
            fileTest.add(fileName,describle,upname,filetype);
            jsonObject.put("msg","上传完成！文件名：" + fileName);
            return new ResponseEntity<>(jsonObject, headers, HttpStatus.OK);
        }
    }

    //文件删除接口
    @PostMapping("filedel")
    public ResponseEntity<JSONObject> filedel(@RequestBody Map<String, String> params,@RequestHeader("Authorization") String token) {
        //--------
        HttpHeaders headers=new HttpHeaders();
        headers.add("Custom-Header", "foo");
        //-------
        JSONObject jsonObject=new JSONObject();
        String fileName = params.get("fileName");
        File folder = new File("D:\\FileUploadAndDownload\\fileuploadafter\\uploadFile\\",fileName);
        //文件查找
        if (!folder.exists()) {
            jsonObject.put("msg","文件未找到:"+fileName);
            return new ResponseEntity<>(jsonObject,headers,HttpStatus.BAD_REQUEST);
        }
        else {
            //判断是否有删除权限
            //以文件名取出upname
            String upname=fileTest.filenametoupname(fileName);
            //以token取出username
            String username=userTest.tokentoname(token);
            //以token取出admin
            String admin=userTest.tokentoadmin(token);
            if(Objects.equals(username,upname) || Objects.equals(admin,"admin")){
                if (folder.getName().equals(fileName)) {
                    folder.delete();
                    fileTest.delfile(fileName);
                    jsonObject.put("msg","文件删除成功:"+fileName);
                    return new ResponseEntity<>(jsonObject,headers,HttpStatus.OK);
                }
            }else{
                jsonObject.put("msg","删除失败,权限不足");
                return new ResponseEntity<>(jsonObject,headers,HttpStatus.BAD_REQUEST);
            }
        }
        //------
        jsonObject.put("msg","服务器被外星人搬走了,删除出现异常,文件删除失败");
        return new ResponseEntity<>(jsonObject,headers,HttpStatus.BAD_REQUEST);
    }


    //文件下载接口
    @GetMapping(value = "/download")
    public void download(@RequestParam("fileName") String fileName, HttpServletResponse response) throws IOException {
        // 获得待下载文件所在文件夹的绝对路径
        String realPath = "D:\\FileUploadAndDownload\\fileuploadafter\\uploadFile";
        // 获得文件输入流
        FileInputStream inputStream = new FileInputStream(new File(realPath, fileName));
        // 设置响应头、以附件形式打开文件
        String encodedFileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("content-disposition", "attachment; fileName=" + encodedFileName);
        ServletOutputStream outputStream = response.getOutputStream();
        int len = 0;
        byte[] data = new byte[1024];
        while ((len = inputStream.read(data)) != -1) {
            outputStream.write(data, 0, len);
        }
        outputStream.close();
        inputStream.close();
    }


    //修改描述
    @PostMapping("fileupdate")
    public ResponseEntity<JSONObject> fileupdate(@RequestBody Map<String,String> params,@RequestHeader("Authorization")String token){
        //----------------
        HttpHeaders headers=new HttpHeaders();
        headers.add("Custom-header","foo");
        //----------------
        String describle=params.get("describle");
        String filename=params.get("filename");
        //以token查询username
        String upname=userTest.tokentoname(token);
        //以token查询admin
        String admin=userTest.tokentoadmin(token);
        //以filename查询upname
        String fupname=fileTest.filenametoupname(filename);

        JSONObject jsonObject=new JSONObject();
        if(Objects.equals(fupname,upname) || Objects.equals(admin,"admin")){
            fileTest.fileupdate(filename,describle);
            jsonObject.put("msg","修改成功");
            return new ResponseEntity<>(jsonObject,headers,HttpStatus.OK);
        }else{
            jsonObject.put("msg","修改失败,权限不足");
            return  new ResponseEntity<>(jsonObject,headers,HttpStatus.BAD_REQUEST);
        }
    }


    //文件搜索
    @PostMapping("/filesearch")
    public ResponseEntity<Object> filesearch(@RequestParam("searchValue") String param,@RequestParam("idx") Integer idx,@RequestParam("type") String type,@RequestParam("category")String category,@RequestHeader("Authorization") String token)throws Exception{
        //----------------
        HttpHeaders headers=new HttpHeaders();
        headers.add("Custom-header","foo");
        //----------------
        JSONObject jsonObject=new JSONObject();
        Integer idx1=(idx-1)*20;//分页

        if(!Objects.equals(category,"all")) {
            //------------------------------------------------
            if (Objects.equals(type, "public")) {
                List<JSONObject> cx2 = new ArrayList<JSONObject>();
                List<FileTable> cx1 = fileTest.filesearchcategory(param, idx1,category);
                for (FileTable obj : cx1) {
                    JSONObject js = new JSONObject();
                    js.put("id", obj.getId());
                    js.put("filename", obj.getFilename());
                    js.put("upname", obj.getUpname());
                    js.put("describle", obj.getDescrible());
                    js.put("shouchang", false);
                    js.put("filetype", obj.getFiletype());
                    cx2.add(js);
                }
                jsonObject.put("data", cx2);
                jsonObject.put("len", fileTest.filesearch1category(param,category));
                return new ResponseEntity<>(jsonObject, headers, HttpStatus.OK);
            } else if (Objects.equals(type, "privatecollect")) {
                List<JSONObject> cx2 = new ArrayList<JSONObject>();
                String username = userTest.tokentoname(token);
                List<Collectioned> cx1 = collectionTest.cxsearchcategory(idx1, param, username,category);
                for (Collectioned obj : cx1) {
                    JSONObject js = new JSONObject();
                    js.put("id", obj.getId());
                    js.put("filename", obj.getFilename());
                    js.put("upname", fileTest.cxupname(obj.getFilename()));
                    js.put("describle", fileTest.cxdescribe(obj.getFilename()));
                    js.put("shouchang", true);
                    js.put("filetype", obj.getFiletype());
                    cx2.add(js);
                }
                jsonObject.put("data", cx2);
                jsonObject.put("len", collectionTest.cxsearchlencategory(username, param,category));
                //取出所有长度
                return new ResponseEntity<>(jsonObject, headers, HttpStatus.OK);

            } else {
                //以token获取username
                String username = userTest.tokentoname(token);
                //私有
                jsonObject.put("data", fileTest.filesearchpscategory(param, idx1, username,category));
                jsonObject.put("len", fileTest.filesearchps1category(param, username,category));
                return new ResponseEntity<>(jsonObject, headers, HttpStatus.OK);
            }
            //------------------------------------------------
        }else{
            //------------------------------------------------
            if (Objects.equals(type, "public")) {
                List<JSONObject> cx2 = new ArrayList<JSONObject>();
                List<FileTable> cx1 = fileTest.filesearch(param, idx1);
                for (FileTable obj : cx1) {
                    JSONObject js = new JSONObject();
                    js.put("id", obj.getId());
                    js.put("filename", obj.getFilename());
                    js.put("upname", obj.getUpname());
                    js.put("describle", obj.getDescrible());
                    js.put("shouchang", false);
                    js.put("filetype", obj.getFiletype());
                    cx2.add(js);
                }
                jsonObject.put("data", cx2);
                jsonObject.put("len", fileTest.filesearch1(param));
                return new ResponseEntity<>(jsonObject, headers, HttpStatus.OK);
            } else if (Objects.equals(type, "privatecollect")) {
                List<JSONObject> cx2 = new ArrayList<JSONObject>();
                String username = userTest.tokentoname(token);
                List<Collectioned> cx1 = collectionTest.cxsearch(idx1, param, username);
                for (Collectioned obj : cx1) {
                    JSONObject js = new JSONObject();
                    js.put("id", obj.getId());
                    js.put("filename", obj.getFilename());
                    js.put("upname", fileTest.cxupname(obj.getFilename()));
                    js.put("describle", fileTest.cxdescribe(obj.getFilename()));
                    js.put("shouchang", true);
                    js.put("filetype", fileTest.cxfiletype(obj.getFilename()));
                    cx2.add(js);
                }
                jsonObject.put("data", cx2);
                jsonObject.put("len", collectionTest.cxsearchlen(username, param));
                //取出所有长度
                return new ResponseEntity<>(jsonObject, headers, HttpStatus.OK);

            } else {
                //以token获取username
                String username = userTest.tokentoname(token);
                //私有
                jsonObject.put("data", fileTest.filesearchps(param, idx1, username));
                jsonObject.put("len", fileTest.filesearchps1(param, username));
                return new ResponseEntity<>(jsonObject, headers, HttpStatus.OK);
            }
            //------------------------------------------------
        }
    }

    //文件查询
    @RequestMapping("filedisplay")
    public ResponseEntity<Object> cxdemo(@RequestParam("idx") Integer idx,@RequestParam("type") String type,@RequestParam("category")String category,@RequestHeader("Authorization") String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "foo");

        JSONObject jsonObject = new JSONObject();
        //数据
        Integer idx1 = (idx - 1) * 20;
        //分类 未搜索
        if(Objects.equals(category,"all")){

        //----------------------------------------
        if (Objects.equals(type, "public")) {
            //判断token是否成立
            if (Objects.equals(token, "")) {
                List<FileTable> cx1 = fileTest.cx(idx1);
                List<JSONObject> cx2 = new ArrayList<JSONObject>();
                for (FileTable obj : cx1) {
                    JSONObject js = new JSONObject();
                    js.put("id", obj.getId());
                    js.put("filename", obj.getFilename());
                    js.put("upname", obj.getUpname());
                    js.put("describle", obj.getDescrible());
                    js.put("shouchang", false);
                    js.put("filetype", obj.getFiletype());
                    cx2.add(js);
                }
                jsonObject.put("data", cx2);
                jsonObject.put("len", fileTest.datalength());
                return new ResponseEntity<>(jsonObject, headers, HttpStatus.OK);
            } else {
                //以token取出username
                String username = userTest.tokentoname(token);
                //以名字查询所有数据
                String id = String.valueOf(userTest.tokentoid(token));
                List<FileTable> cx1 = fileTest.cx(idx1);
                List<JSONObject> cx2 = new ArrayList<JSONObject>();

                for (FileTable obj : cx1) {
                    JSONObject js = new JSONObject();
                    if (fileTest.pdjson(id, obj.getFilename()) == 0) {
                        js.put("id", obj.getId());
                        js.put("filename", obj.getFilename());
                        js.put("describle", obj.getDescrible());
                        js.put("upname", obj.getUpname());
                        js.put("shouchang", false);
                        js.put("filetype", fileTest.cxfiletype(obj.getFilename()));
                        cx2.add(js);
                    } else {
                        js.put("id", obj.getId());
                        js.put("filename", obj.getFilename());
                        js.put("describle", obj.getDescrible());
                        js.put("upname", obj.getUpname());
                        js.put("shouchang", true);
                        js.put("filetype", fileTest.cxfiletype(obj.getFilename()));
                        cx2.add(js);
                    }
                }
                jsonObject.put("data", cx2);
                jsonObject.put("len", fileTest.datalength());
                return new ResponseEntity<>(jsonObject, headers, HttpStatus.OK);
            }

        } else if (Objects.equals(type, "privatecollect")) {
            //---
            if (userTest.cxtotken(token) <= 0) {
                jsonObject.put("msg", "token已过期,请重新登录");
                return new ResponseEntity<>(jsonObject, headers, HttpStatus.UNAUTHORIZED);
            }else{


            List<JSONObject> cx2 = new ArrayList<JSONObject>();
            String username = userTest.tokentoname(token);
            List<Collectioned> cx1 = collectionTest.cx(idx1, username);
            for (Collectioned obj : cx1) {
                if (Objects.equals(collectionTest.pddelfile(obj.getFilename()), 0)) {
                    collectionTest.del(obj.getFilename(), obj.getUsername());
                } else {
                    JSONObject js = new JSONObject();
                    js.put("id", obj.getId());
                    js.put("filename", obj.getFilename());
                    js.put("upname", fileTest.cxupname(obj.getFilename()));
                    js.put("describle", fileTest.cxdescribe(obj.getFilename()));
                    js.put("shouchang", true);
                    js.put("filetype", fileTest.cxfiletype(obj.getFilename()));
                    cx2.add(js);
                }

            }

            jsonObject.put("data", cx2);
            jsonObject.put("len", collectionTest.cxlen(username));
            //取出所有长度

            return new ResponseEntity<>(jsonObject, headers, HttpStatus.OK);

        }
        } else {
            if (userTest.cxtotken(token) <= 0) {
                jsonObject.put("msg", "token已过期,请重新登录");
                return new ResponseEntity<>(jsonObject, headers, HttpStatus.UNAUTHORIZED);
            } else {
                //以token取出username
                String username = userTest.tokentoname(token);
                //以名字查询所有数据
                String id = String.valueOf(userTest.tokentoid(token));
                List<FileTable> cx1 = fileTest.upnamesearch(username, idx1);

                List<JSONObject> cx2 = new ArrayList<>();

                for (FileTable obj : cx1) {
                    JSONObject js = new JSONObject();
                    if (fileTest.pdjson(id, obj.getFilename()) == 0) {
                        js.put("id", obj.getId());
                        js.put("filename", obj.getFilename());
                        js.put("describle", obj.getDescrible());
                        js.put("upname", obj.getUpname());
//                        js.put("shouchang",obj.getShouchang());
                        js.put("shouchang", false);
                        js.put("filetype", fileTest.cxfiletype(obj.getFilename()));
                        cx2.add(js);
                    } else {
                        js.put("id", obj.getId());
                        js.put("filename", obj.getFilename());
                        js.put("describle", obj.getDescrible());
                        js.put("upname", obj.getUpname());
                        js.put("shouchang", true);
                        js.put("filetype", fileTest.cxfiletype(obj.getFilename()));
                        cx2.add(js);
                    }
                }

                jsonObject.put("data", cx2);
                jsonObject.put("len", fileTest.datalength1(username));
                //取出所有长度

                return new ResponseEntity<>(jsonObject, headers, HttpStatus.OK);
            }

        }
        //------------------------------------------------------//-------------------------------------------------
    }else{
            //---------------
            if (Objects.equals(type, "public")) {
                //判断token是否成立
                if (Objects.equals(token, "")) {

                    List<FileTable> cx1 = fileTest.cxcategory(idx1,category);
                    List<JSONObject> cx2 = new ArrayList<JSONObject>();
                    for (FileTable obj : cx1) {
                        JSONObject js = new JSONObject();
                        js.put("id", obj.getId());
                        js.put("filename", obj.getFilename());
                        js.put("upname", obj.getUpname());
                        js.put("describle", obj.getDescrible());
                        js.put("shouchang", false);
                        js.put("filetype", obj.getFiletype());
                        cx2.add(js);
                    }
                    jsonObject.put("data", cx2);
                    jsonObject.put("len", fileTest.datalengthcategory(category));
                    return new ResponseEntity<>(jsonObject, headers, HttpStatus.OK);
                } else {
                    //以token取出username
                    String username = userTest.tokentoname(token);
                    //以名字查询所有数据
                    String id = String.valueOf(userTest.tokentoid(token));
                    List<FileTable> cx1 = fileTest.cxcategory(idx1,category);
                    List<JSONObject> cx2 = new ArrayList<JSONObject>();

                    for (FileTable obj : cx1) {
                        JSONObject js = new JSONObject();
                        if (fileTest.pdjson(id, obj.getFilename()) == 0) {
                            js.put("id", obj.getId());
                            js.put("filename", obj.getFilename());
                            js.put("describle", obj.getDescrible());
                            js.put("upname", obj.getUpname());
                            js.put("shouchang", false);
                            js.put("filetype", fileTest.cxfiletype(obj.getFilename()));
                            cx2.add(js);
                        } else {
                            js.put("id", obj.getId());
                            js.put("filename", obj.getFilename());
                            js.put("describle", obj.getDescrible());
                            js.put("upname", obj.getUpname());
                            js.put("shouchang", true);
                            js.put("filetype", fileTest.cxfiletype(obj.getFilename()));
                            cx2.add(js);
                        }
                    }
                    jsonObject.put("data", cx2);
                    jsonObject.put("len", fileTest.datalengthcategory(category));
                    return new ResponseEntity<>(jsonObject, headers, HttpStatus.OK);
                }

            } else if (Objects.equals(type, "privatecollect")) {

                if (userTest.cxtotken(token) <= 0) {
                    jsonObject.put("msg", "token已过期,请重新登录");
                    return new ResponseEntity<>(jsonObject, headers, HttpStatus.UNAUTHORIZED);
                }else {

                    //--
                    List<JSONObject> cx2 = new ArrayList<JSONObject>();
                    String username = userTest.tokentoname(token);
                    List<Collectioned> cx1 = collectionTest.cxcategory(idx1, username, category);
                    for (Collectioned obj : cx1) {
                        if (Objects.equals(collectionTest.pddelfile(obj.getFilename()), 0)) {
                            collectionTest.del(obj.getFilename(), obj.getUsername());
                        } else {
                            JSONObject js = new JSONObject();
                            js.put("id", obj.getId());
                            js.put("filename", obj.getFilename());
                            js.put("upname", fileTest.cxupname(obj.getFilename()));
                            js.put("describle", fileTest.cxdescribe(obj.getFilename()));
                            js.put("shouchang", true);
                            js.put("filetype", obj.getFiletype());
                            cx2.add(js);
                        }

                    }

                    jsonObject.put("data", cx2);
                    jsonObject.put("len", collectionTest.cxlencategory(username, category));
                    //取出所有长度

                    return new ResponseEntity<>(jsonObject, headers, HttpStatus.OK);
                }
            } else {
                if (userTest.cxtotken(token) <= 0) {
                    jsonObject.put("msg", "token已过期,请重新登录");
                    return new ResponseEntity<>(jsonObject, headers, HttpStatus.UNAUTHORIZED);
                } else {
                    //以token取出username
                    String username = userTest.tokentoname(token);
                    //以名字查询所有数据
                    String id = String.valueOf(userTest.tokentoid(token));
                    List<FileTable> cx1 = fileTest.upnamesearchcategory(username, idx1,category);
                    List<JSONObject> cx2 = new ArrayList<JSONObject>();

                    for (FileTable obj : cx1) {
                        JSONObject js = new JSONObject();
                        if (fileTest.pdjson(id, obj.getFilename()) == 0) {
                            js.put("id", obj.getId());
                            js.put("filename", obj.getFilename());
                            js.put("describle", obj.getDescrible());
                            js.put("upname", obj.getUpname());
                            js.put("shouchang", false);
                            js.put("filetype", obj.getFiletype());
                            cx2.add(js);
                        } else {
                            js.put("id", obj.getId());
                            js.put("filename", obj.getFilename());
                            js.put("describle", obj.getDescrible());
                            js.put("upname", obj.getUpname());
                            js.put("shouchang", true);
                            js.put("filetype", obj.getFiletype());
                            cx2.add(js);
                        }
                    }

                    jsonObject.put("data", cx2);
                    jsonObject.put("len", fileTest.datalength1category(username,category));
                    //取出所有长度

                    return new ResponseEntity<>(jsonObject, headers, HttpStatus.OK);
                }

            }
            //---------------
        }
    }


    //收藏测试
    @PostMapping("/collection")
    public ResponseEntity<Object> collection(@RequestBody Map<String,String> params,@RequestHeader("Authorization") String token){
        //----------------
        HttpHeaders headers=new HttpHeaders();
        headers.add("Custom-header","foo");
        //----------------
        JSONObject jsonObject=new JSONObject();
        if(Objects.equals(token,"")){
            jsonObject.put("msg", "token已过期,请重新登录");
            return new ResponseEntity<>(jsonObject,headers,HttpStatus.BAD_REQUEST);
        }else {
            String filename = params.get("filename");
            String collect = params.get("collect");
            Integer ids = userTest.tokentoid(token);
            String username = userTest.tokentoname(token);
            //开启收藏
            if (Objects.equals(collect, "false")) {
                if (Objects.equals(collectionTest.pdcollect(username, filename), 0)) {
                    fileTest.insertjson(String.valueOf(ids), filename);
                    jsonObject.put("msg", "收藏成功");
                    String filetypes=fileTest.cxfiletype(filename);

                    collectionTest.add(filename, username,filetypes);
                    return new ResponseEntity<>(jsonObject, headers, HttpStatus.OK);
                } else {
                    jsonObject.put("msg", "收藏失败,因为已经收藏了");
                    return new ResponseEntity<>(jsonObject, headers, HttpStatus.BAD_REQUEST);
                }
            } else {
                fileTest.deljson(String.valueOf(ids), filename);
                jsonObject.put("msg", "取消收藏成功");
                collectionTest.del(filename, username);
                return new ResponseEntity<>(jsonObject, headers, HttpStatus.OK);
            }
        }

    }
}