package com.yuhj.neihanduanzi.client;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.yuhj.neihanduanzi.text.TextActivity;

/**
 * @name ClientAPI
 * @Descripation
 * 所有和服务器接口的方法全部在这个类里
 * @author 禹慧军
 * @email lin5667181@163.com
 * @date 2014-10-4
 * @version 1.0
 */
public class ClientAPI {

	/**
	 * 获取内涵段子的列表内容
	 * 
	 * @param CateGroyType
	 *            要获取的参数类型
	 * @param itemCount
	 *            每次要获取的条目数
	 * @param responListener
	 *            用于获取段子列表的回调接口
	 * @param queue 
	 * 			  用于请求网络的队列
	 * @see TextActivity#CATEGORY_ARTICLE
	 * @see #CATEGORY_PIVTURE
	 */
	public static  void getList(RequestQueue queue, int CateGroyType, int itemCount,
			Response.Listener<String> responseListener) {
	
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

}
