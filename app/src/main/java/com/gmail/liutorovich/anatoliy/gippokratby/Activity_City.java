package com.gmail.liutorovich.anatoliy.gippokratby;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.gmail.liutorovich.anatoliy.gippokratby.Adapters.ListAdapter;
import com.gmail.liutorovich.anatoliy.gippokratby.Parsers.CityParsTask;

/**
 * Created by ToLik on 30.05.2016.
 */
public class Activity_City extends Activity {

    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_main);

        mListView = (ListView) findViewById(R.id.lv_list);

        ListAdapter listAdapter = new ListAdapter(this, CityParsTask.list);
        mListView.setAdapter(listAdapter);

        // нажатие на элемент ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                // получаем текст нажатого элемента
                String strText = ((TextView) itemClicked).getText().toString();

                // возвращаем текст нажатого элемента в MainActivity
                Intent intent = new Intent();
                intent.putExtra("name", strText);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }
}
