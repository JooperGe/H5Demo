package com.mzwy.pinhu.js;

import android.content.Context;
import android.webkit.JavascriptInterface;

/**
 * Created by zhang on 2016/10/14.
 * 创建接受Html5监听的类
 */

public class JavascriptInte {
    private Context mContext;
    private int type = 1;

    public JavascriptInte(Context context) {
        this.mContext = context;
    }

    @JavascriptInterface
    public void set(){

    }
}