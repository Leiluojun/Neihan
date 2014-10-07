package com.yuhj.neihanduanzi.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.util.Log;

/**
 * 网络工具类
 * @author Davie
 *
 */
public class NetworkUtils {
	private static String path="http://sports.sina.com.cn/nba/";
	
	//下载网络资源的功能方法
	public static byte[] download(String url){
		//字节数组缓存
		ByteArrayBuffer byteArrayBuffer=new ByteArrayBuffer(0);
		HttpClient client=new DefaultHttpClient(); //生成客户端对象
		HttpGet get=new HttpGet(url);  //生成GET请求对象
		try{
			HttpResponse resp=client.execute(get); //客户端执行Get请求，并得到响应
			if(resp.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				//获取响应的数据
				HttpEntity entity=resp.getEntity();
				InputStream is=entity.getContent();
				byte[] buffer=new byte[1024*10];
				int len=-1;
				while((len=is.read(buffer))!=-1){
					byteArrayBuffer.append(buffer, 0, len);
					//Thread.sleep(500);
				}
				is.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return byteArrayBuffer.toByteArray();
	}
	
	public LruCache<String,Bitmap >GetLruCache(Context context)
	{
		
		final int max_size = ((ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE))
				.getMemoryClass();
		final int cacheSize = 1024 * 1024 * max_size / 8;
		LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(cacheSize) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getByteCount();
				// return value.getRowBytes() * value.getHeight();
			}
		};;
		return lruCache;
	}
	
	public static boolean ISConection(){
		HttpClient httpClient = new DefaultHttpClient();
		HttpParams httpParams = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(httpParams,1000);
		HttpConnectionParams.setSoTimeout(httpParams,1000);
		HttpPost httpPost = new HttpPost(path);
		try {
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				String result = EntityUtils.toString(httpResponse.getEntity(),
						"utf-8");
				if (result!=null) {
					return true;
				}
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			if (httpClient != null)
				httpClient.getConnectionManager().shutdown();
		}
		return false;
	}

}
