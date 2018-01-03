package com.demo.dzj.dzj;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.demo.dzj.dzj.utils.HttpGetCategory;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {

    private ArrayList<HashMap<String, Object>> jsonElementList;

    private ArrayList<String> titleList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (titleList.isEmpty()) {
            new Thread(networkTask).start();
        }

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.TOP);
        try {
            linearLayout.addView(createGridView(titleList));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setContentView(linearLayout);
    }

    private GridView createGridView(ArrayList<String> titleList) throws Exception {
        GridView gridView = new GridView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        gridView.setLayoutParams(params);
        gridView.setNumColumns(3);
        gridView.setVerticalSpacing(10);
        gridView.setHorizontalSpacing(10);
        gridView.setStretchMode(gridView.STRETCH_COLUMN_WIDTH);
        gridView.setGravity(Gravity.CENTER);
        gridView.setAdapter(new MyGridAdapter(this, titleList));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "book"+position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, TitleListView.class);
                startActivity(intent);
            }
        });
        return gridView;
    }


    private void init(String response) throws Exception {
        if (response != null) {
            jsonElementList = HttpGetCategory.Analysis(response);
            for (HashMap<String, Object> title:jsonElementList) {
                titleList.add((String) title.get("dzj_category_text"));
            }
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "请求结果为-->" + val);
            // TODO
            try {
                init(val);
                onCreate(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    Runnable networkTask = new Runnable() {
        @Override
        public void run() {
            // TODO
            String result = null;

            try {
//                Thread.sleep(10000);
                result = HttpGetCategory.getJson("http://10.126.240.51:8080/dzj_api/category/all");
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
