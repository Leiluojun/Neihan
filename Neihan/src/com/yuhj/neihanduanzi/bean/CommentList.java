package com.yuhj.neihanduanzi.bean;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.sharesdk.framework.network.j;

import android.R.integer;

/**
 * @name CommentList
 * @Descripation 评论接口返回的data：{}数据部分的实体定义<br/>
 *               包含了top_comments和recent_comments两个数组<br/>
 * @author 禹慧军
 * @email lin5667181@163.com
 * @date 2014-10-5
 * @version 1.0
 */
public class CommentList {
	private ArrayList<Comment> topComments;

	public ArrayList<Comment> getTopComments() {
		return topComments;
	}

	public ArrayList<Comment> getRecentComments() {
		return recentComments;
	}

	private ArrayList<Comment> recentComments;

	private long groupId;

	public long getGroupId() {
		return groupId;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public boolean isHasMore() {
		return hasMore;
	}

	private int totalNumber;
	private boolean hasMore;

	public void parseJson(JSONObject json) throws JSONException {

		if (json != null) {
			JSONObject data = json.getJSONObject("data");
			groupId = json.getLong("group_id");
			totalNumber = json.getInt("total_number");
			hasMore = json.getBoolean("has_more");
			JSONArray topaArray = data.optJSONArray("top_comments");
			JSONArray recentArray = data.optJSONArray("recent_comments");
			if (topaArray != null) {
				topComments = new ArrayList<Comment>();
				int len = topaArray.length();
				if (len > 0) {
					for (int index = 0; index < len; index++) {
						JSONObject object = topaArray.getJSONObject(index);
						Comment comment = new Comment();
						comment.parseJson(object);
						topComments.add(comment);
					}
				}
			}
			if (recentArray != null) {
				recentComments = new ArrayList<Comment>();
				int len = recentArray.length();
				if (len > 0) {
					for (int index = 0; index < len; index++) {
						JSONObject object = recentArray.getJSONObject(index);
						Comment comment = new Comment();
						comment.parseJson(object);
						recentComments.add(comment);
						System.out.println("---->" + comment.getText());
					}
				}
			}
		}
	}
}
