package au.edu.canberra.mobiletechnology_assignment2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebBrowser extends AppCompatActivity
{

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_browser);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.webView = (WebView)findViewById(R.id.webViewID);

        this.webView.getSettings().setJavaScriptEnabled(true);


        this.webView.setWebViewClient(new WebViewClient()
        {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
            {
                Log.println(Log.ERROR, "CustomWebBrowser", "Error code: " + errorCode + " Description: " + description + " Failing URL: " + failingUrl);
            }
        });

    }

    @Override
    public void onResume()
    {
        super.onRestart();
        String url = getIntent().getExtras().getString("gotoWebsite");
        this.webView.loadUrl(url);
    }
}