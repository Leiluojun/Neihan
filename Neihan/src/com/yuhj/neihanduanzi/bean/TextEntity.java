package com.yuhj.neihanduanzi.bean;

import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;

/**
 * @name TextEntity
 * @Descripation 文本段子的实体
 * @author 禹慧军
 * @email lin5667181@163.com
 * @date 2014-10-5
 * @version 1.0
 */

public class TextEntity {
	/**
	 * 类型
	 */
	protected int type;

	/**
	 * 创建时间
	 */
	private long createtime;
	/**
	 * 上线时间
	 */
	private long onlineTime;

	/**
	 * 显示时间
	 */
	private long displayTime;
	/**
	 * digg的过个数
	 */
	private int diggCount;
	/**
	 * 状态，其中的可选值3待分析是社么类型？
	 */
	private int status;

	protected int userDigg;

	/**
	 * 段子的ID,访问详情和评论时作为接口的参数
	 */

	protected long groupId;
	/**
	 * 内容分类类型:1.文本;2.图片
	 */
	private int categoryId;
	protected String content;

	/**
	 * 代表评论的个数
	 */
	protected int commentCount;
	/**
	 * 代表用户是否Repin
	 */
	private int userRepin;

	/**
	 * 代表当前用户是否踩了，0代表没有，1代表踩了
	 */
	private int userBury;
	/**
	 * 代表当前用户是否赞了，0代表没有，1代码赞了
	 */
	private int userFavorite;

	/**
	 * 代表踩得个数
	 */
	/**
	 * 代表攒的个数
	 */
	private int favoriteCount;

	private int buryCount;

	/**
	 * 代表用于第三方分享，提交的网址参数
	 */
	private String shareUrl;

	// TODO 分析这个字段的含义
	private int label;

	/**
	 * 状态的描述信息<br/>
	 * 可选值：<br/>
	 */
	private String statusDesc;

	/**
	 * 当前用户是否评论
	 */
	private int hasCount;

	/**
	 * 需要分析这个字段的含义
	 */
	private int goDetailCount;

	private int hasComments;

	/**
	 *
	 */
	// TODO 需要去了解这个字段的含义

	/**
	 * 级别，层次 TODO 这个需要分析一下是什么含义,现在有两个地方出现 1.获取接口列表的url里有一个level=6;
	 * 2.文本段子的实体中，level=4;
	 */
	private int level;

	/**
	 * TODO 分析它的含义
	 */
	private int repinCount;

	/**
	 * 是否含有热门评论
	 */
	private int hasHotComments;

	// private String[] comments;

	// TODO 需要去分析comments这个json数组中的内容是什么？

	private UserEntity user;
	public void parseJson(JSONObject json) {
		if (json != null) {
			try {
				this.onlineTime = json.getLong("online_time");
				this.displayTime = json.getLong("display_time");
				JSONObject group = json.getJSONObject("group");

				this.createtime = group.getLong("create_time");
				this.favoriteCount = group.getInt("favorite_count");
				this.userBury = group.getInt("user_bury");
				this.userFavorite = group.getInt("favorite_count");
				this.buryCount = group.getInt("bury_count");
				this.shareUrl = group.getString("share_url");
				this.label = group.optInt("label",0);
				this.content = group.getString("content");
				this.commentCount = group.getInt("comment_count");
				this.status = group.getInt("status");
				this.hasComments = group.getInt("has_comments");
				this.goDetailCount = group.getInt("go_detail_count");
				this.statusDesc = group.getString("status_desc");
				JSONObject userobj = group.getJSONObject("user");
				this.user = new UserEntity();
				user.parseJson(userobj);
				this.userDigg = group.getInt("user_digg");
				this.groupId = group.getLong("group_id");
				this.level = group.getInt("level");
				this.repinCount = group.getInt("repin_count");
				this.diggCount = group.getInt("digg_count");
				this.hasHotComments = group.optInt("has_hot_comments",0);
				this.userRepin = group.getInt("user_repin");
				this.categoryId = group.getInt("category_id");
				this.type = group.getInt("type");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(long createtime) {
		this.createtime = createtime;
	}

	public int getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	public int getUserBury() {
		return userBury;
	}

	public void setUserBury(int userBury) {
		this.userBury = userBury;
	}

	public int getUserFavorite() {
		return userFavorite;
	}

	public void setUserFavorite(int userFavorite) {
		this.userFavorite = userFavorite;
	}

	public int getBuryCount() {
		return buryCount;
	}

	public void setBuryCount(int buryCount) {
		this.buryCount = buryCount;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public int getHasCount() {
		return hasCount;
	}

	public void setHasCount(int hasCount) {
		this.hasCount = hasCount;
	}

	public int getGoDetailCount() {
		return goDetailCount;
	}

	public void setGoDetailCount(int goDetailCount) {
		this.goDetailCount = goDetailCount;
	}

	public int getHasComments() {
		return hasComments;
	}

	public void setHasComments(int hasComments) {
		this.hasComments = hasComments;
	}

	public int getUserDigg() {
		return userDigg;
	}

	public void setUserDigg(int userDigg) {
		this.userDigg = userDigg;
	}

	public int getDiggCount() {
		return diggCount;
	}

	public void setDiggCount(int diggCount) {
		this.diggCount = diggCount;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getRepinCount() {
		return repinCount;
	}

	public void setRepinCount(int repinCount) {
		this.repinCount = repinCount;
	}

	public int getUserRepin() {
		return userRepin;
	}

	public void setUserRepin(int userRepin) {
		this.userRepin = userRepin;
	}

	public int getHasHotComments() {
		return hasHotComments;
	}

	public void setHasHotComments(int hasHotComments) {
		this.hasHotComments = hasHotComments;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public long getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(long onlineTime) {
		this.onlineTime = onlineTime;
	}

	public long getDisplayTime() {
		return displayTime;
	}

	public void setDisplayTime(long displayTime) {
		this.displayTime = displayTime;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
