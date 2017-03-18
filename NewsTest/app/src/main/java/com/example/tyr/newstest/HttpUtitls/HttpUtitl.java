package com.example.tyr.newstest.HttpUtitls;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.tyr.newstest.Constractor.CnblogInternet.XMLRequest;
import com.example.tyr.newstest.Entity.Author;
import com.example.tyr.newstest.Entity.BlogInfo;
import com.example.tyr.newstest.MyApplication;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

/**
 * Created by tyr on 2017/2/23.
 */
public class HttpUtitl {

    private List<BlogInfo> mlLists;
    private BlogInfo blogInfo;
    private int id;
    private String title;
    private Author author;

    public HttpUtitl(List<BlogInfo> mlLists) {
        this.mlLists = mlLists;
    }

    public void Cnbloginfo_volley_Get() {

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
                                //Log.d("title",nodeName);
                                if ("entry".equals(nodeName)) {
                                    blogInfo = new BlogInfo();
                                  /*  String title = xmlPullParser.nextText();
                                    Log.d("title", "title is" + title);*/
                                }
                                if ("id".equals(nodeName)){
                                    blogInfo.setId(xmlPullParser.nextText());
                                }
                                if ("title".equals(nodeName)){
                                    blogInfo.setTitle(xmlPullParser.nextText());
                                }
                                if ("author".equals(nodeName)){
                                    blogInfo.setAuthor(new Author("1233"));
                                }
                                break;
                            case XmlPullParser.END_TAG:
                                String nodeName2 = xmlPullParser.getName();
                                if ("entry".equals(nodeName2)){
                                    mlLists.add(blogInfo);
                                }

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
}
