package com.at.json1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sealll
 * @describe
 * @time 2020/9/10 19:07
 */
public class JSONExample {
    public static Map<String,String> structures = new HashMap<>();
    public static ArrayList<ObjectToken> ao = new ArrayList<>();
    public static ArrayList<ArrayToken> aa = new ArrayList<>();
    public static ArrayList<String> keys = new ArrayList<>();

    public static ArrayList<String> ints = new ArrayList<>();
    public static ArrayList<String> strs = new ArrayList<>();
    public static ArrayList<String> flos = new ArrayList<>();
    public static ArrayList<String> boos = new ArrayList<>();



    public Token strToToken(String json){
        int index = 0;
        Token root;
        TokenBuilder tb = new TokenBuilder();
        if(json.charAt(0) == '{'){
            root = new ObjectToken();
            tb.buildObjectToken(json,0, (ObjectToken) root);
            ((ObjectToken) root).registerHash();
        }else{
            root = new ArrayToken();
            tb.buildArrayToken(json,0, (ArrayToken) root);

        }
        return root;

    }

    public String composeTheStructures(){
        StringBuilder res = new StringBuilder();
        for (Map.Entry<String, String> entry: structures.entrySet()) {
            res.append("struct ").append(entry.getValue());
            res.append(entry.getKey());
            res.append("\n\n");
        }
        return new String(res);

    }

    public String arrayStatements(){
        StringBuilder res = new StringBuilder();
        for (ArrayToken arrayToken : aa) {
            String statement = arrayToken.getStatement();
            res.insert(0,statement + "\n");
        }
        res.append("\n");
        return new String(res);
    }

    public String finalResult(Token root){
        String res = "";
        res += composeTheStructures();
        res += arrayStatements();
        if(root instanceof ObjectToken){
            res += "var res = " + ((ObjectToken) root).getVar();
        }
        return res;
    }

}
