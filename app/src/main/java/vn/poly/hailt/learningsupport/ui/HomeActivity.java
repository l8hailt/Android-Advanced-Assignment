package vn.poly.hailt.learningsupport.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import vn.poly.hailt.learningsupport.R;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private LinearLayout blockCourse;
    private LinearLayout blockMap;
    private LinearLayout blockNews;
    private LinearLayout blockSocial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
        initActions();


    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.title_home);

        blockCourse = findViewById(R.id.blockCourse);
        blockMap = findViewById(R.id.blockMap);
        blockNews = findViewById(R.id.blockNews);
        blockSocial = findViewById(R.id.blockSocial);
    }

    private void initActions() {
        blockMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MapsActivity.class));
            }
        });
        blockNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, NewsActivity.class));
            }
        });
    }
}
