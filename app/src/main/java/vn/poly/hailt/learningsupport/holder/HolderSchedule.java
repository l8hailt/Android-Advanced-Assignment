package vn.poly.hailt.learningsupport.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import vn.poly.hailt.learningsupport.R;

public class HolderSchedule extends RecyclerView.ViewHolder {

    public final TextView tvDate;

    public HolderSchedule(View itemView) {
        super(itemView);
        tvDate = itemView.findViewById(R.id.tvDate);
    }
}
