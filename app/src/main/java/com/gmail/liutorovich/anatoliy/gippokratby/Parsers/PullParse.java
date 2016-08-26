package com.gmail.liutorovich.anatoliy.gippokratby.Parsers;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaZ on 30.05.2016.
 */
public class PullParse {
    private XmlPullParserFactory xmlFactoryObject;
    double current_latitude,current_longitude;
    public PullParse(){}
    public PullParse(double current_latitude,double current_longitude){
        this.current_latitude=current_latitude;
        this.current_longitude=current_longitude;
    }
    public List<String> getPullParse(String url, String sName){

        try {
            String temp=sName.replace(" ", "%20");
            URL finalURL = new URL(url + temp);
            HttpURLConnection connection = (HttpURLConnection) finalURL.openConnection();
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
            return null;
        }
    }


    public ArrayList<String> parseXML(XmlPullParser parser) {

        ArrayList<String> list = new ArrayList<String>();
        try {
            while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() == XmlPullParser.START_TAG
                        && parser.getName().equals("item")) {

                    String value = parser.getAttributeValue(0);
                    list.add(value);
                }
                parser.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
