package com.demo.dzj.dzj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.TOP);
        linearLayout.addView(createGridView());
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(linearLayout);
    }

    private GridView createGridView() {
        GridView gridView = new GridView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        gridView.setLayoutParams(params);
        gridView.setNumColumns(3);
        gridView.setVerticalSpacing(10);
        gridView.setHorizontalSpacing(10);
        gridView.setStretchMode(gridView.STRETCH_COLUMN_WIDTH);
        gridView.setGravity(Gravity.CENTER);
        gridView.setAdapter(new MyGridAdapter(this));
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

}
