package com.wangdong.topnews.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wangdong.topnews.Bean.NewsInfo;
import com.wangdong.topnews.R;

import org.xutils.x;

import java.util.ArrayList;

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
            ViewHolder viewHolder=null;
            if(convertView==null){
               viewHolder=new ViewHolder();
               convertView=layoutInflater.inflate(R.layout.item_news_listview_1,null);
                viewHolder.ll= (LinearLayout) convertView.findViewById(R.id.ll_listView2);
                viewHolder.rl= (RelativeLayout) convertView.findViewById(R.id.rl_listView1);
                viewHolder.tvAuthor1= (TextView) convertView.findViewById(R.id.author1);
               viewHolder.tvTime1= (TextView) convertView.findViewById(R.id.time1);
               viewHolder.tvTitle1= (TextView) convertView.findViewById(R.id.tv_title1);
                viewHolder.tvAuthor2= (TextView) convertView.findViewById(R.id.author2);
                viewHolder.tvTime2= (TextView) convertView.findViewById(R.id.time2);
                viewHolder.tvTitle2= (TextView) convertView.findViewById(R.id.tv_title2);
               viewHolder.imageView= (ImageView) convertView.findViewById(R.id.picture);
                viewHolder.imageView1= (ImageView) convertView.findViewById(R.id.tv_picture1);
                viewHolder.imageView2= (ImageView) convertView.findViewById(R.id.tv_picture2);
                viewHolder.imageView3= (ImageView) convertView.findViewById(R.id.tv_picture3);
               convertView.setTag(viewHolder);
           }else {
                viewHolder= (ViewHolder) convertView.getTag();
           }
        if(arrayList.get(position).getThumbnail_pic_s02()==null){
            viewHolder.ll.setVisibility(View.GONE);
            viewHolder.rl.setVisibility(View.VISIBLE);
            viewHolder.tvAuthor1.setText(arrayList.get(position).getAuthor_name());
            viewHolder.tvTime1.setText(arrayList.get(position).getDate());
            viewHolder.tvTitle1.setText(arrayList.get(position).getTitle());
            x.image().bind(viewHolder.imageView,arrayList.get(position).getThumbnail_pic_s());
        }else {
            viewHolder.ll.setVisibility(View.VISIBLE);
            viewHolder.rl.setVisibility(View.GONE);
            viewHolder.tvAuthor2.setText(arrayList.get(position).getAuthor_name());
            viewHolder.tvTime2.setText(arrayList.get(position).getDate());
            viewHolder.tvTitle2.setText(arrayList.get(position).getTitle());
            x.image().bind(viewHolder.imageView1,arrayList.get(position).getThumbnail_pic_s());
            x.image().bind(viewHolder.imageView2,arrayList.get(position).getThumbnail_pic_s02());
            x.image().bind(viewHolder.imageView3,arrayList.get(position).getThumbnail_pic_s03());
        }
        return convertView;
    }
    public class ViewHolder{
        private RelativeLayout rl;
        private LinearLayout ll;
        private ImageView imageView;
        private ImageView imageView1;
        private ImageView imageView2;
        private ImageView imageView3;
        private TextView tvTitle1;
        private TextView tvTime1;
        private TextView tvAuthor1;
        private TextView tvTitle2;
        private TextView tvTime2;
        private TextView tvAuthor2;

    }
}
