package com.wangdong.topnews.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wangdong.topnews.Bean.NewsInfo;

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
        return null;
    }
    public class ViewHolder{

    }
}
