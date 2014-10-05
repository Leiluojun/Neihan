package com.yuhj.neihanduanzi.bean;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageList {
	private String uri;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public ArrayList<String> getLargeImgeurls() {
		return largeImgeurls;
	}

	public void setLargeImgeurls(ArrayList<String> largeImgeurls) {
		this.largeImgeurls = largeImgeurls;
	}

	private String width;
	private String height;
	private ArrayList<String> largeImgeurls;

	public void parseJson(JSONObject jsonObject) {
		try {
			largeImgeurls = ParseImageUrlList(jsonObject);
			uri = jsonObject.getString("uri");
			width = jsonObject.getString("width");
			height = jsonObject.getString("height");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private ArrayList<String> ParseImageUrlList(JSONObject Image)
			throws JSONException {
		JSONArray Image_urls = Image.getJSONArray("url_list");
		int ulen = Image_urls.length();
		ArrayList<String> ImageList = new ArrayList<String>();
		for (int j = 0; j < ulen; j++) {
			JSONObject uObject = Image_urls.getJSONObject(j);
			String url = uObject.getString("url");
			ImageList.add(url);
		}
		return ImageList;
	}

}
