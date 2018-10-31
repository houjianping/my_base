package com.androidapp.utils;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * JSON解析二次封装
 */
public class JsonUtils {

    private JsonUtils() {
    }

    /**
     * @param src :将要被转化的对象
     * @return :转化后的JSON串
     * @MethodName : toJson
     * @Description : 将对象转为JSON串，此方法能够满足大部分需求
     */
    public static String toJson(Object src) {
        if (null == src) {
            return getGson().toJson(JsonNull.INSTANCE);
        }
        try {
            return getGson().toJson(src);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param json
     * @param classOfT
     * @return
     * @MethodName : fromJson
     * @Description : 用来将JSON串转为对象，但此方法不可用来转带泛型的集合
     */
    public static <T> Object fromJson(String json, Class<T> classOfT) {
        try {
            return getGson().fromJson(json, (Type) classOfT);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param json
     * @param typeOfT
     * @return
     * @MethodName : fromJson
     * @Description : 用来将JSON串转为对象，此方法可用来转带泛型的集合，如：Type为 new
     * TypeToken<List<T>>(){}.getType()
     * ，其它类也可以用此方法调用，就是将List<T>替换为你想要转成的类
     */
    public static Object fromJson(String json, Type typeOfT) {
        try {
            return getGson().fromJson(json, typeOfT);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取json中的某个值
     *
     * @param json
     * @param key
     * @return
     */
    public static String getValue(String json, String key) {
        try {
            JSONObject object = new JSONObject(json);
            return object.getString(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取json中的list值
     *
     * @param json
     * @return
     */
    public static String getListValue(String json) {
        try {
            JSONObject object = new JSONObject(json);
            return object.getString("list");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Double getDoubleValue(String json, String key) {
        try {
            JSONObject object = new JSONObject(json);
            return object.getDouble(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getIntValue(String json, String key) {
        try {
            JSONObject object = new JSONObject(json);
            return object.getInt(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static final String TAG = "JsonUtil.java";

    public static <T> T toBean(String gsonString, Class<T> cls) {
        T t = null;
        if (getGson() != null) {
            t = getGson().fromJson(gsonString, cls);
        }
        return t;
    }

    public static <T> T toBean(JsonElement jsonObject, Class<T> cls) {
        T t = null;
        if (getGson() != null) {
            t = getGson().fromJson(jsonObject, cls);
        }
        return t;
    }

    /**
     * 解析Base64对象
     */
    public static <T> T parse64Object(String responseBody, TypeToken<T> typeToken, String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        Gson gson = getGson();
        Type type = typeToken.getType();
        T bean = gson.fromJson(json, type);
        return bean;
    }

    public static Gson getGson() {
        GsonBuilder b = new GsonBuilder();
        BooleanSerializer serializer = new BooleanSerializer();
        LongSerializer longSerializer = new LongSerializer();
        DoubleSerializer doubleSerializer = new DoubleSerializer();
        IntegerSerializer integerSerializer = new IntegerSerializer();
        b.registerTypeAdapter(Boolean.class, serializer);
        b.registerTypeAdapter(boolean.class, serializer);
        b.registerTypeAdapter(Long.class, longSerializer);
        b.registerTypeAdapter(long.class, longSerializer);
        b.registerTypeAdapter(Double.class, doubleSerializer);
        b.registerTypeAdapter(double.class, doubleSerializer);
        b.registerTypeAdapter(Integer.class, integerSerializer);
        b.registerTypeAdapter(int.class, integerSerializer);
        return b.create();
    }

    public static class StringSerializer implements JsonSerializer<String>, JsonDeserializer<String> {

        @Override
        public JsonElement serialize(String arg0, Type arg1, JsonSerializationContext arg2) {
            return new JsonPrimitive(arg0);
        }

        @Override
        public String deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
            try {
                if (TextUtils.isEmpty(arg0.getAsString())) {
                    return "";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return arg0.getAsString();
        }
    }

    public static class IntegerSerializer implements JsonSerializer<Integer>, JsonDeserializer<Integer> {

        @Override
        public JsonElement serialize(Integer arg0, Type arg1, JsonSerializationContext arg2) {
            return new JsonPrimitive(arg0);
        }

        @Override
        public Integer deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
            try {
                return arg0.getAsInt();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                return StringUtil.parseInt(arg0.getAsString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }
    }

    public static class LongSerializer implements JsonSerializer<Long>, JsonDeserializer<Long> {

        @Override
        public JsonElement serialize(Long arg0, Type arg1, JsonSerializationContext arg2) {
            return new JsonPrimitive(arg0);
        }

        @Override
        public Long deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
            try {
                return arg0.getAsLong();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                return StringUtil.parseLong(arg0.getAsString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0l;
        }
    }

    public static class DoubleSerializer implements JsonSerializer<Double>, JsonDeserializer<Double> {

        @Override
        public JsonElement serialize(Double arg0, Type arg1, JsonSerializationContext arg2) {
            return new JsonPrimitive(arg0);
        }

        @Override
        public Double deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
            try {
                return arg0.getAsDouble();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                return StringUtil.parseDouble(arg0.getAsString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0d;
        }
    }

    public static class BooleanSerializer implements JsonSerializer<Boolean>, JsonDeserializer<Boolean> {

        @Override
        public JsonElement serialize(Boolean arg0, Type arg1, JsonSerializationContext arg2) {
            return new JsonPrimitive(arg0 ? 1 : 0);
        }

        @Override
        public Boolean deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
            String value = arg0.getAsString().toLowerCase();
            if (value.equals("true")) {
                return true;
            } else if (value.equals("false")) {
                return false;
            } else {
                try {
                    return arg0.getAsInt() == 1;
                } catch (Exception e) {
                    return StringUtil.parseInt(arg0.getAsString()) == 1;
                }
            }
        }
    }

    /**
     * 解析json数据
     *
     * @param responseBody
     * @param typeToken
     * @return
     */
    public static <T> T parsesObject(String responseBody, TypeToken<T> typeToken) {
        if (TextUtils.isEmpty(responseBody)) {
            return null;
        }
        Gson gson = getGson();
        Type type = typeToken.getType();
        T bean = gson.fromJson(responseBody, type);
        return bean;
    }

    /**
     * @param responseBody
     * @param c
     * @param <T>
     * @return
     */
    public static <T> T parsesObject(String responseBody, Class<T> c) {
        if (TextUtils.isEmpty(responseBody)) {
            return null;
        }
        Gson gson = getGson();
        T bean = gson.fromJson(responseBody, c);
        return bean;
    }

    public static Map<String, Object> getMap(String json) {
        return getMap(json, null);
    }

    public static Map<String, String> getMapString(String json) {
        return getMapString(json, null);
    }

    public static Map<String, Object> getMap(String json, Map<String, Object> defValue) {
        try {
            Map<String, Object> map = parsesObject(json, new TypeToken<Map<String, Object>>() {
            });
            return map;
        } catch (Exception e) {
            return defValue;
        }
    }

    public static Map<String, String> getMapString(String json, Map<String, String> defValue) {
        try {
            return parsesObject(json, new TypeToken<Map<String, String>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defValue;
    }
}
