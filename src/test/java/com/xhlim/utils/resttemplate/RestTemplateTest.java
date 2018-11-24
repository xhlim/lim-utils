package com.xhlim.utils.resttemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.xhlim.utils.json.JsonUtils;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author xhlim@outlook.com
 * @date 2018-07-31
 */
public class RestTemplateTest {

    // @Test
    public void test() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://lmk.189.cn:9093/wapclient/neteaseCard.do";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", "citrix_ns_id_.189.cn_%2F_wlf=TlNDX3h1LTIyMi42OC4xODUuMjI5?SFyPH7LPjeXzuvkfet/gspvs/akA" +
                "&; citrix_ns_id=tmg0cFvPMhNSQB4ETGhihrxhyIcA030; SESSION=a18e723c-28ac-448c-bd90-9384ee342604; " +
                "_gscu_1708861450=33032410wc66eh31; _gscbrs_1708861450=1; svid=E165E4D24EE9E2F5; " +
                "s_fid=231B8DFE6A49FF6A-10C82C6916112B7E; lvid=7fcfc53c483a36713852691496fd2af8; nvid=1; s_cc=true; " +
                "loginStatus=non-logined; trkId=68C57F16-4A8D-43A5-BB2F-BA68F92A9E2F; " +
                "trkHmClickCoords=830%2C188%2C852; aactgsh111220=\"xhlim@outlook.com\"; " +
                "userId=1%7C20150000000023625060; isLogin=logined; " +
                ".ybtj.189.cn=EB6F9E4D89CA471BF9D224BD18B483778028508AEFF213B4; " +
                "_gscs_1708861450=33032410ihxqsp31|pv:8");

        Param param = new Param();
        Map<String, String> headerInfo = new HashMap<>();
        headerInfo.put("functionCode","queryKingCardNumber");
        Map<String, Object> requestContent = new HashMap<>();
        requestContent.put("shop","");
        requestContent.put("sessionid","1494e0387a9443aaa44d35ae3bdeec7f");
        requestContent.put("salesprodid","000000005B4075DBEDFF796BE053AA1410ACE577");
        requestContent.put("contnumber","");
        requestContent.put("pageindex",2);
        requestContent.put("pagesize",20);
        requestContent.put("provincecode","600102");
        requestContent.put("areacode","8310100");
        requestContent.put("islast","1");
        param.setHeaderInfo(headerInfo);
        param.setRequestContent(requestContent);

        FileOutputStream wf = new FileOutputStream("/Users/xhlim/IdeaProjects/xhlim/lim-utils/src/test/java/com/xhlim/utils/resttemplate/a.txt",true);

        for (int i = 0; i < 2 ; i ++ ){
            requestContent.put("pageindex", i);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(JsonUtils.objToJson(param), headers);
            ResponseEntity<JsonNode> exchange = restTemplate.exchange(url, HttpMethod.POST, entity, JsonNode.class);
            System.out.println(exchange.getBody());
            Iterator<JsonNode> iterator = exchange.getBody().get("responseContent").get("dataList").iterator();

            while (iterator.hasNext()) {
                JsonNode next = iterator.next();
                String phoneNumber = next.get("phoneNumber").asText() + "\n";
                System.out.println(phoneNumber);
                byte[] bytes = phoneNumber.getBytes();
                wf.write(bytes);
            }
        }
        wf.close();

    }

}
