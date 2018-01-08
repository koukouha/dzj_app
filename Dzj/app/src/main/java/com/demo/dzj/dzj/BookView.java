package com.demo.dzj.dzj;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.dzj.dzj.utils.Constant;
import com.demo.dzj.dzj.utils.DividePage;
import com.demo.dzj.dzj.utils.HttpCallAPI;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by hongb on 2018/1/1.
 */

public class BookView extends AppCompatActivity {

    private int bookId;

//    private static String bookContent = "观自在菩萨，行深般若波罗蜜多时，照见五蕴皆空，度一切苦厄。" +
//            "舍利子，色不异空，空不异色，色即是空，空即是色，受想行识亦复如是。" +
//            "舍利子，是诸法空相，不生不灭，不垢不净，不增不减。是故空中无色，无受想行识，" +
//            "无眼耳鼻舌身意，无色声香味触法，无眼界乃至无意识界，无无明亦无无明尽，乃至无老死，亦无老死尽，无苦集灭道，无智亦无得。" +
//            "以无所得故，菩提萨埵。依般若波罗蜜多故，心无挂碍；无挂碍故，无有恐怖，远离颠倒梦想，究竟涅槃。" +
//            "三世诸佛，依般若波罗蜜多故，得阿耨多罗三藐三菩提。" +
//            "故知般若波罗蜜多，是大神咒，是大明咒，是无上咒，是无等等咒，能除一切苦，真实不虚。" +
//            "故说般若波罗蜜多咒，即说咒曰：揭谛揭谛　波罗揭谛　波罗僧揭谛　菩提萨婆诃。";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bookId = getIntent().getIntExtra("bookId", 250);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.TOP);
        linearLayout.setId(Constant.BOOK_LAYOUT_RESOURCE_ID);


        TextView bookView = new TextView(this);
        bookView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        bookView.setBackgroundColor(Color.WHITE);
        bookView.setTextColor(Color.BLACK);
        bookView.setTextSize(Constant.PAGE_VIEW_TEXT_SIZE);
        bookView.setVerticalScrollBarEnabled(true);
        bookView.setSingleLine(false);
        bookView.setMovementMethod(ScrollingMovementMethod.getInstance());
        bookView.setId(Constant.BOOK_VIEW_RESOURCE_ID);
//        bookView.setText(bookContent);
        linearLayout.addView(bookView);

        ViewPager pageView = new ViewPager(this);
        pageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        pageView.setBackgroundColor(Color.WHITE);
        pageView.setId(Constant.PAGE_VIEW_RESOURCE_ID);


        linearLayout.addView(pageView);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(linearLayout);

        new Thread(getBookContent).start();
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "请求结果为-->" + val);
            try {
                LinearLayout linearLayout = (LinearLayout) findViewById(Constant.BOOK_LAYOUT_RESOURCE_ID);
                TextView bookView = (TextView) findViewById(Constant.BOOK_VIEW_RESOURCE_ID);
                String bookContent = HttpCallAPI.AnalysisBook(val);
                bookView.setText(bookContent);
                DividePage divider = new DividePage();
                int[] pages = divider.getPage(bookView);
                Log.i("mylog", "请求结果为-->" + pages);

                ViewPager pageView = (ViewPager) findViewById(Constant.PAGE_VIEW_RESOURCE_ID);
                ContentAdapter contentAdapter = new ContentAdapter(pages, bookContent);
                pageView.setAdapter(contentAdapter);

                linearLayout.removeView(bookView);
                linearLayout.removeView(pageView);
                linearLayout.addView(pageView);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    Runnable getBookContent = new Runnable() {
        @Override
        public void run() {
            // TODO
            String result = null;
            try {
//                Thread.sleep(10000);
                result = HttpCallAPI.getJson(Constant.BASE_URL + "book/" + bookId);
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
