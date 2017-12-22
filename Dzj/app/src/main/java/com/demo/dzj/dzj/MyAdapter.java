package com.demo.dzj.dzj;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by hongbo.gao on 2017/12/22.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;

    private Integer[] bookItems = {
            R.drawable.book, R.drawable.book, R.drawable.book,
            R.drawable.book, R.drawable.book, R.drawable.book,
            R.drawable.book, R.drawable.book, R.drawable.book,
            R.drawable.book, R.drawable.book, R.drawable.book,
    };

    MyAdapter(Context context){
        this.context = context;
    }


    @Override
    public int getCount() {
        return bookItems.length;
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
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setAdjustViewBounds(true);
            imageView.setPadding(8,8,8,8);
        } else {
            imageView = (ImageView)convertView;
        }
//        imageView.setBackgroundColor(Color.BLUE);
        imageView.setImageResource(R.drawable.book);
        return imageView;
    }
}
