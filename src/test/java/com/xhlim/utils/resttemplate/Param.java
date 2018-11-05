package com.xhlim.utils.resttemplate;

import java.util.Map;

/**
 * @author xhlim@outlook.com
 * @date 2018-07-31
 */
public class Param {

    private Map<String, String> headerInfo;

    private Map<String, Object> requestContent;

    public Map<String, String> getHeaderInfo() {
        return headerInfo;
    }

    public void setHeaderInfo(Map<String, String> headerInfo) {
        this.headerInfo = headerInfo;
    }

    public Map<String, Object> getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(Map<String, Object> requestContent) {
        this.requestContent = requestContent;
    }
}
