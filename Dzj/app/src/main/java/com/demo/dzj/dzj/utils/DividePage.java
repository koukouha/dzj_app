package com.demo.dzj.dzj.utils;

import android.graphics.Rect;
import android.widget.TextView;

/**
 * Created by hongbo.gao on 2018/1/5.
 */

public class DividePage {

    public int[] getPage( TextView textView){
        int count=textView.getLineCount();
//        textView.setText(mContent);
        int pCount=getPageLineCount(textView);
        int pageNum=count/pCount;
        int page[]=new int[pageNum];
        for(int i=0;i<pageNum;i++){
            page[i]=textView.getLayout().getLineEnd((i+1)*pCount-1);
        }
        return page;
    }

    private int getPageLineCount(TextView view){
      /*
      * The first row's height is different from other row.
       */
        int h=view.getBottom()-view.getTop()-view.getPaddingTop();
        int firstH=getLineHeight(0,view);
        int otherH=getLineHeight(1,view);
        return (h-firstH)/otherH + 1 ;
    }

    private int getLineHeight(int line,TextView view){
        Rect rect=new Rect();
        view.getLineBounds(line,rect);
        return rect.bottom-rect.top;
    }
}
