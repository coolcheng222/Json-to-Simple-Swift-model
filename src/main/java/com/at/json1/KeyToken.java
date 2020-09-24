package com.at.json1;


/**
 * @author sealll
 * @describe
 * @time 2020/9/11 12:38
 */
public class KeyToken extends Token{

    private Token value;

    public KeyToken(String key){
        int i = JSONExample.keys.indexOf(key);
        if(i == -1){
            index = JSONExample.keys.size();
            JSONExample.keys.add(key);
        }else{
            index = i;
        }
    }

    public KeyToken(Type type, int index, Token value) {
        super(type, index);
        this.value = value;
    }

    public Token getValue() {
        return value;
    }

    public void setValue(Token value) {
        this.value = value;
    }

}
