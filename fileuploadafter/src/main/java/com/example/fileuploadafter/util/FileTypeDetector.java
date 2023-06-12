package com.example.fileuploadafter.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileTypeDetector {
    public static String getFileType(String fileName) throws IOException {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") || extension.equals("gif")) {
            return "image";
        } else if (extension.equals("mp4") || extension.equals("avi") || extension.equals("mov") || extension.equals("wmv")) {
            return "video";
        } else if (extension.equals("mp3") || extension.equals("wav") || extension.equals("flac") || extension.equals("aac")) {
            return "audio";
        } else if( extension.equals("dpsx") || extension.equals("etx") || extension.equals("wpsx") || extension.equals("wdb") || extension.equals("dpt") || extension.equals("ett") || extension.equals("wpt") || extension.equals("et") ||extension.equals("dps") || extension.equals("doc") || extension.equals("docx") || extension.equals("wps") || extension.equals("txt") || extension.equals("docx") || extension.equals("xls") || extension.equals("xlsx") || extension.equals("ppt") || extension.equals("pptx") || extension.equals("pdf")) {
            return "document";
        } else {
            return "other";
        }
    }
}