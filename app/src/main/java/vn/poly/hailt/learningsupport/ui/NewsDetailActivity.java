package vn.poly.hailt.learningsupport.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import vn.poly.hailt.learningsupport.R;

public class NewsDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private WebView wvNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        initViews();

        Intent intent = getIntent();
        String link = intent.getStringExtra("link");
        String titleNews = intent.getStringExtra("titleNews");
        Log.e("TITLE", titleNews);
        getSupportActionBar().setTitle(titleNews);

        wvNews.getSettings().setJavaScriptEnabled(true);
        wvNews.getSettings().setLoadsImagesAutomatically(true);
        wvNews.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wvNews.loadUrl(link);
        wvNews.setWebViewClient(new WebViewClient());

    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        wvNews = findViewById(R.id.wvNews);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
