package vn.poly.hailt.learningsupport.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import vn.poly.hailt.learningsupport.R;

public class HolderNews extends RecyclerView.ViewHolder {

    public final ImageView imgThumb;
    public final TextView tvTitle;
    public final TextView tvDescription;
    public final TextView tvPubDate;

    public HolderNews(View convertView) {
        super(convertView);
        imgThumb = convertView.findViewById(R.id.imgThumb);
        tvTitle = convertView.findViewById(R.id.tvTitle);
        tvDescription = convertView.findViewById(R.id.tvDescription);
        tvPubDate = convertView.findViewById(R.id.tvPubDate);
    }
}
