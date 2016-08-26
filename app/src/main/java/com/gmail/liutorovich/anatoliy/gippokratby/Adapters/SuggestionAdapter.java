package com.gmail.liutorovich.anatoliy.gippokratby.Adapters;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import com.gmail.liutorovich.anatoliy.gippokratby.Parsers.PullParse;

import org.xmlpull.v1.XmlPullParserFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaZ on 30.05.2016.
 */
public class SuggestionAdapter extends ArrayAdapter<String> {
    private final static String url ="http://api.gippokrat.by/get/items.html?zapros=";
    protected static final String TAG = "SuggestionAdapter";
    private List<String> atv_list;
    private XmlPullParserFactory xmlFactoryObject;
    private String nameFilter;
    public SuggestionAdapter(Activity context, String nameFilter) {
        super(context, android.R.layout.simple_dropdown_item_1line);
        atv_list = new ArrayList<String>();
        this.nameFilter=nameFilter;
    }

    @Override
    public int getCount() {
        return atv_list.size();
    }

    @Override
    public String getItem(int index) {
        return atv_list.get(index);
    }

    @Override
    public Filter getFilter() {
        Filter myFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                PullParse jp=new PullParse();
                if (constraint != null) {
                    // A class that queries a web API, parses the data and
                    // returns an ArrayList
                    List<String> list =jp.getPullParse(url, constraint.toString());
                    atv_list.clear();
                    for (int i=0;i<list.size();i++) {
                        atv_list.add(list.get(i));
                    }

                    // Now assign the values and count to the FilterResults
                    // object
                    filterResults.values = atv_list;
                    filterResults.count = atv_list.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence contraint,
                                          FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return myFilter;
    }

}
