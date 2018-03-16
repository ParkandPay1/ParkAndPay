package com.park.imam.parkandpay.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by apple on 3/12/18.
 */

public class Payment {
    public int Id,PaymentID;
    public  String PayMethod;
    public double FeeAmount=0;


    public Payment()
    {

    }

    public Payment(String PaymentJson)
    {
        try {
            JSONObject t = new JSONObject(PaymentJson);


            this.Id = t.getInt("Id");
            this.PaymentID = t.getInt("PaymentID");
            this.PayMethod = t.getString("PayMethod");
            this.FeeAmount = t.getDouble("FeeAmount");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject toJson()
    {
        JSONObject t = new JSONObject();
        try {
            t.put("Id",this.Id);
            t.put("PaymentID",this.PaymentID);
            t.put("PayMethod",this.PayMethod);
            t.put("FeeAmount",this.FeeAmount);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return t;

    }

}
