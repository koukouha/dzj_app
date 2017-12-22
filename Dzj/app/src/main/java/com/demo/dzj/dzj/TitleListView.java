package com.demo.dzj.dzj;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by hongbo.gao on 2017/12/22.
 */

public class TitleListView extends AppCompatActivity {

    private static final int RESOURCE_LinearLayout_ID = 1000;

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
    }

    private ListView createListView() {
        ListView listView = new ListView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        listView.setLayoutParams(layoutParams);
        listView.setId(RESOURCE_LinearLayout_ID);
        String[] titleList = new String[]{
                "111","111","111","111","111","111"
        };
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, RESOURCE_LinearLayout_ID, titleList);
        listView.setAdapter(arrayAdapter);

        return listView;
    }
}
