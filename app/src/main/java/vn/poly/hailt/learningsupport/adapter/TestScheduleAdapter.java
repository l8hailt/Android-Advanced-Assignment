package vn.poly.hailt.learningsupport.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;
import java.util.List;

import vn.poly.hailt.learningsupport.R;
import vn.poly.hailt.learningsupport.holder.HolderSchedule;
import vn.poly.hailt.learningsupport.model.Schedule;
import vn.poly.hailt.learningsupport.model.TestSchedule;

public class TestScheduleAdapter extends RecyclerView.Adapter<HolderSchedule> {

    private final Context context;
    private List<TestSchedule> listTestSchedules;

    public TestScheduleAdapter(Context context, List<TestSchedule> listTestSchedules) {
        this.context = context;
        this.listTestSchedules = listTestSchedules;
    }

    @NonNull
    @Override
    public HolderSchedule onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_schedule, parent, false);
        return new HolderSchedule(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderSchedule holder, int position) {
        TestSchedule testSchedule = listTestSchedules.get(position);

        holder.tvDate.setText(new Date(testSchedule.date).toString());

    }

    @Override
    public int getItemCount() {
        if (listTestSchedules == null) return 0;
        return listTestSchedules.size();
    }
}
