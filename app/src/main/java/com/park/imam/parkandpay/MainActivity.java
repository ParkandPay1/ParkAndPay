package com.park.imam.parkandpay;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.park.imam.parkandpay.Adapters.ParkingSpotAdapter;
import com.park.imam.parkandpay.Models.AppUser;
import com.park.imam.parkandpay.Models.ParkingSpot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    public static Server server;
    public static AppUser user;
    public ListView spot_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        server = new Server(MainActivity.this);
        spot_lv = (ListView)findViewById(R.id.spot_lv);
       login();
       // redirctUser();

     // this is for test posting to github
        //Error:Execution failed for task ':app:transformClassesWithDexBuilderForDebug'.

    }

    public void login()
    {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.login);
        dialog.setCancelable(false);

        ((Button)dialog.findViewById(R.id.signup_bt)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });

        ((Button)dialog.findViewById(R.id.login_bt)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                server.login(((EditText)dialog.findViewById(R.id.userName_tx)).getText().toString(), ((EditText)dialog.findViewById(R.id.userPassword_tx)).getText().toString(), new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
                    {
                        String res = null;
                        try {
                            res = new String(responseBody, "UTF-8");
                            Log.i("OK",  res);
                            JSONObject juser = new JSONObject(res);
                            user = new AppUser(juser.toString());
                            dialog.cancel();

                            Toast.makeText(MainActivity.this,"Welcome "+user.UserFullName,Toast.LENGTH_LONG).show();
                            redirctUser();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(MainActivity.this,"Sorry"+statusCode,Toast.LENGTH_LONG).show();
                    }
                });
            }
        });



        try
        {
            dialog.show();
        }catch (Exception e)
        {

        }
    }

    private void redirctUser()
    {
        if(user != null)
        {
            if(user.UserType==2)
            {
                Intent admin_activity = new Intent() ;
                MainActivity.this.startActivity(admin_activity);
            }
        }
        else
        {


        }
        server.getSpots(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
            {
                ArrayList<ParkingSpot> spots = new ArrayList<>();
                String res;
                try {
                    res = new String(responseBody, "UTF-8");
                    JSONArray jspots = new JSONArray(res);
                    for(int i=0;i<jspots.length();i++)
                    {
                        ParkingSpot t = new ParkingSpot( jspots.get(i).toString());
                        spots.add(t);
                    }
                    ParkingSpotAdapter adapter = new ParkingSpotAdapter(MainActivity.this,spots);
                    spot_lv.setAdapter(adapter);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void signup()
    {

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.signup);
        dialog.setCancelable(false);



        ((Button)dialog.findViewById(R.id.Sign_up)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    if( true)
                    {
                        AppUser tuser = new AppUser();
                        try
                        {
                            tuser.UserFullName = ((EditText)dialog.findViewById(R.id.userFullName_tx)).getText().toString();
                            tuser.UserName = ((EditText)dialog.findViewById(R.id.userName_tx)).getText().toString();
                            tuser.UserPhone = ((EditText)dialog.findViewById(R.id.userPhone_tx)).getText().toString();
                            tuser.UserEmail = ((EditText)dialog.findViewById(R.id.userEmail_tx)).getText().toString();
                            tuser.UserPassword = ((EditText)dialog.findViewById(R.id.userPassword_tx)).getText().toString();
                        }catch (Exception e)
                        {
                            Log.e("error",e.getLocalizedMessage());
                        }

                        Log.e("user", tuser.toJson().toString());


                        server.sinUpUser(tuser, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                Toast.makeText(MainActivity.this,"Sucessfull sign up",Toast.LENGTH_LONG).show();
                                dialog.cancel();
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                Toast.makeText(MainActivity.this,"server error",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Plz make sure of your informations",Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e)
                {
                    Log.e("error", e.getMessage());
                }

            }
        });






        try
        {
            dialog.show();
        }catch (Exception e)
        {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
