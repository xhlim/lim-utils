package com.xhlim.utils.http;

import com.fasterxml.jackson.databind.node.JsonNodeCreator;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xhlim.utils.map.MapUtils;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriTemplate;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xhlim@outlook.com
 * @date 2018-08-02
 */
public class HttpTest {

    @Test
    public void test() throws UnsupportedEncodingException, MalformedURLException {

        String url = "";
        url = "http://{endpoint}/cekube/{version}/{ip}/{port}/{namespace}/deployments";

        Map<String, Object> uriVariables = new HashMap<String, Object>() {{
            put("endpoint", "cekube");
            put("version", "1.7.6");
            put("ip", "192.168.8.112");
            put("port", "8080");
            put("namespace", "lim");
        }};

        URI components = UriComponentsBuilder.fromHttpUrl(url).build(uriVariables);
        System.out.println(components.toASCIIString());
        System.out.println(components.toString());

        url = components.toString();
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        json.put("aaa", "bbb");
        json.put("bbbb", "ccc");


        RequestEntity<String> body = RequestEntity.method(HttpMethod.POST, URI.create(url))
                .header("test", "aaa")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(json.toString());


        System.out.println(body.getUrl());


    }
}
