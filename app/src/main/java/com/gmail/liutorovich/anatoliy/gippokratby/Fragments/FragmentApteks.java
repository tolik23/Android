package com.gmail.liutorovich.anatoliy.gippokratby.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.liutorovich.anatoliy.gippokratby.R;
import com.gmail.liutorovich.anatoliy.gippokratby.Toolbarlistener.Toolbarlistener;

/**
 * Created by ToLik on 01.06.2016.
 */
public class FragmentApteks extends BaseFragment  {

    private Toolbarlistener mToolbarlistener;
    private static final String TAG = FragmentApteks.class.getSimpleName();


    public static FragmentApteks createInstance(FragmentManager fragmentManager) {
        FragmentApteks fragmentApteks = (FragmentApteks) fragmentManager.findFragmentByTag(FragmentApteks.TAG);

        if(fragmentApteks == null){

            fragmentApteks = new FragmentApteks();
        }

        Bundle bundle = new Bundle();
        fragmentApteks.setArguments(bundle);
        return fragmentApteks;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_apteks,container,false);

//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mToolbarlistener !=null)
//                    mToolbarlistener.switchFragment(MyTwoFragment.createInstance(getFragmentManager()),true,false, FragmentAnim.RIGHT_TO_LEFT);
//            }
//        });
        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Toolbarlistener){
            mToolbarlistener =(Toolbarlistener) context;
        }
    }

    @Override
    public void onDetach() {
        mToolbarlistener=null;
        super.onDetach();
        setHasOptionsMenu(false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(mToolbarlistener !=null) mToolbarlistener.setTitle("Fragment Two");
    }

    @Override
    public String getFragmentTag() {return null;}

}
