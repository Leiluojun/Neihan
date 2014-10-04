package com.yuhj.neihanduanzi.text;

import java.util.ArrayList;

import m.framework.network.ResponseCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.yuhj.neihanduanzi.R;
import com.yuhj.neihanduanzi.bean.ImageEntity;
import com.yuhj.neihanduanzi.bean.ImageList;
import com.yuhj.neihanduanzi.client.ClientAPI;

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

public class TextActivity extends Activity implements Response.Listener<String> {
	/**
	 * 分类ID,代表文本
	 */
	public final static int CATEGORY_ARTICLE = 1;
	/**
	 * 分类ID,代表图片
	 */
	private final static int CATEGORY_PICTURE = 2;

	/**
	 * 用于请求网络的队列
	 */
	private RequestQueue queue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text);
		queue = Volley.newRequestQueue(this);
		// 每次获取的文章的个数
		int itemCount = 30;
		ClientAPI.getList(queue, CATEGORY_PICTURE, itemCount, this);
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
			// System.out.println("------->" + json.toString());
			// 获得根目录下的Data对象
			JSONObject object = json.getJSONObject("data");
			// 从data对象中，获取名称为data的数组
			JSONArray array = object.getJSONArray("data");
			int len = array.length();
			if (len > 0) {
				// 遍历数组中每一条段子的信息
				for (int i = 0; i < len; i++) {
					JSONObject item = array.getJSONObject(i);
					ImageEntity imageEntity =new ImageEntity();
					imageEntity.ParseImageItem(item);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
