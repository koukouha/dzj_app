package com.demo.dzj.dzj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
        addView(linearLayout);
        setContentView(linearLayout);
//        setContentView(R.layout.activity_main);
    }

    private void addView(LinearLayout layout) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.book);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 10, 10, 10);
        imageView.setLayoutParams(params);
        layout.addView(imageView);
    }

}
