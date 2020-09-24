package com.at.json1;

import java.util.ArrayList;

/**
 * @author sealll
 * @describe
 * @time 2020/9/11 13:10
 */
public class ObjectToken extends Token{
    private ArrayList<KeyToken> keys = new ArrayList<>();
    private String objectName;

    public ObjectToken() {
        index = JSONExample.ao.size();
        JSONExample.ao.add(this);
    }

    public void addKey(KeyToken key){
        keys.add(key);
    }

    public ArrayList<KeyToken> getKeys() {
        return keys;
    }

    public void setKeys(ArrayList<KeyToken> keys) {
        this.keys = keys;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String registerHash(){
        StringBuilder res = new StringBuilder("{\n");
        for (KeyToken key : keys) {
            Token value = key.getValue();
            String s = "\tvar " + JSONExample.keys.get(key.getIndex()) + " : ";
            String className;
            if(value instanceof ObjectToken){
                if(((ObjectToken) value).getObjectName() == null) {
                    ((ObjectToken) value).registerHash();
                }
                className = ((ObjectToken) value).getObjectName();
            }else if(value instanceof ArrayToken){
                className = ((ArrayToken) value).getTypeString();
            } else{
                className = value.getType().toString() + "?";
            }
            s += className + "\n";
            res.append(s);
        }
        res.append('}');
        if(!JSONExample.structures.containsKey(new String(res))) {

            JSONExample.structures.put(new String(res), "Class" + JSONExample.structures.size());
        }
        this.setObjectName(JSONExample.structures.get(new String(res)));
        return new String(res);
    }

    public String getVar(){
        StringBuilder res = new StringBuilder(objectName + "(");
        boolean first = true;
        for (KeyToken key : keys) {
            if (first) {
                first = false;
            }else{
                res.append(", ");
            }
            Token value = key.getValue();
            String next;
            if(value instanceof ObjectToken){
                next = ((ObjectToken) value).getVar();
            }else if(value instanceof ArrayToken){
                next = "arr" + value.getIndex();
            }else{
                assert key.getValue().getType().getArrayList() != null;
                next = key.getValue().getType().getArrayList().get(key.getValue().getIndex());
            }

            res.append(next);

        }
        res.append(")");
        return new String(res);
    }
}
