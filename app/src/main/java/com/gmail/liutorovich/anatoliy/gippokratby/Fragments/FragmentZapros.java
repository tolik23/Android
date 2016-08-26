package com.gmail.liutorovich.anatoliy.gippokratby.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.liutorovich.anatoliy.gippokratby.R;
import com.gmail.liutorovich.anatoliy.gippokratby.Adapters.RecyclerAdapter;
import com.gmail.liutorovich.anatoliy.gippokratby.Toolbarlistener.Toolbarlistener;
import com.gmail.liutorovich.anatoliy.gippokratby.Parsers.ZaprosParsTask;

/**
 * Created by ToLik on 31.05.2016.
 */
public class FragmentZapros extends BaseFragment {

    private Toolbarlistener mToolbarlistener;
    private static final String TAG = FragmentZapros.class.getSimpleName();
    RecyclerAdapter adapter;
    RecyclerView recyclerView;


    public FragmentZapros() {
    }


    public static FragmentZapros createInstance(FragmentManager fragmentManager) {
        FragmentZapros fragmentZapros = (FragmentZapros) fragmentManager.findFragmentByTag(FragmentZapros.TAG);

        if(fragmentZapros == null){

            fragmentZapros = new FragmentZapros();
        }

        Bundle bundle = new Bundle();
        fragmentZapros.setArguments(bundle);
        return fragmentZapros;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_zapros, container, false);


//        setListAdapter(new ArrayAdapter<String>(
//                this, android.R.layout.simple_list_item_1, ZaprosParsTask.list_products));
//        mButton = (Button) view.findViewById(R.id.btn_button);
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mToolbarlistener !=null)
//                    mToolbarlistener.switchFragment(FragmentApteks.createInstance(getFragmentManager()),true,false);
//            }
//        });
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(mToolbarlistener !=null) mToolbarlistener.setTitle("Fragment One");

        recyclerView = (RecyclerView) getView().findViewById(R.id.myList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        adapter = new RecyclerAdapter(ZaprosParsTask.list_products);
        recyclerView.setAdapter(adapter);
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
    public String getFragmentTag() {return null;}
}
