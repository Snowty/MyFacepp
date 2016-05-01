package com.snowting.myfacepp;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.snowting.myfacepp.R;
import com.snowting.myfacepp.adapter.ListAdapter;
import com.snowting.myfacepp.model.News;
import com.snowting.myfacepp.utills.HttpUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListActivity extends Activity implements OnItemClickListener {
	private ListView lvNews;
	private ListAdapter adapter;
	private List<News> newsList;

	public static final String Get_News_URL = "http://10.162.25.204/NewsDemo/getNewsJSON.php";

	private Handler getNewsHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			String jsonData = (String) msg.obj;
			System.out.println(jsonData);
			try {
				JSONArray jsonArray = new JSONArray(jsonData);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject object = jsonArray.getJSONObject(i);
					String title = object.getString("title");
					String desc = object.getString("desc");
					String time = object.getString("time");
					String content_url = object.getString("content_url");
					String pic_url = object.getString("pic_url");
					//System.out.println("title = "+title);
					//System.out.println("pic_url = "+pic_url);
					newsList.add(new News(title,desc,time,content_url,pic_url));
				}
				adapter.notifyDataSetChanged(); 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
	};
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		lvNews = (ListView) findViewById(R.id.lvNews);
		newsList = new ArrayList<News>();
		adapter = new ListAdapter(this,newsList);

		lvNews.setAdapter(adapter);
		lvNews.setOnItemClickListener(this);
		
		
		HttpUtils.getNewsJson(Get_News_URL, getNewsHandler);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub
		News news = newsList.get(position);
		Intent intent = new Intent(this,BrowseNewsActivity.class);
		intent.putExtra("content_url", news.getContent_url());
		startActivity(intent);
	}

}
