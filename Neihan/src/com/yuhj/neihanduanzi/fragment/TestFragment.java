package com.yuhj.neihanduanzi.fragment;

import java.util.ArrayList;

import org.apache.http.protocol.ResponseConnControl;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.yuhj.neihanduanzi.R;
import com.yuhj.neihanduanzi.adapter.EssayAdapter;
import com.yuhj.neihanduanzi.bean.EntityList;
import com.yuhj.neihanduanzi.bean.TextEntity;
import com.yuhj.neihanduanzi.client.ClientAPI;
import com.yuhj.neihanduanzi.widget.PullToRefreshLayout;
import com.yuhj.neihanduanzi.widget.PullToRefreshLayout.OnRefreshListener;

import android.R.integer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class TestFragment extends Fragment implements OnClickListener,
		OnScrollListener, OnRefreshListener {
	private View quickTools;
	private TextView textNotify;
	private View titleView;
	private View quickPublish;
	private View quickReview;
	private PullToRefreshLayout refreshLayoutManager;
	private PullToRefreshLayout loadLayoutManager;
	/**
	 * 初始化数据
	 */
	private final static int INIT = 0;
	/**
	 * 下拉刷新标志位
	 */
	private final static int REFRESH = 1;
	/**
	 * 上拉加载标志位
	 */
	private final static int LOAD = 2;
	private EssayAdapter adapter;
	private ArrayList<TextEntity> entities;
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

	private Long lastTime = 0l;

	private int RequestCategory = CATEGORY_ARTICLE;

	private Handler getdatahanHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			int what = msg.what;
			if (what == INIT) {	
				ArrayList<TextEntity> data = (ArrayList<TextEntity>) msg.obj;
				entities.addAll(0, data);
				adapter.BindData(entities);
				listView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
			}
			if (what == REFRESH) {
				ArrayList<TextEntity> data = (ArrayList<TextEntity>) msg.obj;
				entities.addAll(0, data);
				adapter.BindData(entities);
				listView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				refreshLayoutManager.refreshFinish(PullToRefreshLayout.SUCCEED);
			}

		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		queue = Volley.newRequestQueue(getActivity());

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			lastTime = savedInstanceState.getLong("lastTime");
		}
		View view = inflater.inflate(R.layout.fragment_textlist, container,
				false);
		titleView = view.findViewById(R.id.textlist_title);
		titleView.setOnClickListener(this);
		listView = (ListView) view.findViewById(R.id.text_list_listview);
		header = inflater.inflate(R.layout.textlist_header_tools, listView,
				false);	
		listView.addHeaderView(header);
		((PullToRefreshLayout) view.findViewById(R.id.refresh_view))
				.setOnRefreshListener(this);
		quickPublish = header.findViewById(R.id.quick_tools_punlish);
		quickPublish.setOnClickListener(this);
		quickReview = header.findViewById(R.id.quick_tools_review);
		quickReview.setOnClickListener(this);
		adapter = new EssayAdapter(getActivity());
		if (entities == null) {
			entities = new ArrayList<TextEntity>();
			GetData(INIT);
		}else {
			adapter.BindData(entities);
			listView.setAdapter(adapter);
		}
		listView.setOnScrollListener(this);
		quickTools = view.findViewById(R.id.textlist_quick_tools);
		quickTools.setVisibility(View.INVISIBLE);
		quickPublish = quickTools.findViewById(R.id.quick_tools_punlish);
		quickPublish.setOnClickListener(this);
		quickReview = quickTools.findViewById(R.id.quick_tools_review);
		quickReview.setOnClickListener(this);
		// 新消息的提醒
		textNotify = (TextView) view.findViewById(R.id.textlist_new_notify);
		textNotify.setVisibility(View.INVISIBLE);
		return view;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			int what = msg.what;
			if (what == 1) {
				// what=1 代表有新消息啊
				textNotify.setVisibility(View.INVISIBLE);
			}
		};

	};

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.textlist_title:
			textNotify.setVisibility(View.VISIBLE);
			handler.sendEmptyMessageDelayed(1, 3000);
			break;

		default:
			break;
		}

	}

	// ======================列表滚动显示工具条========================================
	private int lastIndex = 0;
	private View header;
	private ListView listView;

	@Override
	public void onScroll(AbsListView arg0, int firstVisiableItem, int Count,
			int totalCount) {
		// TODO Auto-generated method stub
		int offset = lastIndex - firstVisiableItem;
		if (offset < 0 || firstVisiableItem == 0) {
			// ListView向上滚动
			if (quickTools != null) {
				quickTools.setVisibility(View.INVISIBLE);
			}

		} else if (offset > 0) {
			if (quickTools != null) {
				quickTools.setVisibility(View.VISIBLE);
				/*
				 * if (header.getVisibility() == View.VISIBLE) {
				 * header.setVisibility(View.INVISIBLE); }
				 */
			}

		}
		lastIndex = firstVisiableItem;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub

	}

	/**
	 * 列表网络获取回调部分：这个方法，是在Volly联网响应返回的时候调用 它在主线程执行的
	 * 
	 * @param result
	 *            列表JSON数据字符串 void
	 */
	public void listOnResponse(String result, int state) {
		try {
			JSONObject json = new JSONObject(result);
			// System.out.println("------->" + json.toString());
			// 获得根目录下的Data对象
			JSONObject object = json.getJSONObject("data");
			EntityList entityList = new EntityList();
			entityList.parseJson(object);
			if (entityList.isHasMore()) {
				lastTime = entityList.getMinTime();
				String tip = entityList.getTip();

			} else {
				String tip = entityList.getTip();

			}
			// TODO 把entityList这个段子的数据集合体，传递给Listview之类的Adapter即可显示
			ArrayList<TextEntity> ets = entityList.getEntitys();
			if (ets != null) {
				if (!ets.isEmpty()) {
					// 把oblect添加到指定的location位置，原有Loacation以及以后的内容
					Message msg = Message.obtain();
					msg.what = state;
					msg.obj = ets;
					getdatahanHandler.sendMessage(msg);
				} else {
					// TODO 没有更多数据，需要提醒一下
				}
			} else {
				// TODO 没有解析到数据，可能是网络异常
			}

			// getdatahanHandler.sendEmptyMessageDelayed(REFRESH,3000);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void GetData(final int state) {

		ClientAPI.getList(queue, RequestCategory, 30,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String result) {

						listOnResponse(result, state);
					}
				}, lastTime);
	}

	// 下拉刷新
	@Override
	public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
		refreshLayoutManager = pullToRefreshLayout;
		GetData(REFRESH);
		/*
		 * ClientAPI.getList(queue,RequestCategory,30,new
		 * Response.Listener<String>() {
		 * 
		 * @Override public void onResponse(String result) {
		 * 
		 * listOnResponse(result,REFRESH); } },lastTime);
		 */

	}

	// 上拉加载
	@Override
	public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
		loadLayoutManager = pullToRefreshLayout;

		pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);

	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.adapter = null;
		this.header = null;
		this.quickTools = null;
		this.textNotify = null;
		this.listView = null;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putLong("LongTime", lastTime);
	}
}
// ===========================================================================
