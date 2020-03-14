package com.example.springbootstudy.self;

import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OrderEventService extends SelfBaseService {

    private OkHttpClient okHttpClient = new OkHttpClient();

    private String baseUrl = "http://localhost:8080/orderInfo";

    @Override
    public String equalsCheck(String param) throws Exception {

        JSONObject reqObj = JSONObject.parseObject(param);

        String url = baseUrl + "?orderNo=" + reqObj.getString("orderNo");

        Request request = new Request.Builder().url(url).build();

        StringBuilder checkResult = new StringBuilder();

        try (Response response = okHttpClient.newCall(request).execute()) {

            String res = response.body().string();

            JSONObject resObj = JSONObject.parseObject(res);

            if (reqObj.getLongValue("amount") != resObj.getLongValue("amount")) {

                checkResult.append("金额不匹配");

            }

            if (reqObj.getIntValue("payStatus") != resObj.getIntValue("payStatus")) {

                checkResult.append("状态不匹配");

            }

        }

        return checkResult.toString();
    }
}
