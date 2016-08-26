package com.gmail.liutorovich.anatoliy.gippokratby;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.gmail.liutorovich.anatoliy.gippokratby.Adapters.SuggestionAdapter;
import com.gmail.liutorovich.anatoliy.gippokratby.Parsers.CityParsTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String id="141";
    Button btnGippokrat;
    Button btnPerson;
    Button btnRegion;
    Button btnSearch;
    private LocationManager locationManager;
    private LocationListener mLocationListener;
    private final static String url = "http://api.gippokrat.by/get/city.html?type=xml";
    static String url_zapros;
    static AutoCompleteTextView acTextView;
    static double latitude;
    static double longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            new CityParsTask(this, url).execute();



        btnGippokrat = (Button) findViewById(R.id.btnGippokrat);
        btnPerson = (Button) findViewById(R.id.btnPerson);
        btnRegion = (Button) findViewById(R.id.btnRegion);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnRegion.setOnClickListener(this);
        btnGippokrat.setOnClickListener(this);
        btnPerson.setOnClickListener(this);
        btnSearch.setOnClickListener(this);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = new MyLocationListener();

        acTextView = (AutoCompleteTextView) findViewById(R.id.autoComplete);
        acTextView.setThreshold(3);
        acTextView.setAdapter(new SuggestionAdapter(this, acTextView.getText().toString()));
    } catch (Throwable t) {
            Toast.makeText(this, "Нет подключения к интернету",
                    Toast.LENGTH_LONG).show();
    }
    }


    @Override
    public void onClick(View v) {
        // по id определеяем кнопку, вызвавшую этот обработчик
        switch (v.getId()) {
            case R.id.btnRegion:
                // кнопка выбор региона
                Intent intent = new Intent(this, Activity_City.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.btnGippokrat:
                // кнопка перехода на Gippokrat.by
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://gippokrat.by"));
                startActivity(intent1);
                break;
            case R.id.btnPerson:
                // кнопка Личный кабинет
                Intent intent3 = new Intent(this, Activity_Person.class);
                startActivity(intent3);
                break;
            case R.id.btnSearch:
                // кнопка поиска
                String preparat = acTextView.getText().toString();
                url_zapros = "http://api.gippokrat.by/get/?cit y=" + id + "&zapros=" + preparat + "&type=xml&key=MUUdeHa4592liK134HE06vq23&%20geo_d=" + longitude + "&geo_sh=" + latitude + "&radius=10";

                new CityParsTask(this, url_zapros).execute();
                Intent intent2 = new Intent(this, Activity_Zapros.class);
                startActivity(intent2);
                break;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        String name = data.getStringExtra("name");
        btnRegion.setText(name);
        id = CityParsTask.map.get(name);
    }

    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000 * 1, 1, mLocationListener);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    // отслеживает изменение местоположения
    public class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location loc) {
            loc.getLatitude();
            loc.getLongitude();
            latitude =loc.getLatitude();
            longitude=loc.getLongitude();
        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(getApplicationContext(), "Отслеживание Gps отключено",
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    }
}
