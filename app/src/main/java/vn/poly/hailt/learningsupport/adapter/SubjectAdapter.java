package vn.poly.hailt.learningsupport.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vn.poly.hailt.learningsupport.R;
import vn.poly.hailt.learningsupport.holder.HolderSubject;
import vn.poly.hailt.learningsupport.model.Subject;

public class SubjectAdapter extends RecyclerView.Adapter<HolderSubject> {

    private final Context context;
    private List<Subject> listSubjects;

    public SubjectAdapter(Context context, List<Subject> listSubjects) {
        this.context = context;
        this.listSubjects = listSubjects;
    }

    @NonNull
    @Override
    public HolderSubject onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_subject, parent, false);
        return new HolderSubject(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderSubject holder, int position) {
        Subject subject = listSubjects.get(position);

        holder.tvSubjectID.setText(subject.subjectID);
        holder.tvSubjectName.setText(subject.subjectName);
        holder.tvTeacher.setText(subject.teacher);

    }

    @Override
    public int getItemCount() {
        if (listSubjects == null) return 0;
        return listSubjects.size();
    }
}
