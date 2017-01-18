package com.wangdong.topnews.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.wangdong.topnews.Adapter.FirstPageListViewAdapter;
import com.wangdong.topnews.Bean.NewsInfo;
import com.wangdong.topnews.R;
import com.wangdong.topnews.WebActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import static com.wangdong.topnews.Constant.ID;
import static com.wangdong.topnews.Constant.URL;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    
    private String mParam1;
    private String mParam2;
    private View view;
    private String type;
    private PullToRefreshListView lvNews;
    private ArrayList<NewsInfo.ResultBean.DataBean> dataBeanList;
    private FirstPageListViewAdapter firstPageListViewAdapter;


    public NewsFragment() {
        // Required empty public constructor
    }
    
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(R.layout.fragment_news, container, false);
        }
        initView();
        initData();
        initListener();
        return view;
    }

    private void initListener() {
        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = dataBeanList.get(position).getUrl();
                Intent intent =new Intent();
                intent.putExtra("url",url);
                intent.setClass(getContext(), WebActivity.class);
                startActivity(intent);
            }
        });
        lvNews.setOnPullEventListener(new PullToRefreshBase.OnPullEventListener<ListView>() {
            @Override
            public void onPullEvent(PullToRefreshBase<ListView> refreshView, PullToRefreshBase.State state, PullToRefreshBase.Mode direction) {

            }
        });
        lvNews.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {

                Log.e("TAG", "onPullDownToRefresh"); // Do work to
                dataBeanList.clear();
                initData();
                lvNews.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        lvNews.onRefreshComplete();
                    }
                }, 500);
            }
        });
        }



    private void initView() {
        lvNews= (PullToRefreshListView) view.findViewById(R.id.lv_news);
        initRefreshListView();
    }

    private void initRefreshListView() {
        ILoadingLayout loadingLayoutProxy = lvNews.getLoadingLayoutProxy();
        String label = DateUtils.formatDateTime(
                getContext(),
                System.currentTimeMillis(),
                DateUtils.FORMAT_SHOW_TIME
                        | DateUtils.FORMAT_SHOW_DATE
                        | DateUtils.FORMAT_ABBREV_ALL);
        loadingLayoutProxy.setLastUpdatedLabel(label);
        loadingLayoutProxy.setPullLabel("下拉推荐");
        loadingLayoutProxy.setRefreshingLabel("正在刷新");
        loadingLayoutProxy.setReleaseLabel("松开推荐");
    }

    private void initData() {
        dataBeanList =new ArrayList<NewsInfo.ResultBean.DataBean>();
        Bundle arguments = getArguments();
        type = arguments.getString("type");
        RequestParams params = new RequestParams(URL);
        params.addBodyParameter("type",type);
        params.addBodyParameter("key",ID);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                NewsInfo newsInfo = gson.fromJson(result, NewsInfo.class);
                dataBeanList.addAll(newsInfo.getResult().getData());
                firstPageListViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        firstPageListViewAdapter = new FirstPageListViewAdapter(dataBeanList,getContext());
        lvNews.setAdapter(firstPageListViewAdapter);
    }


}
