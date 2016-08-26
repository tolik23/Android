package com.gmail.liutorovich.anatoliy.gippokratby.Parsers;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.Menu;
import android.widget.Toast;

import com.gmail.liutorovich.anatoliy.gippokratby.MainActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ToLik on 30.05.2016.
 */
public class CityParsTask extends AsyncTask<ArrayList<String>, Void, ArrayList<String>> {
    public MainActivity activity;
    private String url;
    private XmlPullParserFactory xmlFactoryObject;
    private ProgressDialog pDialog;
    public static ArrayList<String> list = new ArrayList<String>();
    public static Map<String, String> map = new HashMap<String, String>();
    public CityParsTask(MainActivity activity, String url) {
        this.activity = activity;
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
            pDialog = new ProgressDialog(activity);
            pDialog.setTitle("Поиск лекарственных препоратов.");
            pDialog.setMessage("Загрузка...");
            pDialog.show();
    }


    @Override
    protected ArrayList<String> doInBackground(ArrayList<String>... params) {

        try {
            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000 /* milliseconds */);
            connection.setConnectTimeout(15000 /* milliseconds */);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            InputStream stream = connection.getInputStream();

            xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser parser = xmlFactoryObject.newPullParser();

            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(stream, null);
            ArrayList<String> list = (ArrayList<String>) parseXML(parser);
            stream.close();

            return list;

        } catch (Exception e) {
            e.printStackTrace();
//            Toast.makeText(activity,
//                    "Нет подключения к интернету.", Toast.LENGTH_SHORT)
//                    .show();
            return null;
        }

    }


    public ArrayList<String> parseXML(XmlPullParser parser) {


        try {
            while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() == XmlPullParser.START_TAG
                        && parser.getName().equals("city")) {

                    String id = parser.getAttributeValue(0);
                    String city = parser.getAttributeValue(2);
                    list.add(city);
                    map.put(city,id);
                }
                parser.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
//            Toast.makeText(activity,
//                    "Нет подключения к интернету.", Toast.LENGTH_SHORT)
//                    .show();
        }
        return list;
    }

    @Override
    protected void onPostExecute(ArrayList<String> list) {
        super.onPostExecute(list);
        pDialog.dismiss();
    }
}
