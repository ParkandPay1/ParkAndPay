package com.park.imam.parkandpay.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by apple on 3/12/18.
 */

public class Reservation {
    public int Id,ReservationID;
    public Time ArrivalTime,DepartureTime,Duration;
    public Date ReserveDate;


    public Reservation()
    {

    }

    public Reservation(String ReservationJson)
    {
        try {
            JSONObject t = new JSONObject(ReservationJson);


            this.Id = t.getInt("Id");
            this.ReservationID = t.getInt("ReservationID");
            //this.ArrivalTime =t.("ArrivalTime");
            //this.DepartureTime = t.("DepartureTime");
            //this.Duration = t.("Duration");
            //this.ReserveDate = t.("ReserveDate");


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject toJson()
    {
        JSONObject t = new JSONObject();
        try {
            t.put("Id",this.Id);
            t.put("ReservationID",this.ReservationID);
            t.put("ArrivalTime",this.ArrivalTime);
            t.put("DepartureTime",this.DepartureTime);
            t.put("Duration",this.Duration);
            t.put("ReserveDate",this.ReserveDate);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return t;

    }


}
