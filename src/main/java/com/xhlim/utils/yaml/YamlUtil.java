package com.xhlim.utils.yaml;

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
 * yaml
 *
 * @author xhlim@outlook.com
 * @date 2018-11-05
 */
public class YamlUtil {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper(new YAMLFactory());

    private YamlUtil() {

    }

    public static ObjectMapper getInstance() {
        return OBJECT_MAPPER;
    }

    /**
     * json 转 yaml
     *
     * @param json
     * @return
     * @throws JsonProcessingException
     */
    public static String jsonStrToYaml(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        JsonNode node = mapper.readTree(json);
        return OBJECT_MAPPER.writeValueAsString(node);
    }

    /**
     * obj 转 yaml
     *
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public static String objToYaml(Object obj) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(obj);
    }

    /**
     * yaml 转 pojo 对象
     *
     * @param yaml    yaml
     * @param classes 返回类型
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T extends Object> T yamlToPojo(String yaml, Class<T> classes) throws IOException {
        return OBJECT_MAPPER.readValue(yaml, classes);
    }

    /**
     * yaml 转 pojo 对象
     *
     * <pre>
     *  eg:
     *      User user = YamlUtils.yamlToPojo(yaml, new TypeReference&lt;User&gt;() {});
     * </pre>
     *
     * @param yaml          yaml
     * @param typeReference 自定义 TypeReference
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T extends Object> T yamlToPojo(String yaml, TypeReference<T> typeReference) throws IOException {
        return OBJECT_MAPPER.readValue(yaml, typeReference);
    }

    /**
     * yaml 转 map
     *
     * @param yaml yaml
     * @return
     * @throws IOException
     */
    public static Map yamlToMap(String yaml) throws IOException {
        return OBJECT_MAPPER.readValue(yaml, Map.class);
    }

    /**
     * yaml 转 Map
     *
     * <pre>
     *  eg:
     *      Map<String, User> map = YamlUtils.yamlToMap(yaml, User.class);
     * </pre>
     *
     * @param yaml    yaml
     * @param classes 返回类型
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T extends Object> Map<String, T> yamlToMap(String yaml, Class<T> classes) throws IOException {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(Map.class, String.class, classes);
        return OBJECT_MAPPER.readValue(yaml, javaType);
    }

    /**
     * yaml 转 List
     *
     * @param yaml    yaml
     * @param classes 返回类型
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T extends Object> List<T> yamlToList(String yaml, Class<T> classes) throws IOException {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class, classes);
        return OBJECT_MAPPER.readValue(yaml, javaType);
    }

    /**
     * yaml 转 all
     *
     *
     * <pre>
     *  yaml 转指定类型的Map
     *      Map&lt;String, User&gt; map = YamlUtils.yamlToObj(yaml, Map.class, String.class, User.class);
     *  yaml 转指定类型的list
     *      List&lt;User&gt; result = YamlUtils.yamlToObj(yaml, List.class, User.class);
     * </pre>
     *
     * @param yaml             yaml
     * @param parametrized     参数
     * @param parameterClasses 参数类
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T extends Object> T yamlToObj(String yaml, Class<?> parametrized, Class<?>... parameterClasses) throws IOException {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(parametrized, parameterClasses);
        return OBJECT_MAPPER.readValue(yaml, javaType);
    }

    /**
     * yaml 转 all
     *
     * <pre>
     *  使用方法
     *     JavaType javaType = YamlUtils.getInstance().getTypeFactory().constructParametricType(List.class, User.class);
     *     List&lt;User&gt; list = YamlUtils.yamlToObj(yaml, javaType);
     * </pre>
     *
     * @param yaml     yaml
     * @param javaType
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T yamlToObj(String yaml, JavaType javaType) throws IOException {
        return OBJECT_MAPPER.readValue(yaml, javaType);
    }

}
