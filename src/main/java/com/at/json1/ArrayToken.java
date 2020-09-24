package com.at.json1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author sealll
 * @describe
 * @time 2020/9/11 13:24
 */
public class ArrayToken extends Token{
    private ArrayList<Token> arr = new ArrayList<>();
    private Set<String> typeSet = new HashSet<>();

    public ArrayToken() {
        index = JSONExample.aa.size();
        JSONExample.aa.add(this);
    }

    public void addToken(Token token){
        arr.add(token);
        if(token instanceof ObjectToken){
            ((ObjectToken) token).registerHash();
            typeSet.add(((ObjectToken) token).getObjectName());
        }else{
            typeSet.add(token.getType().toString());
        }
    }

    public String getTypeString(){
        if(typeSet.size() == 1){
            return "[" + typeSet.iterator().next() + "]";
        }else{
            return "[Any]";
        }
    }

    public String getStatement(){
        StringBuilder res = new StringBuilder("var arr" + index + " : ");
        res.append(getTypeString());
        
        res.append(" = [");
        boolean flag = true;
        for (Token token : arr) {
            if(flag){
                flag = false;
            }else{
                res.append(",");
            }
            if(token instanceof ObjectToken){
                res.append(((ObjectToken) token).getVar());
            }else{
                int index = token.getIndex();
                res.append(token.getType().getArrayList().get(index));
            }
        }
        res.append("]");
        return res.toString();

    }

}
