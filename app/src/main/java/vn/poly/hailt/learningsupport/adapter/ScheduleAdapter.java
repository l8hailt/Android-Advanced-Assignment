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

public class ScheduleAdapter extends RecyclerView.Adapter<HolderSchedule> {

    private final Context context;
    private List<Schedule> listSchedules;

    public ScheduleAdapter(Context context, List<Schedule> listSchedules) {
        this.context = context;
        this.listSchedules = listSchedules;
    }

    @NonNull
    @Override
    public HolderSchedule onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_schedule, parent, false);
        return new HolderSchedule(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderSchedule holder, int position) {
        Schedule schedule = listSchedules.get(position);

        holder.tvDate.setText(new Date(schedule.date).toString());

    }

    @Override
    public int getItemCount() {
        if (listSchedules == null) return 0;
        return listSchedules.size();
    }
}
