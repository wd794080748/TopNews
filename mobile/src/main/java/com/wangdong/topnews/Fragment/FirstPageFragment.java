package com.wangdong.topnews.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangdong.topnews.Adapter.FirstPageAdapter;
import com.wangdong.topnews.R;

import java.util.ArrayList;

import static com.wangdong.topnews.Constant.titleName;
import static com.wangdong.topnews.Constant.titleTag;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirstPageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FirstPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstPageFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View view;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private ArrayList<Fragment> fragmentArrayList;
    private FirstPageAdapter firstPageAdapter;

    public FirstPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstPageFragment newInstance(String param1, String param2) {
        FirstPageFragment fragment = new FirstPageFragment();
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
            view=inflater.inflate(R.layout.fragment_first_page, container, false);
        }
        initView();
        initData();
        return view;
    }

    private void initData() {
        fragmentArrayList = new ArrayList<>();
        for(int i=0;i<titleName.length;i++){
        NewsFragment newsFragment=new NewsFragment();
            Bundle bundle=new Bundle();
            bundle.putString("type",titleTag[i]);
            newsFragment.setArguments(bundle);
            fragmentArrayList.add(newsFragment);
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            tabLayout.addTab(tabLayout.newTab().setText(titleName[0]));
            tabLayout.addTab(tabLayout.newTab().setText(titleName[1]));
            tabLayout.addTab(tabLayout.newTab().setText(titleName[2]));
            tabLayout.addTab(tabLayout.newTab().setText(titleName[3]));
            tabLayout.addTab(tabLayout.newTab().setText(titleName[4]));
            tabLayout.addTab(tabLayout.newTab().setText(titleName[5]));
            tabLayout.addTab(tabLayout.newTab().setText(titleName[6]));
            tabLayout.addTab(tabLayout.newTab().setText(titleName[7]));
            tabLayout.addTab(tabLayout.newTab().setText(titleName[8]));
            tabLayout.addTab(tabLayout.newTab().setText(titleName[9]));
            firstPageAdapter = new FirstPageAdapter(getActivity().getSupportFragmentManager(),titleName,fragmentArrayList);
            viewPager.setAdapter(firstPageAdapter);
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    private void initView() {
        tabLayout= (TabLayout) view.findViewById(R.id.tl_firstpage);
        viewPager= (ViewPager) view.findViewById(R.id.vp_firstpage);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
