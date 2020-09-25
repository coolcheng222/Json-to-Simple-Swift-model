package com.at.json1;

/**
 * @author
 * @describe
 * @time 2020/9/11 13:30
 */
public class TokenBuilder {
    /**
     *
     * @param json
     * @param start [的索引
     * @param prod
     * @return ]的索引
     */
    public int buildArrayToken(String json,int start,ArrayToken prod){
        char cur;
        String val = "";
        boolean endFlag = false;

        for (int i = start + 1;; i++) {

            cur = json.charAt(i);
            if(Character.isSpaceChar(cur)){
                continue;
            }
            if (cur == '{') {
                ObjectToken ot = new ObjectToken();
                int i1 = buildObjectToken(json, i, ot);
                i = i1 + 1;
                prod.addToken(ot);
                if(json.charAt(i) == ']'){
                    return i;
                }
                continue;
            }
            if(cur != ',' && cur != ']'){
                val += cur;
            }else{
                if(cur == ']'){
                    endFlag = true;
                }
                prod.addToken(new Token(inferType(val),val));
                val = "";
            }
            if(endFlag){
                endFlag = false;
                return i;
            }
        }

    }

    public Type inferType(String val){
        if(val.length() == 0){
            return Type.Int;
        }
        char c = val.charAt(0);
        switch (c){
            case 't':
            case 'f': return Type.Boolean;
            case '\"':
            case '\'':
            case 'n': return Type.String;
        }
        if(val.indexOf('.') != -1){
            return Type.Float;
        }else{
            return Type.Int;
        }

    }

    public int buildObjectToken(String json,int start,ObjectToken prod){
        char cur;
        KeyToken kt = null;
        boolean valMode = false;
        boolean endMode = false;
        StringBuilder anyUse = new StringBuilder();
        for (int i = start + 1;; i++) {
            cur = json.charAt(i);
            if(cur == ':'){
                if(valMode){
                    anyUse.append(cur);
                    continue;
                }
                valMode = true;
                kt = new KeyToken(anyUse.toString());
                anyUse = new StringBuilder();
                prod.addKey(kt);
                continue;
            }else if(cur == '}' || cur == ','){
                valMode = false;
                if(cur == '}'){
                    endMode = true;

                }
                assert kt != null;
                kt.setValue(new Token(inferType(anyUse.toString()), anyUse.toString()));
                anyUse = new StringBuilder();
            }else if((cur == '\"' && !valMode) || Character.isSpaceChar(cur)){
                continue;
            }else if(cur == '{'){
                ObjectToken ot = new ObjectToken();
                valMode = false;
                int i1 = buildObjectToken(json, i, ot);
                i = i1 + 1;
                assert kt != null;
                kt.setValue(ot);
                if(json.charAt(i) == '}'){
                    return i;
                }
            }else if(cur == '['){
                valMode = false;
                ArrayToken at = new ArrayToken();
                int i1 = buildArrayToken(json,i,at);
                i = i1 + 1;
                assert kt != null;
                kt.setValue(at);
                if(json.charAt(i) == '}'){
                    return i;
                }
            }else{
                anyUse.append(cur);
            }
            if(endMode){
                return i;
            }

        }
    }
}
