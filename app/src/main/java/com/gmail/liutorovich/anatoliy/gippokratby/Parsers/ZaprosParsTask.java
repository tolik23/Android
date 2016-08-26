package com.gmail.liutorovich.anatoliy.gippokratby.Parsers;

import android.app.ProgressDialog;
import android.os.AsyncTask;
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
 * Created by MaZ on 30.05.2016.
 */
public class ZaprosParsTask extends AsyncTask<ArrayList<String>, Void, ArrayList<String>> {
    private MainActivity activity;
    private String url;
    private XmlPullParserFactory xmlFactoryObject;
    private ProgressDialog pDialog1;
    public static ArrayList<String> list_products = new ArrayList<String>();
    static Map<String, String> map = new HashMap<String, String>();
    public ZaprosParsTask(MainActivity activity, String url) {
        this.activity = activity;
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog1 = new ProgressDialog(activity);
        pDialog1.setMessage("Загрузка...");
        pDialog1.show();
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
            ArrayList<String> list_products = (ArrayList<String>) parseXML(parser);
            stream.close();

            return list_products;

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(activity,
                    "Нет подключения к интернету.", Toast.LENGTH_SHORT)
                    .show();
            return null;
        }

    }


    public ArrayList<String> parseXML(XmlPullParser parser) {


        try {
            while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() == XmlPullParser.START_TAG
                        && parser.getName().equals("products")) {
                    if (parser.getEventType() == XmlPullParser.START_TAG
                            && parser.getName().equals("product")) {

                        String id = parser.getAttributeValue(0);
                        String name = parser.getAttributeValue(1);
                        String form = parser.getAttributeValue(2);
                        String producer = parser.getAttributeValue(5);
                        list_products.add(name + "\n" + form + "\n" + producer);
                    }

                }
                parser.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(activity,
                    "Нет подключения к интернету.", Toast.LENGTH_SHORT)
                    .show();
        }
        return list_products;
    }

    @Override
    protected void onPostExecute(ArrayList<String> list_products) {
        super.onPostExecute(list_products);
        pDialog1.dismiss();
    }
}
