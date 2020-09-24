package com.at.json1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author sealll
 * @describe
 * @time 2020/9/11 20:28
 */
public class JSONWriter {
    public static void write(String str,String path){
        byte[] bytes = str.getBytes();
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(path);
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
