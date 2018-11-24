package com.xhlim.utils.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xhlim.utils.json.JsonUtils;
import com.xhlim.utils.yaml.YamlUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.IOException;
import java.util.Map;

/**
 * @author xhlim@outlook.com
 * @date 2018-11-06
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Product {

    private Long id;
    private String name;
    private String title;
    private String description;


    @Getter
    @Setter
    @ToString
    @Accessors(chain = true)
    public static class Project {
        private Long id;
        private Long productId;
        private String productName;
        private String name;
        private String title;
        private String description;
    }

    @Getter
    @Setter
    @ToString
    @Accessors(chain = true)
    public static class App {
        private Long id;
        private String appId;
        private Long productId;
        private String productName;
        private Long projectId;
        private String projectName;
        private String name;
        private String title;
        private String description;
    }


    @Getter
    @Setter
    @ToString
    @Accessors(chain = true)
    public static class Resource {
        private Long id;
        private String resId;
        private Long productId;
        private String productName;
        private Long projectId;
        private String projectName;
        private String appId;
        private String appName;
        private String name;
        private String title;
        private String description;
        private String value;
        private String jsonValue;
        private String yamlValue;

        public String getJsonValue() {
            return value;
        }

        public String getYamlValue()  {
            try {
                return YamlUtils.objToYaml(value);
            } catch (JsonProcessingException e) {
                return null;
            }
        }
    }

}
