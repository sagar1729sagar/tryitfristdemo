package demo.tryitfirst.ssapps.com.tryitfirst_demo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


import com.daimajia.numberprogressbar.NumberProgressBar;
import com.onesignal.OneSignal;
import com.thefinestartist.finestwebview.FinestWebView;

import im.delight.android.webview.AdvancedWebView;

public class MainActivity extends AppCompatActivity implements AdvancedWebView.Listener{

    private AdvancedWebView webView;
   // private ProgressBar pb;
    private NumberProgressBar npb;
    private static String url = "https://tryitfirst.in/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FinestWebView.Builder webviewBuilder = new FinestWebView.Builder(this);
        //String url = "https://tryitfirst.in/";
//
//        webviewBuilder.show(url);

        webView = (AdvancedWebView) findViewById(R.id.webview);
        npb = (NumberProgressBar)findViewById(R.id.number_progress_bar);
       // pb = (ProgressBar) findViewById(R.id.progressBar);
        //pb.setVisibility(View.GONE);
        //webView.setListener(this,this);
        //webView.setWebViewClient(new myWebClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //super.onProgressChanged(view, newProgress);
                Log.v("Back","On progress changed");
//                pb.setVisibility(View.VISIBLE);
//                pb.setProgress(newProgress);
                if (npb.getVisibility() == View.GONE){
                    npb.setVisibility(View.VISIBLE);
                }
                npb.setProgress(newProgress);

                if (newProgress == 100){
                    npb.setVisibility(View.GONE);
                }

            }
        });
        webView.loadUrl(url);

// OneSignal Initialization

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();


    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {

    }

    @Override
    public void onPageFinished(String url) {

    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {

    }


    public class myWebClient extends WebViewClient{

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
           // pb.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return  true;
            //return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            //pb.setVisibility(View.GONE);
        }
    }


    @Override
    public void onBackPressed() {

        Log.v("Back","Back pressed");
        if (webView.canGoBack()){
            Log.v("Back","Can go back");
            webView.goBack();
        } else {
            Log.v("Back","Cannot go back");
            super.onBackPressed();
        }
    }
}
