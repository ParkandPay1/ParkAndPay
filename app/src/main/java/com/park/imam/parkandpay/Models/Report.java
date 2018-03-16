package com.park.imam.parkandpay.Models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 3/12/18.
 */

public class Report {
    public int Id,ReportID;
    public  String Title,Message;

    public Report()
    {

    }

    public Report(String PaymentJson)
    {
        try {
            JSONObject t = new JSONObject(PaymentJson);


            this.Id = t.getInt("Id");
            this.ReportID = t.getInt("ReportID");
            this.Title = t.getString("Title");
            this.Message = t.getString("Message");


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject toJson()
    {
        JSONObject t = new JSONObject();
        try {
            t.put("Id",this.Id);
            t.put("ReportID",this.ReportID);
            t.put("Title",this.Title);
            t.put("Message",this.Message);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return t;

    }

}