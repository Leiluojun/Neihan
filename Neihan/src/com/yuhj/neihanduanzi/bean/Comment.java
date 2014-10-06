package com.yuhj.neihanduanzi.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @name Comment
 * @Descripation
 * 评论实体详情
 * @author 禹慧军
 * @email lin5667181@163.com
 * @date 2014-10-5
 * @version 1.0
 */
public class Comment {
	private long Uid;

	private String platform;
	private String text;
	private int digg_count;
	private int userDigg;
	private boolean userVerified;
	private int buryCount;
	private String description;
	private long userId;
	private String userProfileImageUrl;
	private String userName;
	private String userProfileUrl;
	private long id;
	private long createTime;
	private int userBury;

	public void parseJson(JSONObject json) throws JSONException {
		if (json != null) {
			Uid = json.getLong("uid");
			platform = json.getString("platform");
			text = json.getString("text");
			digg_count = json.getInt("digg_count");
			userDigg = json.getInt("user_digg");
			userVerified = json.getBoolean("user_verified");
			buryCount = json.getInt("bury_count");
			description = json.optString("description");
			userId = json.getLong("user_id");
			userProfileImageUrl = json.getString("user_profile_image_url");
			userName = json.getString("user_name");
			userProfileUrl = json.getString("user_profile_url");
			id = json.getLong("id");
			createTime = json.getLong("create_time");
			userBury = json.getInt("user_bury");
		}

	}

	public long getUid() {
		return Uid;
	}

	public String getPlatform() {
		return platform;
	}

	public String getText() {
		return text;
	}

	public int getDigg_count() {
		return digg_count;
	}

	public int getUserDigg() {
		return userDigg;
	}

	public boolean isUserVerified() {
		return userVerified;
	}

	public int getBuryCount() {
		return buryCount;
	}

	public String getDescription() {
		return description;
	}

	public long getUserId() {
		return userId;
	}

	public String getUserProfile() {
		return userProfileImageUrl;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserProfileUrl() {
		return userProfileUrl;
	}

	public long getId() {
		return id;
	}

	public long getCreateTime() {
		return createTime;
	}

	public int getUserBury() {
		return userBury;
	}

}
