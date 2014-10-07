package com.yuhj.neihanduanzi.text;

import java.util.ArrayList;

import javax.xml.transform.ErrorListener;

import m.framework.network.ResponseCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.yuhj.neihanduanzi.R;
import com.yuhj.neihanduanzi.bean.AdEntity;
import com.yuhj.neihanduanzi.bean.CommentList;
import com.yuhj.neihanduanzi.bean.EntityList;
import com.yuhj.neihanduanzi.bean.ImageEntity;
import com.yuhj.neihanduanzi.bean.ImageList;
import com.yuhj.neihanduanzi.bean.TextEntity;
import com.yuhj.neihanduanzi.client.ClientAPI;

import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.content.Entity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @name TextActivity
 * @Descripation 这是一个专门用来测试的Activity，所有的测试内容都可以放在这里
 * @author 禹慧军
 * @email lin5667181@163.com
 * @date 2014-10-4
 * @version 1.0
 */

/**
 * 1.列表界面,第一次启动，并且数据为空的时候，自动加载数据
 * 2.只要列表没有数据，进入这个界面的时候，就尝试加载数据
 * 3.只要有数据,就不进行数据的加载
 * 4.进入这个界面并且有数据的情况下,自动检查是否有数据更新，并且显示新信息个数
 * */

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

	private Button button;

	private TextView textView;

	private Long lastTime = 0l;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text);
		queue = Volley.newRequestQueue(this);
		// 每次获取的文章的个数
		final int itemCount = 30;
		/*
		 * button=(Button) findViewById(R.id.button1); textView=(TextView)
		 * findViewById(R.id.textView1); button.setOnClickListener(new
		 * OnClickListener() {
		 * 
		 * @Override public void onClick(View arg0) { ClientAPI.getList(queue,
		 * CATEGORY_PICTURE, itemCount,TextActivity.this,lastTime); } });
		 */
		button=(Button) findViewById(R.id.button1);
		textView=(TextView)findViewById(R.id.textView1);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		long groupId = 3551461874l;//1410094021l;// 3551461874l;
		String offsetParam = "offset=0";
		ClientAPI.getComment(queue, groupId, offsetParam, this);
	}

	@Override
	public void onResponse(String result) {
		try {
			JSONObject json = new JSONObject(result);
			CommentList commentList = new CommentList();
			commentList.parseJson(json);
			long groupId = commentList.getGroupId();
			boolean hasMore = commentList.isHasMore();
			int totalNumber = commentList.getTotalNumber();
			System.out.println("------>" + hasMore + "-------" + groupId + "------"
					+ totalNumber);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 列表回调部分
	 * 
	 * @param result
	 *            void
	 */
	public void listOnResponse(String result) {
		try {
			JSONObject json = new JSONObject(result);
			// System.out.println("------->" + json.toString());
			// 获得根目录下的Data对象
			JSONObject object = json.getJSONObject("data");
			EntityList entityList = new EntityList();
			entityList.parseJson(object);
			if (entityList.isHasMore()) {
				lastTime = entityList.getMinTime();
				textView.setText("" + lastTime);
				String tip = entityList.getTip();
				
			} else {
				String tip = entityList.getTip();
			
			}
			// TODO 把entityList这个段子的数据集合体，传递给Listview之类的Adapter即可显示

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
