package com.banglore.computer.bce;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by a on 5/3/2017.
 */

public class webclient extends WebViewClient {


    Context context;

    public webclient(Context context)
    {
        this.context=context;
    }

    ProgressDialog dialog=new ProgressDialog(this.context,R.style.mydialog);

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        dialog.dismiss();
    }


}
