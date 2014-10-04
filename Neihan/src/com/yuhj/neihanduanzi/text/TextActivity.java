package com.yuhj.neihanduanzi.text;

import m.framework.network.ResponseCallback;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.yuhj.neihanduanzi.R;
import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

/**
 * @name TextActivity
 * @Descripation 这是一个专门用来测试的Activity，所有的测试内容都可以放在这里
 * @author 禹慧军
 * @email lin5667181@163.com
 * @date 2014-10-4
 * @version 1.0
 */

public class TextActivity extends Activity implements Response.Listener<String>{
	/**
	 * 分类ID,代表文本
	 */
	private final static int CATEGORY_ARTICLE = 1;
	/**
	 * 分类ID,代表图片
	 */
	private final static int CATEGORY_PICTURE = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text);
		// 每次获取的文章的个数
		int itemCount = 30;
		getList(CATEGORY_ARTICLE, itemCount,this);
	}

	/**
	 * 获取内涵段子的列表内容
	 * 
	 * @param CateGroyType
	 *            要获取的参数类型
	 * @param itemCount
	 *            每次要获取的条目数
	 * @see #CATEGORY_ARTICLE
	 * @see #CATEGORY_PIVTURE
	 */
	private void getList(int CateGroyType, int itemCount,
			Response.Listener<String> responseListener) {
		RequestQueue queue = Volley.newRequestQueue(this);
		String CATEGORY_LIST_API = "http://ic.snssdk.com/2/essay/zone/category/data/";
		// 分类参数,根据类型获取不同的数据
		String categoryParam = "category_id=" + CateGroyType;
		String countParam = "count=" + itemCount;
		String device_type = "device_type=KFTT";
		String openUDID = "openudid=b90ca6a3a19a78d6";
		String url = CATEGORY_LIST_API
				+ "?"
				+ categoryParam
				+ "&"
				+ countParam
				+ "&"
				+ device_type
				+ "&"
				+ openUDID
				+ "&level=6&iid=2337593504&device_id=2757969807&ac=wifi&channel=wandoujia&aid=7&app_name=joke_essay&version_code=302&device_platform=android&os_api=15&os_version=4.0.3";
		queue.add(new StringRequest(Request.Method.GET, url, responseListener,
				new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub

					}
				}));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.text, menu);
		return true;
	}

	@Override
	public void onResponse(String result) {
		try {
			JSONObject json = new JSONObject(result);
			System.out.println("------->" + json.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}



}
