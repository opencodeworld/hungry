package com.minisea.cookbook.fragment;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

/*import com.iflytek.voiceads.AdError;
import com.iflytek.voiceads.AdKeys;
import com.iflytek.voiceads.IFLYAdListener;
import com.iflytek.voiceads.IFLYAdSize;
import com.iflytek.voiceads.IFLYFullScreenAd;*/
import com.minisea.cookbook.activity.DianPingWebActivity;
import com.minisea.cookbook.activity.RestaurantDetailActivity;
import com.minisea.cookbook.adapter.HomePageRestaurantAdapter;
import com.minisea.cookbook.entity.RestaurantEntity;
import com.minisea.cookbook.util.RefreshableListView;
import com.minisea.cookbook.util.RefreshableListView.OnRefreshListener;
import com.minisea.cookbook.R;

@SuppressLint("NewApi")
public class HomeFragment extends Fragment implements OnClickListener {
	private View currentView;
	private LinearLayout openMenu;
	private RefreshableListView mListView;
	private HomePageRestaurantAdapter adapter;
	private List<RestaurantEntity> mlist;
	private int total = 21;
	private int step = 7;
	private int add = 7;
	private View listHeaderView;
	private ImageView head_pic;
	//private IFLYFullScreenAd fullScreenAd;

	public void setCurrentViewPararms(FrameLayout.LayoutParams layoutParams) {
		currentView.setLayoutParams(layoutParams);
	}

	public FrameLayout.LayoutParams getCurrentViewParams() {
		return (LayoutParams) currentView.getLayoutParams();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		currentView = inflater.inflate(R.layout.slidingpane_home_layout,
				container, false);
		mListView = (RefreshableListView) currentView
				.findViewById(R.id.mineList);
		openMenu = (LinearLayout) currentView
				.findViewById(R.id.linear_above_toHome);
		listHeaderView = getActivity().getLayoutInflater().inflate(
				R.layout.home_head_view, null);
		head_pic = (ImageView) listHeaderView.findViewById(R.id.iv_home_head);
		openMenu.setOnClickListener(this);
		getDate();
		setListener();
		/*fullScreenAd = IFLYFullScreenAd.createFullScreenAd(getActivity(),
				"0B9E74EF9128FB42DEA76D9409D78A9C");
		IFLYAdListener mAdListener = new IFLYAdListener() {
			@Override
			public void onAdReceive() {
				// 成功接收广告，调用广告展示接口。注意：该接口在回调中才能生效
				fullScreenAd.showAd();
			}

			@Override
			public void onAdFailed(AdError error) {
				// 广告请求失败
				// error.getErrorCode():错误码，error.getErrorDescription()：错误描述
				Log.e("huanry", error.getErrorCode() + ": " + error.getErrorDescription());
			}

			@Override
			public void onAdClick() {
				// 广告被点击
			}

			@Override
			public void onAdClose() {
				// 关闭广告
			}
		};

		fullScreenAd.setParameter(AdKeys.DEBUG_MODE, "false");
		// 设置广告尺寸
		fullScreenAd.setAdSize(IFLYAdSize.FULLSCREEN);
		// 设置全屏广告展示时间，单位为 ms。默认广告展示 5000ms 后消失
		fullScreenAd.setParameter(AdKeys.SHOW_TIME_FULLSCREEN, "6000");
		
		// 添加监听器，请求广告
		fullScreenAd.loadAd(mAdListener);*/

		return currentView;
	}

