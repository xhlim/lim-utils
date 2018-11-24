package com.xhlim.utils.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * json
 *
 * @author xhlim@outlook.com
 * @create 2018-06-07
 */
public class JsonUtil {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper(new JsonFactory());

    private JsonUtil() {

    }

    public static ObjectMapper getInstance() {
        return OBJECT_MAPPER;
    }

    /**
     * yaml 字符串 转 json
     *
     * @param yaml 需要转换为json的yaml字符串
     * @return
     * @throws JsonProcessingException
     */
    public static String yamlStrToJson(String yaml) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        JsonNode node = mapper.readTree(yaml);
        return OBJECT_MAPPER.writeValueAsString(node);
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
     * <pre>
     *  eg:
     *      User user = JsonUtils.jsonToPojo(json, new TypeReference&lt;User&gt;() {});
     * </pre>
     *
     * @param json          json
     * @param typeReference 自定义 TypeReference
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T extends Object> T jsonToPojo(String json, TypeReference<T> typeReference) throws IOException {
        return OBJECT_MAPPER.readValue(json, typeReference);
    }

    /**
     * json 转 map
     *
     * @param json json
     * @return
     * @throws IOException
     */
    public static Map jsonToMap(String json) throws IOException {
        return OBJECT_MAPPER.readValue(json, Map.class);
    }

    /**
     * json 转 Map
     *
     * <pre>
     *  eg:
     *      Map<String, User> map = JsonUtils.jsonToMap(json, User.class);
     * </pre>
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
     *
     * <pre>
     *  json 转指定类型的Map
     *      Map&lt;String, User&gt; map = JsonUtils.jsonToObj(json, Map.class, String.class, User.class);
     *  json 转指定类型的list
     *      List&lt;User&gt; result = JsonUtils.jsonToObj(json, List.class, User.class);
     * </pre>
     *
     * @param json             json
     * @param parametrized     参数
     * @param parameterClasses 参数类
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T extends Object> T jsonToObj(String json, Class<?> parametrized, Class<?>... parameterClasses) throws IOException {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(parametrized, parameterClasses);
        return OBJECT_MAPPER.readValue(json, javaType);
    }

    /**
     * json 转 all
     *
     * <pre>
     *  使用方法
     *     JavaType javaType = JsonUtils.getInstance().getTypeFactory().constructParametricType(List.class, User.class);
     *     List&lt;User&gt; list = JsonUtils.jsonToObj(json, javaType);
     * </pre>
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
