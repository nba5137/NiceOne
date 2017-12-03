package nba5137.niceone;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class RealMainAct extends AppCompatActivity {

    public android.widget.Button exit;
    public android.widget.ImageButton xv, ph, t8, rt;
    WebView pv;
    ProgressBar progressBar;
    public String url = "www.pornpics.com/";

    public void init(){
        exit = findViewById(R.id.quit);
        xv = findViewById(R.id.xvb);
        ph = findViewById(R.id.phb);
        t8 = findViewById(R.id.t8b);
        rt = findViewById(R.id.rtb);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

        xv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = "m.xvideos.com/";
                pv.loadUrl("https://" + url);
            }
        });

        ph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = "m.pornhub.com/";
                pv.loadUrl("https://" + url);
            }
        });

        t8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = "m.tube8.com/";
                pv.loadUrl("https://" + url);
            }
        });

        rt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = "m.redtube.com/";
                pv.loadUrl("https://" + url);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_main);
        init();
        progressBar = findViewById(R.id.progressBarB);
        progressBar.setMax(100);
        progressBar.setVisibility(View.GONE);

        pv = findViewById(R.id.pvb);


        if (savedInstanceState != null) {
            pv.restoreState(savedInstanceState);
        } else {
            pv.getSettings().setJavaScriptEnabled(true);
            pv.getSettings().setSupportZoom(true);
            pv.getSettings().setBuiltInZoomControls(false);
            pv.getSettings().setLoadWithOverviewMode(true);
            pv.getSettings().setUseWideViewPort(true);
            pv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            pv.setBackgroundColor(Color.BLACK);
            pv.getSettings().getBuiltInZoomControls();

            pv.setWebViewClient(new WebViewClient());
            pv.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int progress) {
                    progressBar.setProgress(progress);
                    if (progress < 100 && progressBar.getVisibility() == ProgressBar.GONE) {
                        progressBar.setVisibility(ProgressBar.VISIBLE);
                    }
                    if (progress == 100) {
                        progressBar.setVisibility(ProgressBar.GONE);
                    }
                }
            });

            pv.loadUrl("https://" + url);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        pv.saveState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.item_back:
                if(pv.canGoBack()){
                    pv.goBack();
                }
                return true;
            case R.id.item_forward:
                if(pv.canGoForward()){
                    pv.goForward();
                }
                return true;
            case R.id.item_home:
                url = "www.pornpics.com/";
                pv.loadUrl("https://" + url);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed(){
        if(pv.canGoBack()){
            pv.goBack();
        }
        // else nothing lol
    }


}
