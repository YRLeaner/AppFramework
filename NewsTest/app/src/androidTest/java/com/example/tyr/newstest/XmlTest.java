package com.example.tyr.newstest;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.tyr.newstest.Constractor.CnblogInternet.XMLRequest;

import junit.framework.TestCase;

import org.junit.Test;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.PortUnreachableException;
import java.security.PublicKey;

/**
 * Created by tyr on 2017/2/21.
 */
public class XmlTest extends TestCase{


    public void testvolley_Get() {

        String url = "http://wcf.open.cnblogs.com/blog/sitehome/recent/10";
        XMLRequest xmlRequest = new XMLRequest(url, new Response.Listener<XmlPullParser>() {
            @Override
            public void onResponse(XmlPullParser xmlPullParser) {
                try {
                    int eventtype = xmlPullParser.getEventType();
                    while (eventtype != XmlPullParser.END_DOCUMENT) {
                        switch (eventtype) {
                            case XmlPullParser.START_TAG:
                                String nodeName = xmlPullParser.getName();
                                if ("entry".equals(nodeName)) {
                                    String title = xmlPullParser.getAttributeValue(1);
                                    Log.d("title", "title is" + title);
                                }
                                break;
                        }
                        eventtype = xmlPullParser.next();
                    }
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("TAG",volleyError.getMessage(),volleyError);
            }
        });
        MyApplication.getHttpQueues().add(xmlRequest);

    }

    @Test
    public void testR() throws Exception{
        testvolley_Get();
       // Log.d("My", "hahahahha");
    }
}
