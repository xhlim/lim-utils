package com.xhlim.utils.yaml;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import com.xhlim.utils.constant.Constant;
import com.xhlim.utils.json.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author xhlim@outlook.com
 * @create 2018-06-08
 */
public class YamlUtilsTest {

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

    @Getter
    @Setter
    @ToString
    class Tmp {
        private String test;
        private List<User> list;
        private User user;
    }

}
