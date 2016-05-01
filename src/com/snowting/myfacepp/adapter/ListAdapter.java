package com.snowting.myfacepp.adapter;

import java.util.List;

import com.snowting.myfacepp.R;
import com.snowting.myfacepp.model.News;
import com.snowting.myfacepp.utills.HttpUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

	private Context context; // 上下文对象
	private List<News> newsList;

	public ListAdapter(Context context,List<News> newsList) {
		this.context = context;
		this.newsList = newsList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return newsList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return newsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.list_adapter, null);
		}
		TextView tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
		TextView tvDesc = (TextView) convertView.findViewById(R.id.tvDesc);
		TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);
		ImageView ivPic = (ImageView) convertView.findViewById(R.id.ivPic);
		
		News news = newsList.get(position);
		tvTitle.setText(news.getTitle());
		tvDesc.setText(news.getDesc());
		tvTime.setText(news.getTime());
		
		String pic_url = news.getPic_url();
		HttpUtils.setPicBitmap(ivPic, pic_url);
		
		return convertView;
	}

}
