package com.example.volley_test2;

import java.util.Map;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.StringRequest;

public class VolleyRequest
{
	public static StringRequest stringRequest;
	public static Context context;
	public static void RequestGet(Context mContext,String url,String tag ,VolleyInterface vif)
	{
		MyApplication.getHttpQueue().cancelAll(tag);
		stringRequest = new StringRequest(Method.GET,url, vif.loadingListener(), vif.errorListener());
		stringRequest.setTag(tag);
		MyApplication.getHttpQueue().add(stringRequest);
		MyApplication.getHttpQueue().start();
	}
	
	public static void RequestPost(Context mContext,String url,String tag, final Map<String, String> params ,VolleyInterface vif)
	{
		MyApplication.getHttpQueue().cancelAll(tag);
		stringRequest = new StringRequest(url, vif.loadingListener(), vif.errorListener())
		{
			@Override
			protected Map<String, String> getParams() throws AuthFailureError
			{
				// TODO Auto-generated method stub
				return params;
				
			}
		};
		stringRequest.setTag(tag);
		MyApplication.getHttpQueue().add(stringRequest);
		MyApplication.getHttpQueue().start();
	}
}
