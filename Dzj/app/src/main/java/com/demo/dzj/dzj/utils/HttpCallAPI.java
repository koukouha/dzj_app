package com.demo.dzj.dzj.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hongbo.gao on 2018/1/3.
 */

public class HttpCallAPI {

    public static String getJson(String urlPath) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        URL url = new URL(urlPath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        InputStream inStream = conn.getInputStream();
        while ((len = inStream.read(data)) != -1) {
            outStream.write(data, 0, len);
        }
        inStream.close();
        return new String(outStream.toByteArray());
    }

    public static ArrayList<HashMap<String, Object>> AnalysisCategoryList(String jsonStr)
            throws JSONException {
        /******************* 解析 ***********************/
        JSONArray jsonArray = null;
        // 初始化list数组对象
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        jsonArray = new JSONArray(jsonStr);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            // 初始化map数组对象
            HashMap<String, Object> map = new HashMap<>();
            map.put("dzj_category_id", jsonObject.getString("dzj_category_id"));
            map.put("dzj_category_text", jsonObject.getString("dzj_category_text"));
            list.add(map);
        }
        return list;
    }

    public static ArrayList<HashMap<String, Object>> AnalysisTitleList(String jsonStr)
            throws JSONException {
        /******************* 解析 ***********************/
        JSONArray jsonArray = null;
        // 初始化list数组对象
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        jsonArray = new JSONArray(jsonStr);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            // 初始化map数组对象
            HashMap<String, Object> map = new HashMap<>();
            map.put("dzj_id", jsonObject.getString("dzj_id"));
            map.put("dzj_category_id", jsonObject.getString("dzj_category_id"));
            map.put("dzj_title_text", jsonObject.getString("dzj_title_text"));
            list.add(map);
        }
        return list;
    }

    public static String AnalysisBook(String jsonStr)
            throws JSONException {
        /******************* 解析 ***********************/;
        JSONObject jsonObject = new JSONObject(jsonStr);
        return jsonObject.getString("dzj_text");
    }
}
