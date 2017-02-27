package com.example.toshiba.jsaonobject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.view.View;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callApi();

    }
    private void callApi()
    {

        JsonObjectRequest jobjreq = new JsonObjectRequest("http://192.168.0.14/selectdb.php", new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jarr = response.getJSONArray("result");

                            for(int i = 0; i < jarr.length() ; i++)
                            {
                                JSONObject jobj = (JSONObject) jarr.get(i);
                                String name = jobj.getString("n");

                                System.out.println(name);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        jobjreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 3 ,2));

        AppController ap = new AppController(MainActivity.this);

        ap.addToRequestQueue(jobjreq);

    }
}
