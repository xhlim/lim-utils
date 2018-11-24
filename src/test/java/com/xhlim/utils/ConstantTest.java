package com.xhlim.utils;

import com.xhlim.utils.json.JsonUtils;
import com.xhlim.utils.resource.Product;
import com.xhlim.utils.yaml.YamlUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xhlim@outlook.com
 * @date 2018-10-22
 */
public class ConstantTest {

    @Test
    public void test() throws IOException {
        Product product = new Product();
        product.setId(1L).setName("paas").setTitle("PAAS平台").setDescription("开发环境");
        System.out.println(JsonUtils.objToJson(product));
        Product.Project project = new Product.Project();
        project.setId(1L).setProductId(product.getId()).setName("container").setTitle("容器引擎").setDescription("开发环境");
        System.out.println(JsonUtils.objToJson(project));
        Product.App app = new Product.App();
        app.setAppId("workload-container-paas").setProductId(product.getId()).setProductName(product.getName()).setProjectId(project.getId()).setProjectName(project.getName()).setName("workload").setTitle("应用服务").setDescription("开发环境");
        System.out.println(JsonUtils.objToJson(app));
        Product.Resource resource = new Product.Resource();
        String essentialValue = "{\"url\": \"jdbc:mysql://101.89.114.124:15516/ce_manage?characterEncoding=utf8&useSSL=false\", \"username\": \"admin\", \"password\": \"password\" }";
        String unessentialValue = "{\"max-idle\": 10, \"driver-class-name\": \"com.mysql.jdbc.Driver\"}";
        resource.setResId("mysql-datasource").setProductId(product.getId()).setProductName(product.getName()).setProjectId(project.getId()).setProjectName(project.getName()).setAppId(app.getAppId()).setAppName(app.getName()).setName("mysql").setTitle("mysql数据库").setDescription("开发环境").setValue(essentialValue);
        System.out.println(JsonUtils.objToJson(resource));
        System.out.println(resource.getYamlValue());
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("ip", "10.4.231.57");
            put("port", 3306);
        }};
        System.out.println(YamlUtils.objToYaml(map));


    }
}
