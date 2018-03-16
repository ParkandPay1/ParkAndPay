package com.park.imam.parkandpay.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.park.imam.parkandpay.Models.ParkingSpot;
import com.park.imam.parkandpay.R;

import java.util.ArrayList;

/**
 * Created by apple on 3/14/18.
 */

public class ParkingSpotAdapter extends ArrayAdapter<ParkingSpot> {


    public ParkingSpotAdapter(@NonNull Context context, @NonNull ArrayList<ParkingSpot> parkingSpots) {
        super(context, 0, parkingSpots);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ParkingSpot parkingSpot = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spot_list_item, parent, false);
        }

        ((TextView)convertView.findViewById(R.id.spotNum_tx)).setText(parkingSpot.SpotNum);
        if(parkingSpot.Availability ==1)
        {
            ((TextView)convertView.findViewById(R.id.spotNum_tx)).setBackgroundColor(Color.RED);
        }
        convertView.setTag(parkingSpot);

        return convertView;

    }
}
