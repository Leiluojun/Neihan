package com.yuhj.neihanduanzi.bean;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @name EntityList
 * @Descripation 内涵段子的实体
 * @author 禹慧军
 * @email lin5667181@163.com
 * @date 2014-10-5
 * @version 1.0
 */
public class EntityList {
	private boolean hasMore;
	private long minTime;
	private String tip;
	private long maxTime;
	private ArrayList<TextEntity> entitys;

	

	public void parseJson(JSONObject json) throws JSONException {
		if (json != null) {
			hasMore = json.getBoolean("has_more");
			if (hasMore==false) {
				minTime = json.optLong("min_time");
			}
			
			tip = json.optString("tip");
			maxTime = json.optLong("max_time");
			// 从data对象中，获取名称为data的数组
			JSONArray array = json.getJSONArray("data");
			int len = array.length();
			if (len > 0) {
				entitys = new ArrayList<TextEntity>();
				// 遍历数组中每一条段子的信息
				for (int i = 0; i < len; i++) {
					JSONObject item = array.getJSONObject(i);
					int type = item.getInt("type");
					if (type == 5) {
						// TODO 处理广告内容
						AdEntity entity = new AdEntity();
						entity.parseJson(item);
						System.out.println("---->" + entity.getDownloadUrl());
					} else if (type == 1) {
						JSONObject group = item.getJSONObject("group");
						int cid = group.getInt("category_id");
						TextEntity Entity = null;
						if (cid == 1) {
							// TODO 解析文本段子
							Entity = new TextEntity();
						} else if (cid == 2) {
							// TODO解析图片段子
							Entity = new ImageEntity();
						}
						Entity.parseJson(item);
						entitys.add(Entity);
						System.out.println("---->" + Entity.getGroupId());
					}
					
				}
			}
		}

	}

	public boolean isHasMore() {
		return hasMore;
	}

	public Long getMinTime() {
		return minTime;
	}

	public String getTip() {
		return tip;
	}

	public long getMaxTime() {
		return maxTime;
	}
	public ArrayList<TextEntity> getEntitys() {
		return entitys;
	}

}
