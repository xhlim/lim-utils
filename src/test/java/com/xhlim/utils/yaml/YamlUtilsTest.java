package com.xhlim.utils.yaml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xhlim.utils.entity.User;
import com.xhlim.utils.json.JsonUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author xhlim@outlook.com
 * @create 2018-06-08
 */
public class YamlUtilsTest {

    @Test
    public void test() throws JsonProcessingException, FileNotFoundException {
        String yml = "list:\n" +
                "- &id001\n" +
                "  age: 123\n" +
                "  isRegist: false\n" +
                "  name: 暂时\n" +
                "- age: 12322\n" +
                "  isRegist: true\n" +
                "  name: 经济法\n" +
                "test: 测试1是否，ss\n" +
                "user: *id001";


        Yaml yaml = new Yaml();

        User user = new User();
        user.setName("暂时");
        user.setAge(123);
        user.setIsRegist(false);
        User user1 = new User();
        user1.setName("经济法");
        user1.setAge(12322);
        user1.setIsRegist(true);

        Tmp tmp = new Tmp();
        tmp.setTest("测试1是否，ss");
        tmp.setUser(user);
        tmp.setList(Arrays.asList(user, user1));


        String str = yaml.dumpAsMap(tmp);
        System.out.println(str);

        Map load = yaml.loadAs(str, Map.class);
        System.out.println(load);

        System.out.println(JsonUtils.objToJson(load));


        //    ===================
        File file = new File("/Users/xhlim/IdeaProjects/xhlim/lim-utils/src/test/resources/applic.yaml");
        FileInputStream stream = new FileInputStream(file);
        Object load1 = yaml.loadAll(stream);
        Iterable<Object> objects = yaml.loadAll(stream);
        System.out.println("===========");
        for (Object object : objects) {
            if (object instanceof Map) {
                System.out.println(object);
            }
        }
        System.out.println(load1);
        System.out.println(JsonUtils.objToJson(load1));
        System.out.println("=====================");
        List<User> users = Arrays.asList(user, user1);
        System.out.println(yaml.dumpAll(users.iterator()));
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
