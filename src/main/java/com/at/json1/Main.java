package com.at.json1;

/**
 * @author sealll
 * @describe
 * @time 2020/9/11 14:39
 */
public class Main {
    public static void main(String[] args) {
//        String var = "[{\"a\":1}]";
//        ArrayToken at = new ArrayToken();
//        new TokenBuilder()
//                .buildArrayToken(var,0,at);
//        String statement = at.getStatement();
//        System.out.println(statement);
//
//        System.out.println();
//        String var2 = "{\"resultCode\":0,\"record\":[1,2]}";
//        ObjectToken ot = new ObjectToken();
//        new TokenBuilder()
//                .buildObjectToken(var2,0,ot);
        String file;
        if(args == null || args.length == 0){
            file = "Log.json";
        }else{
            file = args[0];
        }

        JSONExample jsonExample = new JSONExample();
        String s = JSONReader.readJson(file);
        assert s != null;
        Token token = jsonExample.strToToken(s);
        String s1 = jsonExample.finalResult(token);
        System.out.println(s1);

        JSONWriter.write(s1,"res.swift");

    }
}
