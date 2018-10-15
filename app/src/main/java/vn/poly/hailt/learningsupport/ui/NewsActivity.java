package vn.poly.hailt.learningsupport.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import vn.poly.hailt.learningsupport.R;
import vn.poly.hailt.learningsupport.adapter.NewsAdapter;
import vn.poly.hailt.learningsupport.model.News;

public class NewsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView lvNews;
    private NewsAdapter newsAdapter;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        initViews();

        LoadRSSFromInternetTask loadRSSFromInternetTask = new LoadRSSFromInternetTask(this);
        loadRSSFromInternetTask.execute("http://vietnamnet.vn/rss/giao-duc.rss");

    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_news);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lvNews = findViewById(R.id.lvNews);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }


    class LoadRSSFromInternetTask extends AsyncTask<String, Long, List<News>> {

        private Context context;

        public LoadRSSFromInternetTask(Context context) {
            this.context = context;
        }

        @Override
        protected List<News> doInBackground(String... strings) {

            ArrayList<News> arrayListNews = new ArrayList<>();

            try {
                URL url = new URL(strings[0]);

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(url.openConnection().getInputStream(), "UTF-8");

                int eventType = xpp.getEventType();
                String text = "";

                News news = null;

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    String nameTag = xpp.getName();
                    switch (eventType) {
                        case XmlPullParser.START_TAG:

                            Log.e("Name", xpp.getName());
                            if (nameTag.equalsIgnoreCase("item")) {
                                news = new News();
                                Log.e("CREATE", "NEWS");
                            }
                            break;

                        case XmlPullParser.TEXT:
                            text = xpp.getText();
                            break;

                        case XmlPullParser.END_TAG:
                            if (nameTag.equals("item"))
                                arrayListNews.add(news);
                            else if (news != null & nameTag.equalsIgnoreCase("title"))
                                news.title = text.trim();
                            else if (news != null & nameTag.equalsIgnoreCase("description"))
                                news.description = text.trim();
                            else if (news != null & nameTag.equalsIgnoreCase("pubDate"))
                                news.pubDate = text.trim();
                            else if (news != null & nameTag.equalsIgnoreCase("link"))
                                news.link = text.trim();
                            else if (news != null & nameTag.equalsIgnoreCase("image"))
                                news.image = text.trim();

                            Log.e("END_TAG " + nameTag, text + "");
                            break;

                        default:
                            break;

                    }
                    eventType = xpp.next(); //move to next element
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return arrayListNews;
        }

        @Override
        protected void onPostExecute(List<News> news) {
            super.onPostExecute(news);

            newsAdapter = new NewsAdapter(context, news);
            manager = new LinearLayoutManager(context);

            lvNews.setLayoutManager(manager);
            lvNews.setAdapter(newsAdapter);

        }
    }

}
