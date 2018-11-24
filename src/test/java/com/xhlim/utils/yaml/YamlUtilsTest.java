package com.xhlim.utils.yaml;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import com.xhlim.utils.json.JsonUtils;
import com.xhlim.utils.yaml.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xhlim@outlook.com
 * @create 2018-06-08
 */
public class YamlUtilsTest {

    @Test
    public void test_objToYaml() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("test", "sss");
        map.put("2s", "稍等");

        User user = new User().setName("张三").setAge(10).setIsRegist(true);
        User.Child child = new User.Child().setName("张三").setAge(10).setIsRegist(true);
        User.Child child1 = new User.Child().setName("张三").setAge(10).setIsRegist(true);
        User.Child child2 = new User.Child().setName("张三").setAge(10).setIsRegist(true);
        user.setChild(child);
        user.setChilds(new ArrayList<User.Child>(){{
            add(child);
            add(child1);
            add(child2);
        }});


        User user2 = new User().setName("张三").setAge(10).setIsRegist(true);

        Map<String, Object> user1 = new HashMap<String, Object>() {{
            put("key1", "上三");
            put("keee", 101);
            put("sss,", true);
        }};

        List<Object> users = Arrays.asList(user2, user1);


        Map<String, User> test = new HashMap<String, User>(){{
            put("key", user);
        }};

