package com.park.imam.parkandpay;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.park.imam.parkandpay.Models.AppUser;

import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by apple on 3/11/18.
 */

public class Server {


    Context context;
    public static AsyncHttpClient client = new AsyncHttpClient();

    static final String API_URL = "http://parkandpay.4dsis.com/api/";



    public Server(Context con)
    {
        context= con;


        client.setConnectTimeout(30000);
        client.setResponseTimeout(30000);
    }


    public void login(String userName, String userPassword,AsyncHttpResponseHandler handler)
    {
        client.get(API_URL+"AppUsers/Login?userName="+userName+"&userPassword="+userPassword,handler);

    }

    public void sinUpUser(AppUser user, AsyncHttpResponseHandler handler)
    {
        Log.e("Out obj",user.UserFullName);
        Log.e("Out",user.toJson().toString());
        StringEntity entity = new StringEntity(user.toJson().toString(), "UTF-8");
        Log.i(" out ", user.toJson().toString());
        client.post(context, API_URL + "AppUsers/PostAppUser", entity, "application/json", handler);
    }

    public void getSpots(AsyncHttpResponseHandler handler)
    {
        client.get(API_URL+"ParkingSpots/",handler);

    }


}


