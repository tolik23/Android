package com.gmail.liutorovich.anatoliy.gippokratby.Toolbarlistener;

import com.gmail.liutorovich.anatoliy.gippokratby.Fragments.BaseFragment;

/**
 * Created by ToLik on 31.05.2016.
 */
public interface Toolbarlistener {
    void setTitle(CharSequence name);
    void setTitle(int resTitleId);
    void switchFragment(BaseFragment fragment, boolean addToBackStak, boolean clearToBackStak);

}
