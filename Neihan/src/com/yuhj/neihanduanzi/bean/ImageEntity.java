package com.yuhj.neihanduanzi.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class ImageEntity extends TextEntity {
	/*
	 * private int type; private int comment_count; private long group_id;
	 * private String content;
	 */
	private ImageList largeImagelist;
	private ImageList middleImagelist;

	public void ParseImageItem(JSONObject json) {

		try {
			type = json.getInt("type");

			JSONObject group = json.getJSONObject("group");

			commentCount = group.getInt("comment_count");

			JSONObject largeImage = group.optJSONObject("large_image");

			JSONObject middleImage = group.optJSONObject("middle_image");

			groupId = group.getLong("group_id");

			content = group.getString("cotent");

			largeImagelist = new ImageList();
			if (largeImage != null) {
				largeImagelist.parseJson(largeImage);
			}
			// 小图片的信息
			middleImagelist = new ImageList();
			if (middleImage != null) {
				middleImagelist.parseJson(largeImage);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 大图片的网址
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getComment_count() {
		return commentCount;
	}

	public void setComment_count(int comment_count) {
		this.commentCount = comment_count;
	}

	public long getGroup_id() {
		return groupId;
	}

	public void setGroup_id(long group_id) {
		this.groupId = group_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
