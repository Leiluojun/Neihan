package com.yuhj.neihanduanzi.bean;

import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;

/**
 * @name AdEntity
 * @Descripation 这是广告是实体
 * @author 禹慧军
 * @email lin5667181@163.com
 * @date 2014-10-5
 * @version 1.0
 */
public class AdEntity {
	private int type;

	private long displayTime;
	private long onlineTime;
	private int displayImageHeight;
	private long adId;
	private int displayImagewidth;
	private String source;
	private String packageName;
	private String title;
	private String openUrl;
	private String downloadUrl;
	private String idAd;
	private String displayInfo;
	private String webUrl;
	private String dispalyType;
	private String buttonText;
	private String appleId;
	private String trackUrl;
	private String label;
	private String AdType;
	private long Id;
	private String ipaUrl;
	private String displayImage;

	public void parseJson(JSONObject json) {
		if (json != null) {
			try {
				int type = json.getInt("type");

				displayTime = json.getLong("display_time");
				onlineTime = json.getLong("online_time");
				JSONObject ad = json.getJSONObject("ad");
				displayImageHeight = ad.getInt("display_image_height");
				adId = ad.getLong("ad_id");
				displayImagewidth = ad.getInt("display_image_width");
				source = ad.getString("source");
				packageName = ad.getString("package");
				title = ad.getString("title");
				openUrl = ad.getString("open_url");
				downloadUrl = ad.getString("download_url");
				idAd = ad.getString("is_ad");
				displayInfo = ad.getString("display_info");
				webUrl = ad.getString("web_url");
				dispalyType = ad.getString("display_type");
				buttonText = ad.getString("button_text");
				appleId = ad.getString("appleid");
				trackUrl = ad.getString("track_url");
				label = ad.getString("label");
				AdType = ad.getString("type");
				Id = ad.getLong("id");
				ipaUrl = ad.getString("ipa_url");
				displayImage = ad.getString("display_image");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public int getType() {
		return type;
	}

	public long getDisplayTime() {
		return displayTime;
	}

	public long getOnlineTime() {
		return onlineTime;
	}

	public int getDisplayImageHeight() {
		return displayImageHeight;
	}

	public long getAdId() {
		return adId;
	}

	public int getDisplayImagewidth() {
		return displayImagewidth;
	}

	public String getSource() {
		return source;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getTitle() {
		return title;
	}

	public String getOpenUrl() {
		return openUrl;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public String getIdAd() {
		return idAd;
	}

	public String getDisplayInfo() {
		return displayInfo;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public String getDispalyType() {
		return dispalyType;
	}

	public String getButtonText() {
		return buttonText;
	}

	public String getAppleId() {
		return appleId;
	}

	public String getTrackUrl() {
		return trackUrl;
	}

	public String getLabel() {
		return label;
	}

	public String getAdType() {
		return AdType;
	}

	public long getId() {
		return Id;
	}

	public String getIpaUrl() {
		return ipaUrl;
	}

	public String getDisplayImage() {
		return displayImage;
	}

}
