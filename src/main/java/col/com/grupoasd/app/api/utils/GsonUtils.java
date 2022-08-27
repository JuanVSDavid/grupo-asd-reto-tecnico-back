package col.com.grupoasd.app.api.utils;

import com.google.gson.Gson;

public class GsonUtils {

    public static String toJson(Object obj) {
        return new Gson().toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return new Gson().fromJson(json, clazz);
    }

    public static <T> T fromJson(Object src, Class<T> clazz) {
        return new Gson().fromJson(toJson(src), clazz);
    }
}
