package com.softwaresolution.phhikers.Services;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GeoWebViewClient extends WebViewClient {
    String TAG = "GeoWebViewClient";
    private final Context context;

    public GeoWebViewClient(Context context) {
        this.context = context;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        // When user clicks a hyperlink, load in the existing WebView
        if(url.startsWith("tel:")) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(url));
            context.startActivity(intent);
            return true;
        }
        Log.d(TAG,url);
        Log.d(TAG,String.valueOf(url.startsWith("https://maps.app.goo.gl")));
        if(url.startsWith("https://maps.app.goo.gl")){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
            return true;
        }
        view.loadUrl(url);
        return true;
    }
}
