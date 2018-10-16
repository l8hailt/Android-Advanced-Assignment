package vn.poly.hailt.learningsupport.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Objects;

import vn.poly.hailt.learningsupport.R;
import vn.poly.hailt.learningsupport.adapter.ScheduleAdapter;
import vn.poly.hailt.learningsupport.database.dao.ScheduleDAO;
import vn.poly.hailt.learningsupport.model.Schedule;
import vn.poly.hailt.learningsupport.ui.ScheduleActivity;

public class ScheduleFragment extends Fragment {

    private View view;
    private RecyclerView lvSchedule;
    private ScheduleDAO scheduleDAO;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_schedule, container, false);

        initViews();

        scheduleDAO = new ScheduleDAO(getContext());

//        sampleData();

        String subjectID = ((ScheduleActivity) Objects.requireNonNull(getActivity())).getSubjectID();

        setRecyclerView(subjectID);

        return view;
    }

    private void setRecyclerView(String subjectID) {
        List<Schedule> listSchedules = scheduleDAO.getAllSchedule(subjectID);
        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(getContext(), listSchedules);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        lvSchedule.setLayoutManager(manager);
        lvSchedule.setAdapter(scheduleAdapter);
    }

    private void initViews() {
        lvSchedule = view.findViewById(R.id.lvSchedule);
    }

    private void sampleData() {
        scheduleDAO.insertSchedule(new Schedule("MOB1032", 1544598900));
        scheduleDAO.insertSchedule(new Schedule("MOB201", 1544598900));
        scheduleDAO.insertSchedule(new Schedule("MOB202", 1544598900));
        scheduleDAO.insertSchedule(new Schedule("MOB204", 1544598900));
        scheduleDAO.insertSchedule(new Schedule("PRO112", 1544598900));


        scheduleDAO.insertSchedule(new Schedule("MOB1032", 1544771700));
        scheduleDAO.insertSchedule(new Schedule("MOB201", 1544771700));
        scheduleDAO.insertSchedule(new Schedule("MOB202", 1544771700));
        scheduleDAO.insertSchedule(new Schedule("MOB204", 1544771700));
        scheduleDAO.insertSchedule(new Schedule("PRO112", 1544771700));


        scheduleDAO.insertSchedule(new Schedule("MOB1032", 1544944500));
        scheduleDAO.insertSchedule(new Schedule("MOB201", 1544944500));
        scheduleDAO.insertSchedule(new Schedule("MOB202", 1544944500));
        scheduleDAO.insertSchedule(new Schedule("MOB204", 1544944500));
        scheduleDAO.insertSchedule(new Schedule("PRO112", 1544944500));
    }

}
