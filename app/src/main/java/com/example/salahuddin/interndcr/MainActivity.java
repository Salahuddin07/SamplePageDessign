package com.example.salahuddin.interndcr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import static android.R.layout.simple_spinner_item;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Spinner productGroupSpinner,literatureSpinner,physicianSpinner,giftSpinner;
    private RequestQueue mQueue;
    private RequestQueue mQueue1;
    private RequestQueue mQueue2;
    private ArrayAdapter<String> myArrayAdapter;
    private ArrayAdapter<String> myArrayAdapter1;
    private ArrayAdapter<String> myArrayAdapter2;
    private ArrayList<String> arrayList;
    private ArrayList<String> arrayList1;
    private ArrayList<String> arrayList2;
    public static final String URL = "https://raw.githubusercontent.com/appinion-dev/intern-dcr-data/master/data.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"“done”",Toast.LENGTH_LONG).show();
            }
        });

        productGroupSpinner = findViewById(R.id.productGroupId);
        literatureSpinner = findViewById(R.id.literatureId);
        physicianSpinner = findViewById(R.id.physicianSampleId);
        giftSpinner = findViewById(R.id.giftSpinnerId);
        mQueue = Volley.newRequestQueue(this);
        mQueue1 = Volley.newRequestQueue(this);
        mQueue2 = Volley.newRequestQueue(this);
        arrayList = new ArrayList<>();
        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();

        arrayList.add("Choose");
        jsonParseProductGroup();
        arrayList1.add("Choose");
        jsonParsePhysicianSample();
        arrayList2.add("Choose");
        jsonParseGiftSample();
    }

    private void jsonParseProductGroup() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("product_group_list");
                            for (int i = 0;i <jsonArray.length();i++)
                            {
                                JSONObject productGroup = jsonArray.getJSONObject(i);
                                String productGroupName = productGroup.getString("product_group");
                                arrayList.add(productGroupName);
                                myArrayAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item,arrayList);
                                productGroupSpinner.setAdapter(myArrayAdapter);
                                literatureSpinner.setAdapter(myArrayAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"“Something Wrong”",Toast.LENGTH_LONG).show();
            }
        });
        mQueue.add(jsonObjectRequest);
    }

    private void jsonParsePhysicianSample() {

        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray1 = response.getJSONArray("physician_sample_list");
                            for (int i = 0;i <jsonArray1.length();i++)
                            {
                                JSONObject sample = jsonArray1.getJSONObject(i);
                                String sampleName = sample.getString("sample");
                                arrayList1.add(sampleName);
                                myArrayAdapter1 = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item,arrayList1);
                                physicianSpinner.setAdapter(myArrayAdapter1);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"“Something Wrong”",Toast.LENGTH_LONG).show();
            }
        });
        mQueue1.add(jsonObjectRequest1);
    }

    private void jsonParseGiftSample() {

        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray2 = response.getJSONArray("gift_list");
                            for (int i = 0;i <jsonArray2.length();i++)
                            {
                                JSONObject sample = jsonArray2.getJSONObject(i);
                                String sampleName = sample.getString("gift");
                                arrayList2.add(sampleName);
                                myArrayAdapter2 = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item,arrayList2);
                                giftSpinner.setAdapter(myArrayAdapter2);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"“Something Wrong”",Toast.LENGTH_LONG).show();
            }
        });
        mQueue2.add(jsonObjectRequest2);
    }
}
