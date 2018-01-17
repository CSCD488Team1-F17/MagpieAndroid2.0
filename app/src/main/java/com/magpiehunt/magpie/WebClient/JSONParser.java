package com.magpiehunt.magpie.WebClient;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.magpiehunt.magpie.Database.MagpieDatabase;
import com.magpiehunt.magpie.Entities.Collection;
import com.magpiehunt.magpie.Entities.Landmark;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * Created by James on 1/12/2018.
 */

public class JSONParser {

    private String main_url = "http://10.104.176.172/api/";


    private Context context;
    public JSONParser(Context context)
    {
        this.context = context;
    }
    public void getCollections()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, main_url.concat("collections"), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                String type = "NONE";
                try {
                    JSONObject json = new JSONObject(response);
                    type = json.getString("result_type");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                parseCollection(gson, response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        ApiRequest.getInstance(context).addRequastQueue(stringRequest);
    }
    public void getLandmarks(int cid)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, main_url.concat("landmark/" + cid), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                String type = "NONE";
                try {
                    JSONObject json = new JSONObject(response);
                    type = json.getString("result_type");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                parseLandmark(gson, response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        ApiRequest.getInstance(context).addRequastQueue(stringRequest);
    }

    private void parseAward(Gson gson){

    }
    private void parseCollectionOwner(Gson gson) {
    }

    private void parseLandmarkImage(Gson gson) {
    }

    private void parseCollectionImage(Gson gson) {
    }

    private void parseLandmark(Gson gson,  String response) {
        LandmarkResult landmarkResult = gson.fromJson(response, LandmarkResult.class);
        MagpieDatabase db = MagpieDatabase.getMagpieDatabase(context);
        List<Landmark> landmarks = landmarkResult.getLandmarks();
        for(Landmark l: landmarks)
            db.landmarkDao().addLandmark(l);
    }

    private void parseCollection(Gson gson, String response) {
        CollectionResult collectionResult = gson.fromJson(response, CollectionResult.class);
        MagpieDatabase db = MagpieDatabase.getMagpieDatabase(context);
        List<Collection> collections = collectionResult.getCollections();
        for(Collection c: collections)
            db.collectionDao().addCollection(c);

    }
}
