package com.androidapp.cachewebviewlib.utils;

import android.text.TextUtils;

import java.io.File;

public class FileUtil {

    public static void deleteDirs(String path,boolean isDeleteDir){

        if (TextUtils.isEmpty(path)){
            return;
        }
        File dir = new File(path);
        if (!dir.exists()){
            return;
        }
        File [] files = dir.listFiles();
        for ( File file :  files){
            if ( file.isDirectory()){
                deleteDirs(file.getAbsolutePath(),isDeleteDir);
            }else{
                file.delete();
            }
        }
        if (isDeleteDir){
            dir.delete();
        }

    }
}
