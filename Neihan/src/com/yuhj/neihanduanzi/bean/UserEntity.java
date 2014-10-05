package com.yuhj.neihanduanzi.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @name UserEntity
 * @Descripation 这是用户信息的实体
 * @author 禹慧军
 * @email lin5667181@163.com
 * @date 2014-10-5
 * @version 1.0
 */
public class UserEntity {
	/**
	 * 用户头像Url
	 */
	private String avatarUrl;

	/**
	 * 用户ID
	 */
	private long UserID;

	/**
	 * 用户的昵称
	 */
	private String name;

	/**
	 * 用户是否验证，是否加"V"了
	 */
	private boolean userVerified;

	public void parseJson(JSONObject json) {
		if (json != null) {
			try {
				this.avatarUrl = json.getString("avatar_url");
				this.UserID = json.getLong("user_id");
				this.name = json.getString("name");
				this.userVerified = json.getBoolean("user_verified");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public long getUserID() {
		return UserID;
	}

	public String getName() {
		return name;
	}

	public boolean isUserVerified() {
		return userVerified;
	}
}
