package com.yuhj.neihanduanzi.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class ImageEntity {
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getComment_count() {
		return comment_count;
	}

	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}

	public long getGroup_id() {
		return group_id;
	}

	public void setGroup_id(long group_id) {
		this.group_id = group_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	private int type;
	private int comment_count;
	private long group_id;
	private String content;

	public void ParseImageItem(JSONObject json){
	
		try {
			type = json.getInt("type");
			
			JSONObject group = json.getJSONObject("group");
			
			comment_count = group.getInt("comment_count");
			
			JSONObject largeImage = group.getJSONObject("large_image");
			
			JSONObject middleImage = group
					.getJSONObject("middle_image");
			
			group_id = group.getLong("group_id");
			
			content = group.getString("cotent");
			
			ImageList largeImagelist = new ImageList();
			
			largeImagelist.parseJson(largeImage);
			//小图片的信息
			ImageList middleImagelist = new ImageList();
			
			middleImagelist.parseJson(largeImage);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 大图片的网址
		
	}

}
