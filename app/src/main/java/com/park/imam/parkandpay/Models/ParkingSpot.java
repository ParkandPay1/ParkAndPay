package com.park.imam.parkandpay.Models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 3/11/18.
 */

public class ParkingSpot {
    public String SpotNum,SpotLocation;
    public int Id, Availability ;

    public ParkingSpot(){

    }


    public ParkingSpot(String parkingSpotJson)
    {
        try {
            JSONObject t = new JSONObject(parkingSpotJson);


            this.Id = t.getInt("Id");
            this.Availability= t.getInt("Availability");
            this.SpotNum = t.getString("SpotNum");
            this.SpotLocation = t.getString("SpotLocation");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject toJson()
    {
        JSONObject t = new JSONObject();
        try {
            t.put("Id",this.Id);
            t.put("Availability",this.Availability);
            t.put("SpotNum",this.SpotNum);
            t.put("SpotLocation",this.SpotLocation);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return t;

    }

}
