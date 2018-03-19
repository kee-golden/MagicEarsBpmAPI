package com.magicears.bpm.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

public class JsonUtil {

    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    /**
     * 转成bean
     *
     * @param jsonStr
     * @param cls
     * @return
     */
    public static <T> T jsonToBean(String jsonStr, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(jsonStr, cls);
        }
        return t;
    }


    /**
     * 转成list
     *
     * @param jsonStr
     * @param cls
     * @return
     */
    public static <T> List<T> jsonToList(String jsonStr, Class<T> cls) {
        List<T> list = null;
        if (gson != null) {
            list = gson.fromJson(jsonStr, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }



    /**
     * 转成map的
     *
     * @param jsonStr
     * @return
     */
    public static <T> Map<String, T> jsonToMaps(String jsonStr) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(jsonStr, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }


}
