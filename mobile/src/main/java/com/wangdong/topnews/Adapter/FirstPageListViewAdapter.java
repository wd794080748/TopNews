package com.wangdong.topnews.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangdong.topnews.Bean.NewsInfo;
import com.wangdong.topnews.R;

import org.xutils.x;

import java.util.ArrayList;

import static com.google.android.gms.wearable.DataMap.TAG;

/**
 * Created by wd794 on 2017/1/8 0008.
 */

public class FirstPageListViewAdapter extends BaseAdapter {
    private ArrayList<NewsInfo.ResultBean.DataBean> arrayList;
    private LayoutInflater layoutInflater;

    public FirstPageListViewAdapter(ArrayList<NewsInfo.ResultBean.DataBean> arrayList, Context context) {
        this.arrayList = arrayList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "getView: "+arrayList.toString());
        if(arrayList.get(position).getThumbnail_pic_s02()==null){
            ViewHolder viewHolder=null;
            if(convertView==null){
               viewHolder=new ViewHolder();
               convertView=layoutInflater.inflate(R.layout.item_news_listview_1,null);
               viewHolder.tvAuthor= (TextView) convertView.findViewById(R.id.author);
               viewHolder.tvTime= (TextView) convertView.findViewById(R.id.time);
               viewHolder.tvTitle= (TextView) convertView.findViewById(R.id.tv_title);
               viewHolder.imageView1= (ImageView) convertView.findViewById(R.id.picture);
               convertView.setTag(viewHolder);
           }else {
                viewHolder= (ViewHolder) convertView.getTag();
           }
           viewHolder.tvTime.setText(arrayList.get(position).getDate()+" ");
           viewHolder.tvTitle.setText(arrayList.get(position).getTitle()+" ");
           x.image().bind(viewHolder.imageView1,arrayList.get(position).getThumbnail_pic_s());
       }
        else {
            ViewHolder viewHolder=null;
            if(convertView==null){
                viewHolder=new ViewHolder();
               convertView=layoutInflater.inflate(R.layout.item_news_listview_2,null);
               viewHolder.tvTime= (TextView) convertView.findViewById(R.id.time);
               viewHolder.tvTitle= (TextView) convertView.findViewById(R.id.tv_title);
               viewHolder.imageView1= (ImageView) convertView.findViewById(R.id.picture);
               viewHolder.imageView2= (ImageView) convertView.findViewById(R.id.tv_picture2);
               viewHolder.imageView3= (ImageView) convertView.findViewById(R.id.tv_picture3);
               convertView.setTag(viewHolder);
               Log.i(TAG, "getView: "+"111"+viewHolder.tvTitle.isClickable());
           }else {
               Log.i(TAG, "getView: "+"222");
               viewHolder= (ViewHolder) convertView.getTag();
           }
           Log.i(TAG, "getView: "+arrayList.get(position).getDate());
           viewHolder.tvTime.setText(arrayList.get(position).getDate()+" ");
           viewHolder.tvTitle.setText(arrayList.get(position).getTitle()+" ");
           x.image().bind(viewHolder.imageView1,arrayList.get(position).getUrl());
           x.image().bind(viewHolder.imageView2,arrayList.get(position).getThumbnail_pic_s02());
           if(arrayList.get(position).getThumbnail_pic_s03()!=null){
               x.image().bind(viewHolder.imageView3,arrayList.get(position).getThumbnail_pic_s03());
           }else {
               viewHolder.imageView3.setVisibility(View.GONE);
           }
       }
        return convertView;
    }
    public class ViewHolder{
        private ImageView imageView1;
        private ImageView imageView2;
        private ImageView imageView3;
        private TextView tvTitle;
        private TextView tvTime;
        private TextView tvAuthor;

    }
}
