package vn.poly.hailt.learningsupport.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import vn.poly.hailt.learningsupport.R;
import vn.poly.hailt.learningsupport.RecyclerItemClickListener;
import vn.poly.hailt.learningsupport.adapter.SubjectAdapter;
import vn.poly.hailt.learningsupport.database.dao.SubjectDAO;
import vn.poly.hailt.learningsupport.model.Subject;

public class CourseActivity extends AppCompatActivity {

    private RecyclerView lvSubject;
    private SubjectDAO subjectDAO;
    private SubjectAdapter subjectAdapter;
    private List<Subject> listSubjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        initViews();

        subjectDAO = new SubjectDAO(this);

        setRecyclerView();

    }

    private void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_course);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lvSubject = findViewById(R.id.lvSubject);
    }

    private void setRecyclerView() {
        listSubjects = subjectDAO.getAllSubject();
        subjectAdapter = new SubjectAdapter(this, listSubjects);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        lvSubject.setLayoutManager(manager);
        lvSubject.setAdapter(subjectAdapter);

        lvSubject.addOnItemTouchListener(new RecyclerItemClickListener(this, lvSubject,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(CourseActivity.this, ScheduleActivity.class);
                        intent.putExtra("subjectID", listSubjects.get(position).subjectID);
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        showConfirmDeleteBill(position);
                    }
                }));
    }

    private void showConfirmDeleteBill(final int position) {
        String subjectID = listSubjects.get(position).subjectID;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.action_delete) + " " + subjectID);
        builder.setMessage(getString(R.string.message_confirm_delete_subject));

        builder.setNegativeButton(getString(R.string.action_no), null);
        builder.setPositiveButton(getString(R.string.action_yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                subjectDAO.deleteSubject(listSubjects.get(position));
                listSubjects.remove(position);
                subjectAdapter.notifyDataSetChanged();
                Toast.makeText(CourseActivity.this, R.string.toast_deleted_successfully, Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    private void showRegisterDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        final String[] subjects = {
                "MOB1032 - Lập trình Android cơ bản",
                "MOB202 - Thiết kế giao diện trên Android",
                "MOB201 - Lập trình Android nâng cao",
                "MOB204 - Dự án mẫu (ngành Mobile)",
                "PRO112 - Dự án 1 - Lập trình Mobile"};
        final boolean[] checkedSubject = {false, false, false, false, false};
        builder.setTitle(R.string.action_register);
        builder.setMultiChoiceItems(subjects, checkedSubject, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            }
        });
        builder.setPositiveButton(R.string.action_register, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addSubject(checkedSubject);
            }
        });
        builder.show();
    }

    private void addSubject(boolean[] checkedSubject) {
        boolean added = false;

        if (checkedSubject[0]) {
            if (checkSubject("MOB1032") > -1) {
                Subject subject = new Subject("MOB1032", "Lập trình Android cơ bản", "minhtd");
                subjectDAO.insertSubject(subject);
                listSubjects.add(subject);
                subjectAdapter.notifyDataSetChanged();
                added = true;
            }
        }
        if (checkedSubject[1]) {
            if (checkSubject("MOB202") > -1) {
                Subject subject = new Subject("MOB202", "Thiết kế giao diện trên Android", "sonnv28");
                subjectDAO.insertSubject(subject);
                listSubjects.add(subject);
                subjectAdapter.notifyDataSetChanged();
                added = true;
            }
        }
        if (checkedSubject[2]) {
            if (checkSubject("MOB201") > -1) {
                Subject subject = new Subject("MOB201", "Lập trình Android nâng cao", "huynh2");
                subjectDAO.insertSubject(subject);
                listSubjects.add(subject);
                subjectAdapter.notifyDataSetChanged();
                added = true;
            }
        }
        if (checkedSubject[3]) {
            if (checkSubject("MOB204") > -1) {
                Subject subject = new Subject("MOB204", "Dự án mẫu (ngành Mobile)", "huynh2");
                subjectDAO.insertSubject(subject);
                listSubjects.add(subject);
                subjectAdapter.notifyDataSetChanged();
                added = true;
            }
        }
        if (checkedSubject[4]) {
            if (checkSubject("PRO112") > -1) {
                Subject subject = new Subject("PRO112", "Dự án 1 - Lập trình Mobile", "huynh2");
                subjectDAO.insertSubject(subject);
                listSubjects.add(subject);
                subjectAdapter.notifyDataSetChanged();
                added = true;
            }
        }

        if (added) {
            Toast.makeText(CourseActivity.this, R.string.register_successfully, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(CourseActivity.this, R.string.subject_registered, Toast.LENGTH_SHORT).show();
        }
    }

    private int checkSubject(String subjectID) {
        int result = -1;
        if (!subjectDAO.checkSubject(subjectID)) {
            result = 0;
        }
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemRegister:
                showRegisterDialog();
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
