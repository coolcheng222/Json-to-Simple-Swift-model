package com.at.json1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author sealll
 * @describe
 * @time 2020/9/11 15:47
 */
public class JSONReader {
    public static String readJson(String path){
        InputStream resourceAsStream = null;
        int temp;
        String json = "";
        try {
            resourceAsStream = new FileInputStream(path);
            while((temp = resourceAsStream.read()) != -1){
                char c = (char)temp;
                if(!Character.isSpaceChar(c) && !(c == '\n') && (c != '\r')){
                    json += c;
                }
            }
            return json;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                resourceAsStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
