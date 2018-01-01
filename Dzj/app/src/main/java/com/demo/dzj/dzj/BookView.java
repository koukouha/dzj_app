package com.demo.dzj.dzj;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by hongb on 2018/1/1.
 */

public class BookView extends AppCompatActivity {

    private static String bookContent = "观自在菩萨，行深般若波罗蜜多时，照见五蕴皆空，度一切苦厄。" +
            "舍利子，色不异空，空不异色，色即是空，空即是色，受想行识亦复如是。" +
            "舍利子，是诸法空相，不生不灭，不垢不净，不增不减。是故空中无色，无受想行识，" +
            "无眼耳鼻舌身意，无色声香味触法，无眼界乃至无意识界，无无明亦无无明尽，乃至无老死，亦无老死尽，无苦集灭道，无智亦无得。" +
            "以无所得故，菩提萨埵。依般若波罗蜜多故，心无挂碍；无挂碍故，无有恐怖，远离颠倒梦想，究竟涅槃。" +
            "三世诸佛，依般若波罗蜜多故，得阿耨多罗三藐三菩提。" +
            "故知般若波罗蜜多，是大神咒，是大明咒，是无上咒，是无等等咒，能除一切苦，真实不虚。" +
            "故说般若波罗蜜多咒，即说咒曰：揭谛揭谛　波罗揭谛　波罗僧揭谛　菩提萨婆诃。";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.TOP);

        TextView bookView = new TextView(this);
        bookView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        bookView.setBackgroundColor(Color.GRAY);
        bookView.setTextColor(Color.BLACK);
        bookView.setTextSize(20);
        bookView.setText(bookContent);

        linearLayout.addView(bookView);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(linearLayout);
    }
}
