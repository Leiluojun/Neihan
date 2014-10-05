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
	private int type;

	/**
	 * 创建时间
	 */
	private long createtime;
	/**
	 * 代表攒的个数
	 */
	private int favoriteCount;

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
	private int buryCount;

	/**
	 * 代表用于第三方分享，提交的网址参数
	 */
	private String shareUrl;

	// TODO 分析这个字段的含义
	private int label;

	/**
	 * 文本段子的内容部分（完整部分）
	 */
	private String content;

	/**
	 * 代表评论的个数
	 */
	private int commentCount;

	/**
	 * 状态，其中的可选值3待分析是社么类型？
	 */
	private int status;

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
	private int userDigg;

	/**
	 * digg的过个数
	 */
	private int diggCount;
	/**
	 * 段子的ID,访问详情和评论时作为接口的参数
	 */

	private long groupId;

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
	 * 代表用户是否Repin
	 */
	private int userRepin;

	/**
	 * 是否含有热门评论
	 */
	private int hasHotComments;

	/**
	 * 内容分类类型:1.文本;2.图片
	 */
	private int categoryId;

	// private String[] comments;

	// TODO 需要去分析comments这个json数组中的内容是什么？
	/**
	 * 上线时间
	 */
	private long onlineTime;
	/**
	 * 显示时间
	 */
	private long displayTime;

	private UserEntity user;

	/**
	 * { "online_time": 1411878957, "display_time": 1411878957, "group": {
	 * "create_time": 1411718218.0, "favorite_count": 1209, "user_bury": 0,
	 * "user_favorite": 0, "bury_count": 1516, "share_url":
	 * "http://toutiao.com/group/3560859160/?iid=2337593504&app=joke_essay",
	 * "label": 1, "content":
	 * "甲:昨天碰到抢劫的，被打了两顿。乙:为啥啊？甲:他问我有钱吗我说没有，他从我身上搜出一包软中华然后就被打了一顿。等他走了，不一会儿又回来打了我一顿，因为他发现里面塞的是白红梅，劫匪走时还留下一句‘没钱还装B’"
	 * , "comment_count": 177, "status": 3, "has_comments": 0,
	 * "go_detail_count": 4370, "status_desc": "已发表到热门列表", "user": {
	 * "avatar_url": "http://p1.pstatp.com/thumb/1367/2213311454", "user_id":
	 * 3080520868, "name": "请叫我梓安哥", "user_verified": false }, "user_digg": 0,
	 * "group_id": 3560859160, "level": 4, "repin_count": 1209, "digg_count":
	 * 18424, "has_hot_comments": 1, "user_repin": 0, "category_id": 1 },
	 * "comments": [], "type": 1 }
	 * */
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
				this.label = group.getInt("label");
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
				this.hasHotComments = group.getInt("has_hot_comments");
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
