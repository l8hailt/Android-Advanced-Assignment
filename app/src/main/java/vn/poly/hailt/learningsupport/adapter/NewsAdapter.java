package vn.poly.hailt.learningsupport.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.poly.hailt.learningsupport.R;
import vn.poly.hailt.learningsupport.holder.HolderNews;
import vn.poly.hailt.learningsupport.model.News;
import vn.poly.hailt.learningsupport.ui.NewsDetailActivity;

public class NewsAdapter extends RecyclerView.Adapter<HolderNews> {

    private Context context;
    private List<News> listNews;

    public NewsAdapter(Context context, List<News> listNews) {
        this.context = context;
        this.listNews = listNews;
    }

    @NonNull
    @Override
    public HolderNews onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        return new HolderNews(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderNews holder, int position) {
        final News news = listNews.get(position);

        holder.tvTitle.setText(news.title);
        holder.tvDescription.setText(news.description);
        holder.tvPubDate.setText(news.pubDate);

        Glide.with(context).load(news.image).into(holder.imgThumb);

        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsDetailActivity.class);
                intent.putExtra("titleNews", news.title);
                intent.putExtra("link", news.link);
                context.startActivity(intent);
            }
        });
        holder.imgThumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsDetailActivity.class);
                intent.putExtra("titleNews", news.title);
                intent.putExtra("link", news.link);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (listNews == null) return 0;
        return listNews.size();
    }
}
