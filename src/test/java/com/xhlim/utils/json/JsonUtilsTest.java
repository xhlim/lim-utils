package com.xhlim.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.xhlim.utils.entity.User;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xhlim@outlook.com
 * @create 2018-06-07
 */
public class JsonUtilsTest {

    @Test
    public void test_objToJson() throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        map.put("test", "sss");
        map.put("2s", "稍等");

        User user = new User();
        user.setName("张三");
        user.setAge(10);
        user.setIsRegist(true);

        Map<String, Object> user1 = new HashMap();
        user1.put("key1", "上三");
        user1.put("keee", 101);
        user1.put("sss,", true);

        List<Object> users = Arrays.asList(user, user1);


        Map<String, User> test = new HashMap<>();
        test.put("key", user);

        System.out.println(JsonUtils.objToJson(test));
        System.out.println(JsonUtils.objToJson(map));
        System.out.println(JsonUtils.objToJson(user));
        System.out.println(JsonUtils.objToJson(users));
    }


    @Test
    public void test_jsonToPojo() throws IOException {
        String json = "{\"name\":\"张三\",\"age\":10,\"isRegist\":true}";
        System.out.println(JsonUtils.jsonToPojo(json, User.class));
    }

    @Test
    public void test_jsonToPojo_typeReference() throws IOException {
        String json = "{\"name\":\"张三\",\"age\":10,\"isRegist\":true}";
        System.out.println((User) JsonUtils.jsonToPojo(json, new TypeReference<User>() {
        }));
    }


    @Test
    public void test_jsonToMap_typeReference() throws IOException {
        String json = "{\"key\":{\"name\":\"张三\",\"age\":10,\"isRegist\":true}}";
        Map<String, User> map = JsonUtils.jsonToMap(json, User.class);
        System.out.println(map.get("key").getName());
        System.out.println(JsonUtils.jsonToMap(json, User.class));
    }

    @Test
    public void test_jsonToList_typeReference() throws IOException {
        String json = "[{\"name\":\"张三\",\"age\":10,\"isRegist\":true},{\"name\":\"上三\",\"age\":101," +
                "\"isRegist\":false}]";
        List<User> list = JsonUtils.jsonToList(json, User.class);
        System.out.println(list.get(0).getAge());
        System.out.println(JsonUtils.jsonToList(json, User.class));
    }

    @Test
    public void test_jsonToObj_javaType() throws IOException {
        String json = "[{\"name\":\"张三\",\"age\":10,\"isRegist\":true},{\"name\":\"上三\",\"age\":101," +
                "\"isRegist\":false}]";
        JavaType javaType = JsonUtils.getInstance().getTypeFactory().constructParametricType(List.class, User.class);
        List<User> list = JsonUtils.jsonToObj(json, javaType);
        System.out.println(list.get(0).getAge());
        System.out.println((List) JsonUtils.jsonToObj(json, javaType));
    }

    @Test
    public void test_jsonToObj_K_V() throws IOException {

        String json = "{\"key\":{\"name\":\"张三\",\"age\":10,\"isRegist\":true}}";
        Map<String, User> map = JsonUtils.jsonToObj(json, Map.class, String.class, User.class);
        System.out.println(map);
        System.out.println(map.get("key").getAge());

        json = "[{\"name\":\"张三\",\"age\":10,\"isRegist\":true},{\"name\":\"上三\",\"age\":101," +
                "\"isRegist\":false}]";
        List<User> result = JsonUtils.jsonToObj(json, List.class, User.class);
        System.out.println(result);
        System.out.println(result.get(1).getName());
    }


}
