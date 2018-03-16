package com.park.imam.parkandpay.Models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 3/11/18.
 */

public class AppUser
{
    public int Id,UserType,UserStatus,UserPoints=0;
    public double Ewallet=0;
    public String UserName,UserFullName,UserPassword,UserEmail, UserPlate, UserPhone;

    public AppUser()
    {

    }

    public AppUser(String appUserJson)
    {
        try {
            JSONObject t = new JSONObject(appUserJson);


            this.Id = t.getInt("Id");
            this.Ewallet= t.getDouble("Ewallet");
            this.UserType = t.getInt("UserType");
            this.UserName = t.getString("UserName");
            this.UserFullName = t.getString("UserFullName");
            this.UserPassword = t.getString("UserPassword");
            this.UserEmail = t.getString("UserEmail");
            this.UserPlate = t.getString("UserPlate");
            this.UserPhone = t.getString("UserPhone");
           // this.UserPoints = t.getInt("UserPoints");
            this.UserStatus = t.getInt("UserStatus");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject toJson()
    {
        JSONObject t = new JSONObject();
        try {
           // t.put("Id",this.Id);
            t.put("Ewallet",this.Ewallet);
            t.put("UserType",this.UserType);
            t.put("UserName",this.UserName);
            t.put("UserFullName",this.UserFullName);
            t.put("UserPassword",this.UserPassword);
            t.put("UserEmail",this.UserEmail);
            t.put("UserPlate",this.UserPlate);
            t.put("UserPhone",this.UserPhone);
            t.put("UserStatus",this.UserStatus);
            t.put("Pcounter",this.UserPoints);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return t;

    }

}
