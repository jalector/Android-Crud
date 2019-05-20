package com.example.crud.service;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

public class GlobalRequest {
    public static String URL_BASE = "http://192.168.1.66:8000/api/";
    public static String POKEMON = "pokemon";

    public static  JsonObjectRequest postRequest(
            String uri,
            JSONObject body,
            Response.Listener listener,
            Response.ErrorListener errorListener
    ){
        return new JsonObjectRequest(
                Request.Method.POST,
                GlobalRequest.URL_BASE + uri,
                body,
                listener,
                errorListener
        );
    }

    public static JsonObjectRequest patchRequest(
            String uri,
            JSONObject body,
            Response.Listener listener,
            Response.ErrorListener errorListener
    ){
        return new JsonObjectRequest(
                Request.Method.PATCH,
                GlobalRequest. URL_BASE + uri,
                body,
                listener,
                errorListener
        );
    }

    public static StringRequest getRequest(
            String uri,
            Response.Listener listener,
            Response.ErrorListener errorListener
    ){
        return new StringRequest(
                Request.Method.GET,
                GlobalRequest.URL_BASE + uri,
                listener,
                errorListener
        );
    }

    public static StringRequest deleteRequest(
            String uri,
            Response.Listener listener,
            Response.ErrorListener errorListener
    ){
        return new StringRequest(
            Request.Method.DELETE,
            GlobalRequest.URL_BASE + uri,
            listener,
            errorListener
        );
    }


}
