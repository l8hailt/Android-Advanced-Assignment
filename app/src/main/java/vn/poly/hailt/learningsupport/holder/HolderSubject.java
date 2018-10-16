package vn.poly.hailt.learningsupport.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import vn.poly.hailt.learningsupport.R;

public class HolderSubject extends RecyclerView.ViewHolder {

    public final TextView tvSubjectID;
    public final TextView tvSubjectName;
    public final TextView tvTeacher;


    public HolderSubject(View convertView) {
        super(convertView);

        tvSubjectID = convertView.findViewById(R.id.tvSubjectID);
        tvSubjectName = convertView.findViewById(R.id.tvSubjectName);
        tvTeacher = convertView.findViewById(R.id.tvTeacher);

    }
}
