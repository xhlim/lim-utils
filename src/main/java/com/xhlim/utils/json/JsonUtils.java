package com.xhlim.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * json
 *
 * @author xhlim@outlook.com
 * @create 2018-06-07
 */
public class JsonUtils {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonUtils() {

    }

    public static ObjectMapper getInstance() {
        return OBJECT_MAPPER;
    }

    /**
     * obj 转 json
     *
     * @param obj 需要转换为json的对象
     * @return
     * @throws JsonProcessingException
     */
    public static String objToJson(Object obj) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(obj);
    }

    /**
     * json 转 pojo 对象
     *
     * @param json    json
     * @param classes 返回类型
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T extends Object> T jsonToPojo(String json, Class<T> classes) throws IOException {
        return OBJECT_MAPPER.readValue(json, classes);
    }

    /**
     * json 转 pojo 对象
     *
     * @param json          json
     * @param typeReference 自定义 TypeReference
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T extends Object> T jsonToPojo(String json, TypeReference typeReference) throws IOException {
        return OBJECT_MAPPER.readValue(json, typeReference);
    }

    /**
     * json 转 map
     *
     * @param json json
     * @return
     * @throws IOException
     */
    public static Map<String, Object> jsonToMap(String json) throws IOException {
        return OBJECT_MAPPER.readValue(json, Map.class);
    }

    /**
     * json 转 Map
     *
     * @param json    json
     * @param classes 返回类型
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T extends Object> Map<String, T> jsonToMap(String json, Class<T> classes) throws IOException {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(Map.class, String.class, classes);
        return OBJECT_MAPPER.readValue(json, javaType);
    }

    /**
     * json 转 List
     *
     * @param json    json
     * @param classes 返回类型
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T extends Object> List<T> jsonToList(String json, Class<T> classes) throws IOException {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class, classes);
        return OBJECT_MAPPER.readValue(json, javaType);
    }

    /**
     * json 转 all
     *
     * @param json             json
     * @param parametrized     参数
     * @param parameterClasses 参数类
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T extends Object> T jsonToObj(String json, Class<?> parametrized, Class<?>... parameterClasses)
            throws IOException {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(parametrized, parameterClasses);
        return OBJECT_MAPPER.readValue(json, javaType);
    }

    /**
     * json 转 all
     *
     * @param json     json
     * @param javaType
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T jsonToObj(String json, JavaType javaType) throws IOException {
        return OBJECT_MAPPER.readValue(json, javaType);
    }


}
