package com.wangdong.topnews;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wangdong.topnews.Fragment.FirstPageFragment;
import com.wangdong.topnews.Fragment.HistoryFragment;
import com.wangdong.topnews.Fragment.MineFragment;
import com.wangdong.topnews.Fragment.VideoFragment;

public class MainActivity extends BaseActivity implements FirstPageFragment.OnFragmentInteractionListener{
    private RadioGroup rgSwitch;
    private RadioButton rbHome;
    private FirstPageFragment firstPageFragment;
    private HistoryFragment historyFragment;
    private VideoFragment videoFragment;
    private MineFragment mineFragment;
    private FragmentTransaction fragmentTransaction;
    private android.support.v4.app.FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        Listener();
    }

    private void HideFragmnet(FragmentTransaction fragmentTransaction) {
        if(firstPageFragment!=null){
            fragmentTransaction.hide(firstPageFragment);
        }
        if(videoFragment!=null){
            fragmentTransaction.hide(videoFragment);
        }
        if(historyFragment!=null){
            fragmentTransaction.hide(historyFragment);
        }
        if(mineFragment!=null){
            fragmentTransaction.hide(mineFragment);
        }
    }

    private void Listener() {
        rgSwitch.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                fragmentTransaction = supportFragmentManager.beginTransaction();
                //隐藏fragment
                HideFragmnet(fragmentTransaction);
                switch (i) {
                    case R.id.rb_firstpage:
                        if (firstPageFragment == null) {
                            firstPageFragment = new FirstPageFragment();
                            fragmentTransaction.add(R.id.fragment, firstPageFragment);
                        } else {
                            fragmentTransaction.show(firstPageFragment);
                        }

                        break;
                    case R.id.rb_video:
                        if (videoFragment == null) {
                            videoFragment = new VideoFragment();
                            fragmentTransaction.add(R.id.fragment, videoFragment);
                        } else {
                            fragmentTransaction.show(videoFragment);
                        }

                        break;
                    case R.id.rb_history:
                        if (historyFragment == null) {
                            historyFragment = new HistoryFragment();
                            fragmentTransaction.add(R.id.fragment, historyFragment);
                        } else {
                            fragmentTransaction.show(historyFragment);
                        }

                        break;
                    case R.id.rb_mine:
                        if (mineFragment == null) {
                            mineFragment = new MineFragment();
                            fragmentTransaction.add(R.id.fragment, mineFragment);
                        } else {
                            fragmentTransaction.show(mineFragment);
                        }
                        break;
                }
                fragmentTransaction.commit();
            }
        });
    }

    private void initData() {
        supportFragmentManager = getSupportFragmentManager();
    }

    private void initView() {
        rgSwitch = (RadioGroup) findViewById(R.id.rg_switch);
        rbHome = (RadioButton) findViewById(R.id.rb_firstpage);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
