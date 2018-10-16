package vn.poly.hailt.learningsupport.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import vn.poly.hailt.learningsupport.R;
import vn.poly.hailt.learningsupport.adapter.ViewPagerAdapter;
import vn.poly.hailt.learningsupport.database.dao.SubjectDAO;

public class ScheduleActivity extends AppCompatActivity {

    private String subjectID;
    private SubjectDAO subjectDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        subjectDAO = new SubjectDAO(this);

        subjectID = getIntent().getStringExtra("subjectID");
        getSupportActionBar().setTitle(getSubjectID() + " - " + subjectDAO.getSubject(subjectID).subjectName);
    }

    public String getSubjectID() {
        return subjectID;
    }

}
