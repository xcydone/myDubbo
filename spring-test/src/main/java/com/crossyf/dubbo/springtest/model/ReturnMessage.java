package com.crossyf.dubbo.springtest.model;

import com.alibaba.fastjson.JSONObject;
import com.crossyf.dubbo.springtest.constant.DemoConstants;

/**
 * 返回信息
 *
 * @author dukunbiao(null)  2018-09-23
 * https://github.com/dkbnull/SpringBootDemo
 */
public class ReturnMessage {

    private ReturnMessage() {
    }

    public static JSONObject success() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(DemoConstants.CODE, "1");
        jsonObject.put(DemoConstants.MESSAGE, "SUCCESS");

        return jsonObject;
    }

    public static JSONObject createReturnMessage(String code, String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(DemoConstants.CODE, code);
        jsonObject.put(DemoConstants.MESSAGE, message);

        return jsonObject;
    }

    public static JSONObject createReturnMessage(String code, String message, Object data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(DemoConstants.CODE, code);
        jsonObject.put(DemoConstants.MESSAGE, message);
        jsonObject.put(DemoConstants.DATA, data);

        return jsonObject;
    }
}
