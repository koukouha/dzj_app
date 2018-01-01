package com.demo.dzj.dzj;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by hongbo.gao on 2017/12/22.
 */

public class TitleListView extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.addView(createListView());
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(linearLayout);
    }

    private ListView createListView() {
        ListView listView = new ListView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        listView.setLayoutParams(layoutParams);
        String[] titleList = new String[]{
                "般若波罗蜜多心经",
                "般若波罗密金刚经",
                "大智度论",
                "瑜伽师地论",
                "佛母大孔雀明王经",
                "圆觉经"
        };
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,     titleList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TitleListView.this, BookView.class);
                startActivity(intent);
            }
        });

        return listView;
    }
}
