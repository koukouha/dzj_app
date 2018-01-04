package com.demo.dzj.dzj;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hongbo.gao on 2017/12/22.
 */

public class MyGridAdapter extends BaseAdapter {

    private Context context;

    private ArrayList titleList = new ArrayList();

    MyGridAdapter(Context context) throws Exception {
        this.context = context;
    }

    MyGridAdapter(Context context, ArrayList<String> titleList) throws Exception {
        this.context = context;
        this.titleList = titleList;
    }

//    private static String titleList[] = {
//            "大乘般若部", "大乘宝积部", "大乘大集部",
//            "大乘华严部", "大乘涅槃部", "五大部外重译经",
//            "大乘单译经", "小乘阿含部", "小乘单译经",
//            "宋元入藏诸经", "大乘律", "小乘律",
//            "大乘论", "小乘论", "宋元续入藏诸论",
//            "西土圣贤撰集", "此土著述",
//    };

    @Override
    public int getCount() {
        return titleList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FrameLayout categoryView;
        if (convertView == null) {
            categoryView = createCategoryView(position);
        } else {
            categoryView = (FrameLayout)convertView;
        }
        return categoryView;
    }

    private FrameLayout createCategoryView(int position) {

        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setAdjustViewBounds(true);
        imageView.setPadding(8,8,8,8);
        imageView.setImageResource(R.drawable.book);

        TextView textView = new TextView(context);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(60, 50, 20, 50);
        textView.setLayoutParams(lp);
        textView.setEms(1);
//        textView.setTextSize(20);
        textView.setText((String)titleList.get(position));

        frameLayout.addView(imageView);
        frameLayout.addView(textView);

        return frameLayout;
    }
}
