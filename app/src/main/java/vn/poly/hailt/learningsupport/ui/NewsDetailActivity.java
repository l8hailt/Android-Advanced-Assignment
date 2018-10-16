package vn.poly.hailt.learningsupport.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookActivity;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import vn.poly.hailt.learningsupport.R;

public class NewsDetailActivity extends AppCompatActivity {

    private WebView wvNews;
    private ShareDialog shareDialog;
    private ShareLinkContent shareLinkContent;
    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        initViews();

        Intent intent = getIntent();
        link = intent.getStringExtra("link");
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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        wvNews = findViewById(R.id.wvNews);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemShare:
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                if (accessToken != null) {
                    shareDialog = new ShareDialog(NewsDetailActivity.this);
                    if (ShareDialog.canShow(ShareLinkContent.class)) {
                        shareLinkContent = new ShareLinkContent.Builder().setContentUrl(Uri.parse(link)).build();
                        shareDialog.show(shareLinkContent);
                    }
                } else {
                    Toast.makeText(this, R.string.not_logged_in, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(NewsDetailActivity.this, SocialActivity.class));
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
