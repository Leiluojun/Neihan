package com.yuhj.neihanduanzi.adapter;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

import com.yuhj.neihanduanzi.R;
import com.yuhj.neihanduanzi.bean.TextEntity;
import com.yuhj.neihanduanzi.bean.UserEntity;

import android.R.integer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @name EssayAdapter
 * @Descripation 文本段子的适配器
 * @author 禹慧军
 * @email lin5667181@163.com
 * @date 2014-10-7
 * @version 1.0
 */
public class EssayAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<TextEntity> entities;

	public void BindData(ArrayList<TextEntity> entities) {
		this.entities = entities;
	}

	public EssayAdapter(Context context) {
		this.context = context;
		//this.entities = entities;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return entities.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return entities.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		View ret = convertView;
		if (convertView == null) {
			ret = LayoutInflater.from(context)
					.inflate(R.layout.itemessay, null);

		}
		if (ret != null) {
			ViewHolder viewHolder = (ViewHolder) ret.getTag();
			if (viewHolder == null) {
				viewHolder=new ViewHolder();
				viewHolder.bunGifPlay = (TextView) ret
						.findViewById(R.id.btnGifplay);
				viewHolder.imgbtnShare = (ImageButton) ret
						.findViewById(R.id.item_share);
				viewHolder.chbBuryCount = (CheckBox) ret
						.findViewById(R.id.item_bury_count);
				viewHolder.chbDiggCount = (CheckBox) ret
						.findViewById(R.id.item_digg_count);
				viewHolder.gifImageView = (GifImageView) ret
						.findViewById(R.id.gifImageView);
				viewHolder.profileImage = (ImageView) ret
						.findViewById(R.id.item_profile_image);
				viewHolder.chbCommentCount = (CheckBox) ret
						.findViewById(R.id.item_comment_count);
				viewHolder.content = (TextView) ret
						.findViewById(R.id.item_content);
				viewHolder.profileNickName = (TextView) ret
						.findViewById(R.id.item_profile_nickname);
				viewHolder.DownloadprogressBar = (ProgressBar) ret
						.findViewById(R.id.item_image_download_progress);
				ret.setTag(viewHolder);
			}

			TextEntity entity = entities.get(position);
			// 1.先设置文本的内容
			UserEntity user = entity.getUser();
			String nick = "匿名用户";
			if (user != null) {
				nick = user.getName();
			}
			viewHolder.profileNickName.setText(nick);
			String content = entity.getContent();
			viewHolder.content.setText(content);
			int diggCount = entity.getDiggCount();
			viewHolder.chbDiggCount.setText(diggCount + "");
			int userDigg = entity.getUserDigg();// 当前用户是否赞过
			// userDigg=1代表已经赞过，那么chbDiggCount必须赞过
			viewHolder.chbDiggCount.setEnabled(userDigg != 1);
			// userBury=1代表已经赞过，那么chbBuryCount必须赞过
			int buryCount = entity.getBuryCount();
			viewHolder.chbBuryCount.setText(buryCount + "");
			int userBury = entity.getUserBury();
			viewHolder.chbBuryCount.setEnabled(userBury != 1);

			int commentCount = entity.getCommentCount();
			viewHolder.chbCommentCount.setText(commentCount+"");

			// 2.再设置图片的内容

			// TODO需要加载各种网络数据
		}
		return ret;
	}

	private class ViewHolder {

		/**
		 * 头像
		 */
		public ImageView profileImage;

		/**
		 * 昵称
		 */
		public TextView profileNickName;

		/**
		 * 文本内容
		 */
		public TextView content;

		/**
		 * 下载进度
		 */
		public ProgressBar DownloadprogressBar;

		/**
		 * gif动画
		 */
		public GifImageView gifImageView;

		/**
		 * 开始播放
		 */
		public TextView bunGifPlay;

		/**
		 * 赞的个数
		 */
		public CheckBox chbDiggCount;

		/**
		 * 踩得个数
		 */
		public CheckBox chbBuryCount;

		/**
		 * 评论的个数
		 */
		public CheckBox chbCommentCount;

		/**
		 * 分享
		 */
		public ImageButton imgbtnShare;
	}

}