	public void setListener() {

		mListView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh(RefreshableListView listView) {
				new NewDataTask().execute();
			}
		});

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Intent intent = new Intent(getActivity(),
						RestaurantDetailActivity.class);
				intent.putExtra("name", mlist.get(position + 1).getName());
				startActivity(intent);
			}
		});
		head_pic.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),
						DianPingWebActivity.class);
				startActivity(intent);
			}
		});
	}

	private class NewDataTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			int current = mListView.getAdapter().getCount();
			if (current < total) {
				add += step;
				mListView.removeHeaderView(listHeaderView);
				getDate();
			}

			mListView.completeRefreshing();

			super.onPostExecute(result);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case (R.id.linear_above_toHome):
			openMenu.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					final SlidingPaneLayout slidingPaneLayout = (SlidingPaneLayout) getActivity()
							.findViewById(R.id.slidingpanellayout);
					if (slidingPaneLayout.isOpen()) {
						slidingPaneLayout.closePane();
					} else {
						slidingPaneLayout.openPane();
					}
				}
			});

			break;
		}
	}

	private void getDate() {

		mlist = new ArrayList<RestaurantEntity>();
		RestaurantEntity restaurant1 = new RestaurantEntity();
		restaurant1.setLogo("drawable://" + R.drawable.pic_jigongbao);
		restaurant1.setName("齐鲁兄弟鸡公煲");
		restaurant1.setItem_msg("月售208单 / 20元起送 / 30分钟");
		restaurant1.setRate_numbers(1);
		restaurant1.setIs_rest(true);
		restaurant1.setPromotion("指定食品，每份减4元");
		restaurant1.setIs_half(true);
		restaurant1.setIs_mins(true);
		mlist.add(restaurant1);
		restaurant1 = null;

		RestaurantEntity restaurant2 = new RestaurantEntity();
		restaurant2.setLogo("drawable://" + R.drawable.pic_jixiang);
		restaurant2.setName("吉祥混沌");
		restaurant2.setItem_msg("月售128单 / 14元起送 / 20分钟");
		restaurant2.setPromotion("【新】下单立减3元，份份减3，加赠500ml康师傅果汁！");
		restaurant2.setIs_mins(true);
		restaurant2.setRate_numbers(2);
		mlist.add(restaurant2);
		restaurant2 = null;

		RestaurantEntity restaurant3 = new RestaurantEntity();
		restaurant3.setLogo("drawable://" + R.drawable.pic_milishi);
		restaurant3.setName("迷离士汉堡");
		restaurant3.setItem_msg("月售221单 / 12元起送 / 30分钟");
		restaurant3.setIs_favor(true);
		restaurant3.setRate_numbers(3);
		restaurant3.setPromotion("【新】赠500ml康师傅果汁！");
		restaurant3.setIs_half(true);
		mlist.add(restaurant3);
		restaurant3 = null;

		RestaurantEntity restaurant4 = new RestaurantEntity();
		restaurant4.setLogo("drawable://" + R.drawable.pic_shaxian);
		restaurant4.setName("沙县小吃");
		restaurant4.setItem_msg("月售218单 / 11元起送 / 10分钟");
		restaurant4.setIs_rest(true);
		restaurant4.setRate_numbers(4);
		restaurant4.setPromotion("帅哥给你送餐！");
		restaurant4.setIs_mins(true);
		mlist.add(restaurant4);
		restaurant4 = null;

		RestaurantEntity restaurant5 = new RestaurantEntity();
		restaurant5.setLogo("drawable://" + R.drawable.pic_shiguifan);
		restaurant5.setName("韩式石锅饭");
		restaurant5.setItem_msg("月售82单 / 14元起送 / 22分钟");
		restaurant5.setIs_favor(true);
		restaurant5.setRate_numbers(5);
		restaurant5.setIs_mins(true);
		mlist.add(restaurant5);
		restaurant5 = null;

		RestaurantEntity restaurant6 = new RestaurantEntity();
		restaurant6.setLogo("drawable://" + R.drawable.pic_tengqi);
		restaurant6.setName("藤崎寿司");
		restaurant6.setItem_msg("月售34单 / 11元起送 / 10分钟");
		restaurant6.setRate_numbers(2);
		restaurant6.setIs_mins(true);
		mlist.add(restaurant6);
		restaurant6 = null;

		RestaurantEntity restaurant7 = new RestaurantEntity();
		restaurant7.setLogo("drawable://" + R.drawable.pic_xiaohongmao);
		restaurant7.setName("小红帽快餐厅");
		restaurant7.setItem_msg("月售233单 / 14元起送 / 20分钟");
		restaurant7.setRate_numbers(3);
		restaurant7.setIs_mins(true);
		mlist.add(restaurant7);
		restaurant7 = null;

		adapter = new HomePageRestaurantAdapter(getActivity(), mlist);
		mListView.setAdapter(adapter);
		mListView.addHeaderView(listHeaderView);
	}

}
