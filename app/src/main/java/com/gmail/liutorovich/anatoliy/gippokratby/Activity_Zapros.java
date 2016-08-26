package com.gmail.liutorovich.anatoliy.gippokratby;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.gmail.liutorovich.anatoliy.gippokratby.Fragments.BaseFragment;
import com.gmail.liutorovich.anatoliy.gippokratby.Fragments.FragmentZapros;
import com.gmail.liutorovich.anatoliy.gippokratby.Toolbarlistener.Toolbarlistener;

/**
 * Created by ToLik on 31.05.2016.
 */
public class Activity_Zapros extends AppCompatActivity implements Toolbarlistener {

    private TextView mTitleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zapros_main);
        initToolbar();

        showFragment(FragmentZapros.createInstance(getSupportFragmentManager()), false, false);
    }

    protected void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTitleText = (TextView) toolbar.findViewById(R.id.tv_title);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    public void showFragment(BaseFragment fragment, boolean addToBackStack, boolean clearToBackStak) {

        if(clearToBackStak){
            while (getSupportFragmentManager().getBackStackEntryCount()>0){
                getSupportFragmentManager().popBackStackImmediate();
            }
        }

        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


        if(addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();

    }

    public void setTitle(CharSequence title) {mTitleText.setText(title);}

    public void setTitle(int titleId) {mTitleText.setText(titleId);}

    @Override
    public void switchFragment(BaseFragment fragment, boolean addToBackStak, boolean clearToBackStak) {
        showFragment(fragment,addToBackStak, clearToBackStak);
    }
}
