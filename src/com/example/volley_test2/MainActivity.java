package com.example.volley_test2;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.protocol.ResponseConnControl;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		volley_Get();
		volley_Post();
	}

	private void volley_Post()
	{
		// 使用普通方法获取
		//       或
		// 直接使用JSON格式获取
		String url = "http://apis.juhe.cn/mobile/get?";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("phone", "13429667914");
		map.put("key", "335adcc4e891ba4e4be6d7534fd54c5d");
		JSONObject object = new JSONObject(map);
		JsonObjectRequest objectRequest = new JsonObjectRequest(Method.POST,
				url, object, new Listener<JSONObject>()
				{

					@Override
					public void onResponse(JSONObject response)
					{
						// TODO Auto-generated method stub
						Toast.makeText(MainActivity.this, response.toString(),
								Toast.LENGTH_LONG).show();		
					}
				}, new ErrorListener()
				{

					@Override
					public void onErrorResponse(VolleyError error)
					{
						// TODO Auto-generated method stub
						Toast.makeText(MainActivity.this, error.toString(),
								Toast.LENGTH_LONG).show();
					}
				});
		objectRequest.setTag("abcGet");
		MyApplication.getHttpQueue().add(objectRequest);
		
		
		StringRequest request = new StringRequest(Method.POST, null,
				new Listener<String>()
				{

					@Override
					public void onResponse(String response)
					{
						// TODO Auto-generated method stub
						Toast.makeText(MainActivity.this, response,
								Toast.LENGTH_LONG).show();		
					}
				}, new Response.ErrorListener()
				{

					@Override
					public void onErrorResponse(VolleyError error)
					{
						// TODO Auto-generated method stub
						Toast.makeText(MainActivity.this, error.toString(),
								Toast.LENGTH_LONG).show();		
					}
				})
		{
			@Override
			protected Map<String, String> getParams() throws AuthFailureError
			{
				// TODO Auto-generated method stub
				Map<String, String> hashMap = new HashMap<String, String>();
				hashMap.put("phone", "13429667914");
				hashMap.put("key", "335adcc4e891ba4e4be6d7534fd54c5d");
				return super.getParams();
			}

		};
		request.setTag("abcPost");
		MyApplication.getHttpQueue().add(request);
	}

	private void volley_Get()
	{
		String url = "http://apis.juhe.cn/mobile/get?phone=13429667914&key=335adcc4e891ba4e4be6d7534fd54c5d";
		// 使用普通方法获取
		// StringRequest request = new StringRequest(Method.GET, url, new
		// Listener<String>()
		// {
		//
		// @Override
		// public void onResponse(String response)
		// {
		// // TODO Auto-generated method stub
		// Toast.makeText(MainActivity.this, response ,
		// Toast.LENGTH_LONG).show();
		// }
		// }, new Response.ErrorListener()
		// {
		//
		// @Override
		// public void onErrorResponse(VolleyError error)
		// {
		// // TODO Auto-generated method stub
		// Toast.makeText(MainActivity.this,error.toString() ,
		// Toast.LENGTH_LONG).show();
		// }
		// });
		//
		// request.setTag("abcGet");
		// MyApplication.getHttpQueue().add(request);

		// 直接使用JSON格式获取
//		JsonRequest request = new JsonObjectRequest(Method.GET, url, null,
//				new Listener<JSONObject>()
//				{
//
//					@Override
//					public void onResponse(JSONObject response)
//					{
//						// TODO Auto-generated method stub
//						Toast.makeText(MainActivity.this, response.toString(),
//								Toast.LENGTH_LONG).show();
//					}
//				}, new Response.ErrorListener()
//				{
//
//					@Override
//					public void onErrorResponse(VolleyError error)
//					{
//						// TODO Auto-generated method stub
//						Toast.makeText(MainActivity.this, error.toString(),
//								Toast.LENGTH_LONG).show();
//					}
//				});
//		request.setTag("abcGet");
//		MyApplication.getHttpQueue().add(request);
		
		//直接使用自定义封装的接口来实现
		VolleyRequest.RequestGet(this, url, "abcGet", new VolleyInterface(this , VolleyInterface.mListener ,VolleyInterface.mErrorListener)
		{
			
			@Override
			public void onMySuccess(String result)
			{
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, result,
						Toast.LENGTH_LONG).show();
			}
			
			@Override
			public void onMyError(VolleyError error)
			{
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, error.toString(),
						Toast.LENGTH_LONG).show();
			}
		});
	}
	@Override
	protected void onStop()
	{
		// TODO Auto-generated method stub
		super.onStop();
		MyApplication.getHttpQueue().cancelAll("abcGet");
	}

}