        // System.out.println(YamlUtils.objToYaml(test));
        // System.out.println(YamlUtils.objToYaml(map));
        // System.out.println(YamlUtils.objToYaml(user));
        // System.out.println(YamlUtils.objToYaml(users));
        String ss = "{\"id1\":1,\"name\":\"paas\",\"title\":\"PAAS平台\",\"description\":\"开发环境\"}";
        String ss1 = "---\n" + "name: \"张三\"\n" + "age: 10\n" + "isRegist: true\n" + "child:\n" + "  name: \"张三\"\n" + "  age: 10\n" + "  isRegist: true\n" + "childs:\n" + "- name1: \"张三\"\n" + "  age: 10\n" + "  isRegist: true\n" + "- name: \"张三\"\n" + "  age: 10\n" + "  isRegist: true\n" + "- name: \"张三\"\n" + "  age: 10\n" + "  isRegist: true";
        String ss2 = "---\n" + "name: \"张三\"\n" + "age: 10\n" + "isRegist: true\n" + "child:\n" + "  name: \"张三\"\n" + "  age: 10\n" + "  isRegist: true\n" + "childs:\n" + "- name: \"张三\"\n" + "  age: 10\n" + "  isRegist: true\n" + "- name: \"张三\"\n" + "  age: 10\n" + "  isRegist: true\n" + "- name: \"张三\"\n" + "  age: 10\n" + "  isRegist: true";
        String ss3 = "---\n" + "name: \"张三\"\n" + "age: 10\n" + "isRegist: true\n" + "child:\n" + "  name: \"张三\"\n" + "  age: 10\n" + "  isRegist: true\n" + "childs:\n" + "- name: \"张三\"\n" + "  age: 10\n" + "  isRegist2: true\n" + "- name: \"张三\"\n" + "  age: 10\n" + "  isRegist: true\n" + "- name: \"张三\"\n" + "  age: 10\n" + "  isRegist: true";
        System.out.println(YamlUtils.jsonStrToYaml(ss));

    }
    @Test
    public void test() throws IOException {
        Yaml yaml = new Yaml();
        String path = "/Users/xhlim/IdeaProjects/xhlim/lim-utils/src/test/resources/app.yaml";
        File file = new File(path);
        FileInputStream stream = new FileInputStream(file);
        Iterable<Object> objects = yaml.loadAll(stream);
        System.out.println("===========");
        for (Object object : objects) {
            if (object instanceof Map) {
                System.out.println(object);
            }
        }
        System.out.println("===========");

        YAMLFactory factory = new YAMLFactory();
        YAMLParser parser = factory.createParser(file);
        ObjectMapper mapper = new ObjectMapper();
        TreeNode node = mapper.readTree(parser);
        System.out.println(node);
        System.out.println(node.isArray());
        JsonParser traverse = node.traverse();
        // TreeTraversingParser treeTraversingParser = new TreeTraversingParser(tr);
        // final Service service = mapper.readValue(treeTraversingParser, Service.class);

        System.out.println("-------------------------------------------------------------------------------------------------------");
        ObjectMapper mapper1 = new ObjectMapper(new YAMLFactory());
        List<Object> values = mapper1.readValues(parser, new TypeReference<ObjectNode>(){{}}).readAll();
        values.stream().forEach(e -> System.out.println(e));
        // RequestEntity.method(HttpMethod.GET,URI.create("http://{test}/sss")).contentType()
    }

    @Test
    public void test_yamlToPojo() throws IOException {
        String yaml = "{\"name\":\"张三\",\"age\":10,\"isRegist\":true}";
        System.out.println(YamlUtils.yamlToPojo(yaml, User.class));
    }

    @Test
    public void test_yamlToPojo_typeReference() throws IOException {
        String yaml = "{\"name\":\"张三\",\"age\":10,\"isRegist\":true}";
        User user = YamlUtils.yamlToPojo(yaml, new TypeReference<User>() {
        });
        System.out.println((User) YamlUtils.yamlToPojo(yaml, new TypeReference<User>() {
        }));
    }

    @Test
    public void test_yamlToMap() throws IOException {
        String yaml = "{\"name\":\"张三\",\"age\":10,\"isRegist\":true}";
        Map<String, User> map = YamlUtils.yamlToMap(yaml);
        System.out.println(map);
    }


    @Test
    public void test_yamlToMap_typeReference() throws IOException {
        String yaml = "{\"key\":{\"name\":\"张三\",\"age\":10,\"isRegist\":true}}";
        Map<String, User> map = YamlUtils.yamlToMap(yaml, User.class);
        System.out.println(map.get("key").getName());
        System.out.println(YamlUtils.yamlToMap(yaml, User.class));
    }

    @Test
    public void test_yamlToList_typeReference() throws IOException {
        String yaml = "[{\"name\":\"张三\",\"age\":10,\"isRegist\":true},{\"name\":\"上三\",\"age\":101," +
                "\"isRegist\":false}]";
        List<User> list = YamlUtils.yamlToList(yaml, User.class);
        System.out.println(list.get(0).getAge());
        System.out.println(YamlUtils.yamlToList(yaml, User.class));
    }

    @Test
    public void test_yamlToObj_javaType() throws IOException {
        String yaml = "[{\"name\":\"张三\",\"age\":10,\"isRegist\":true},{\"name\":\"上三\",\"age\":101," +
                "\"isRegist\":false}]";
        JavaType javaType = YamlUtils.getInstance().getTypeFactory().constructParametricType(List.class, User.class);
        List<User> list = YamlUtils.yamlToObj(yaml, javaType);
        System.out.println(list.get(0).getAge());
        System.out.println((List) YamlUtils.yamlToObj(yaml, javaType));
    }

    @Test
    public void test_yamlToObj_K_V() throws IOException {

        String yaml = "{\"key\":{\"name\":\"张三\",\"age\":10,\"isRegist\":true}}";
        Map<String, User> map = YamlUtils.yamlToObj(yaml, Map.class, String.class, User.class);
        System.out.println(map);
        System.out.println(map.get("key").getAge());

        yaml = "[{\"name\":\"张三\",\"age\":10,\"isRegist\":true},{\"name\":\"上三\",\"age\":101," +
                "\"isRegist\":false}]";
        List<User> result = YamlUtils.yamlToObj(yaml, List.class, User.class);
        System.out.println(result);
        System.out.println(result.get(1).getName());
    }

    @Getter
    @Setter
    @ToString
    class Tmp {
        private String test;
        private List<User> list;
        private User user;
    }

}
