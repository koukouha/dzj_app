package com.demo.dzj.dzj;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.demo.dzj.dzj.utils.Constant;
import com.demo.dzj.dzj.utils.HttpCallAPI;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hongbo.gao on 2017/12/22.
 */

public class TitleListView extends AppCompatActivity {

    private int categoryId;

    private List<Integer> bookIdList = new ArrayList<>();

    private List<String> bookTitleList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryId = getIntent().getIntExtra("id", 0);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setId(Constant.TITLE_LIST_RESOURCE_ID);
//        linearLayout.addView(createListView());
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(linearLayout);
        new Thread(getTitleList).start();
    }

    private ListView createListView(List<String> titleList) {
        ListView listView = new ListView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        listView.setLayoutParams(layoutParams);
//        String[] titleList = new String[]{
//                "般若波罗蜜多心经",
//                "般若波罗密金刚经",
//                "大智度论",
//                "瑜伽师地论",
//                "佛母大孔雀明王经",
//                "圆觉经"
//        };
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titleList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TitleListView.this, BookView.class);
                intent.putExtra("bookId", bookIdList.get(i));
                startActivity(intent);
            }
        });

        return listView;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "请求结果为-->" + val);
            try {
                ArrayList<HashMap<String, Object>> jsonElementList = HttpCallAPI.AnalysisTitleList(val);
                for (HashMap<String, Object> element:jsonElementList) {
                    bookIdList.add(Integer.parseInt((String) element.get("dzj_id")));
                    bookTitleList.add(((String) element.get("dzj_title_text"))
                            .replace(".txt", "").replaceAll(".*部～",""));
                }
                LinearLayout linearLayout = (LinearLayout) findViewById(Constant.TITLE_LIST_RESOURCE_ID);
                linearLayout.addView(createListView(bookTitleList));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    Runnable getTitleList = new Runnable() {
        @Override
        public void run() {
            // TODO
            String result = null;
            try {
//                Thread.sleep(10000);
                result = HttpCallAPI.getJson(Constant.BASE_URL + "title/" + categoryId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value", result);
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };
}
