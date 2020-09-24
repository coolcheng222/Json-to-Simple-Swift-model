package com.at.json1;


import java.util.ArrayList;

/**
 * @author zhou
 * @describe
 * @time 2020/9/11 12:33
 */
public class Token{
    protected Type type;
    protected int index;

    public Token(){

    }

    public Token(Type type,String val){
        this.type = type;
        ArrayList<String> arrayList = type.getArrayList();
        int i = arrayList.indexOf(val);
        if(i != -1){
            index = i;
        }else{
            index = arrayList.size();
            arrayList.add(val);
        }

    }
    public Token(Type type, int index) {
        this.type = type;
        this.index = index;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
enum Type{
    Boolean,Int,Float,String;

    public ArrayList<String> getArrayList(){
        switch (this){
            case Int:return JSONExample.ints;
            case Float: return JSONExample.flos;
            case Boolean: return JSONExample.boos;
            case String : return JSONExample.strs;
        }
        return null;
    }
}