package com.mzwy.pinhu;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mzwy.pinhu.interfaces.PermissionInterface;
import com.mzwy.pinhu.utils.PermissionUtils;
import com.mzwy.pinhu.view.ProgressWebView;

/**
 * Created by 张亚楠 on 2017/4/28.
 */

public class HomeWebviewActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_fragment_news);
        initView();
    }

    public ImageView ivBackApproveAddress;
    public ImageView share;
    public static TextView tv_title;
    public static ProgressWebView webView;

    private void initView() {
        webView = (ProgressWebView) findViewById(R.id.WebView);

        tv_title = (TextView) findViewById(R.id.tv_title);
        ivBackApproveAddress = (ImageView) findViewById(R.id.iv_back_approve_address);
        share = (ImageView) findViewById(R.id.share);
        ivBackApproveAddress.setOnClickListener(this);
        share.setOnClickListener(this);
        webSeetingUrl();
    }

    private void webSeetingUrl() {
        webView.loadUrl("http://m.pinhumall.com/pinhu-wechat/index.html", tv_title, ivBackApproveAddress);
    }

    public static void reload() {
        if (webView != null) {
            webView.reload();
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.share://分享
                Toast.makeText(HomeWebviewActivity.this, webView.getUrl(), Toast.LENGTH_LONG).show();
                break;
            case R.id.iv_back_approve_address://展开以及返回
                webView.goback();
                break;
        }

    }

}
