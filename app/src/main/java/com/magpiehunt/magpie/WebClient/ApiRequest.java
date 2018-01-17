package com.magpiehunt.magpie.WebClient;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by James on 1/15/2018.
 */

public class ApiRequest {

    private static ApiRequest instance;
    private RequestQueue requestQueue;
    private static Context context;

    private ApiRequest(Context context)
    {
        this.context = context;
        requestQueue = getRequestQueue();
    }
    private RequestQueue getRequestQueue()
    {
        if(requestQueue == null)
                requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        return requestQueue;
    }
    public  static synchronized ApiRequest getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new ApiRequest(context);
        }
        return instance;
    }
    public<T> void addRequastQueue(Request<T> request)
    {
        getRequestQueue().add(request);
    }
}
