package com.dgg.hdforeman.mvp.ui.widget.popwindow;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.dgg.hdforeman.R;

import java.util.ArrayList;
import java.util.Collections;

@SuppressLint({ "ViewConstructor", "InflateParams" })
public class HotalkMenuView extends View {
	/**
	 * 回到首页
	 */
	public final static int MENU_SEND_MSG_FORMULAR = 0;

	/**
	 * 消息
	 */
	public final static int MENU_VIEW_CONTACT = 1;

	/**
	 * 专业咨询师
	 */
	public final static int MENU_ADD_CONTACT = 2;

	/**
	 * 专家工作室
	 */
	public final static int MENU_MEMBER_MANAGER = 3;

	/**
	 * 分享
	 */
	public final static int MENU_ADD_TO_FAVORITES = 4;

	/**
	 * 查看课程
	 */
	public final static int MENU_DELETE_MULTI_MSG = 5;
	
	/**
	 * 记录上一次pop弹出方向
	 */
	public static int oldnum;

	/**
	 * 上下文
	 */
	private Context mContext;

	/**
	 * PopupWindow
	 */
	private PopupWindow popWindow;

	/**
	 * 显示View
	 */
	private View popview;

	/**
	 * listview
	 */
	public ListView listview;

	/**
	 * 填充数据集
	 */
	public ArrayList<MenuItem> mitems = null;

	/**
	 * 设置显示位置
	 */
	RelativeLayout layout;

	/**
	 * 横屏菜单距离最大高度
	 */
	private int bottomLenght_h = 77;

	/**
	 * 竖屏菜单距离最大高度
	 */
	private int bottomLenght_v = 173;

	/**
	 * 屏幕密度
	 */
	private Display display;

	/**
	 * 画板用于测字符宽度
	 */
	private Paint paint = null;

	/**
	 * 菜单中最在的字符宽度
	 */
	private float maxWeith = 0;

	/**
	 * 显示靠左边菜单的左边距
	 */
	private int rightMenuLeft = 45;

	/**
	 * 显示靠左边菜单的最大右边距
	 */
	private int maxRightWeith = 44;

	/**
	 * 显示靠左边菜单的最小右边距
	 */
	private int minRightWeith = 140;

	/**
	 * 菜单的最小左边距
	 */
	private int maxLeftWeith = 88;

	/**
	 * 菜单的最小左边距
	 */
	private int minLeftWeith = 188;

	/**
	 * 横屏菜单最小左边距
	 */
	private int maxLeftWeith_h = 282;

	/**
	 * 横屏菜单最小左边距
	 */
	private int minLeftWeith_h = 371;

	/**
	 * 菜单的背景和文字两边占间距
	 */
	private int contentSpaceWeith = 38;

	/**
	 * 打开Menu动画
	 */
	private TranslateAnimation myMenuOpen;

	/**
	 * 关闭Menu动画
	 */
	private TranslateAnimation myMenuClose;

	/**
	 * 打开Menu动画所用时间
	 */
	private int menuOpenMillis = 500;

	/**
	 * 关闭Menu动画 所用时间
	 */
	private int menuCloseMillis = 200;

	/**
	 * 弹出窗口播放Menu的动画
	 */
	private final int MENU_OPEN_ANIM = 1;

	/**
	 * 关闭Menu动画 后关闭窗口
	 */
	private final int MENU_CLOSE_ANIM = 2;

	/**
	 * 当前窗口handler
	 */
	private MyHandler myHandler = new MyHandler();

	/**
	 * 正在进行关闭Menu操作
	 */
	private boolean isDismissing = false;
	
	/**
	 * 屏幕宽和高
	 */
//	private int width=0,height=0;
	
	private int num;

	@SuppressLint("HandlerLeak")
	class MyHandler extends Handler {
		@SuppressLint("HandlerLeak")
		@Override
		public void handleMessage(Message msg) {
			if (msg == null) {
				return;
			}
			super.handleMessage(msg);
			switch (msg.what) {
			case MENU_OPEN_ANIM:
				startMenuOpenAnimation();
				break;
			case MENU_CLOSE_ANIM:
				if (popWindow != null) {
					if(popWindow.isShowing()){
						if(mContext instanceof Activity){
							if(Build.VERSION.SDK_INT>=17){
								if(!((Activity) mContext).isFinishing() && !((Activity) mContext).isDestroyed()){
									popWindow.dismiss();
								}
							}else{
								popWindow.dismiss();
							}
						}
					}
//					popWindow=null;
				}
				isDismissing = false;
				break;
			}
		}
	}

//	/**
//	 * 获取手机屏幕宽高
//	 */
//	public void getWindow(){
////		WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
////		width = wm.getDefaultDisplay().getWidth();
////		height = wm.getDefaultDisplay().getHeight();
//	}
	
	@SuppressLint("InflateParams")
	@SuppressWarnings({ "static-access", "deprecation" })
	public HotalkMenuView(Context context,int num,ArrayList<Integer> listImage,int orientation) {
		super(context);
		mContext = context;
		this.num=num;
		mitems = new ArrayList<MenuItem>();
		LayoutInflater layoutInflater = (LayoutInflater) (mContext).getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
		// 获取自定义布局文件的视图
		switch (num) {
		case 1:
			popview = layoutInflater.inflate(R.layout.hotalk_menu_bottom_view, null);
			adapter = new ItemTextListAdapter(mContext);
			break;
		case 2:
			popview = layoutInflater.inflate(R.layout.hotalk_menu_left_view, null);
			adapter = new ItemTextListAdapter(mContext);
			break;
		case 3:
			popview = layoutInflater.inflate(R.layout.hotalk_menu_right_view, null);
			if(orientation==4){
				popview = layoutInflater.inflate(R.layout.hotalk_menu_right_view2, null);
			}
			adapter = new ItemTextListAdapter(mContext,listImage);
			break;
//		case 4:
//			popview = layoutInflater.inflate(R.layout.hotalk_menu_top_view, null);
//			break;
		}
		listview = (ListView) popview.findViewById(R.id.hotalk_menu_listview);
		layout = (RelativeLayout) popview.findViewById(R.id.hotalk_menu_view_layout);
		//设置pop背景色
		TextView bg=(TextView) popview.findViewById(R.id.bg);
		bg.setBackgroundColor(Color.argb(100, 100, 100, 100));
//		adapter = new ItemTextListAdapter(mContext,listImage);
		popWindow = new PopupWindow(popview, LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);// 以PopupWindow弹出
		display = ((WindowManager) mContext.getSystemService(mContext.WINDOW_SERVICE)).getDefaultDisplay();
		initValue();
		layout.setOnClickListener(onclick);
		listview.setFocusable(false);
		listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		listview.setAdapter(adapter);
		listview.setFocusableInTouchMode(true);
		listview.setMinimumHeight(200);
		listview.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// 正执行关闭Menu菜单操作将不重复操作
				if (!isDismissing) {
					isDismissing = true;
					if ((keyCode == KeyEvent.KEYCODE_MENU)
							&& (popWindow.isShowing())) {
						close();
					} else if (((keyCode == KeyEvent.KEYCODE_BACK) && (popWindow.isShowing()))) {
						close();
					}
				}
				return false;
			}
		});
	}

	/**
	 * 初始设置Menu位置参数
	 */
	private void initValue() {
		paint = new Paint();
		rightMenuLeft = (int) (rightMenuLeft * FusionField.currentDensity);
		maxLeftWeith = (int) (maxLeftWeith * FusionField.currentDensity);
		minLeftWeith = (int) (minLeftWeith * FusionField.currentDensity);
		maxRightWeith = (int) (maxRightWeith * FusionField.currentDensity);
		minRightWeith = (int) (minRightWeith * FusionField.currentDensity);
		bottomLenght_h = (int) (bottomLenght_h * FusionField.currentDensity);
		bottomLenght_v = (int) (bottomLenght_v * FusionField.currentDensity);
		contentSpaceWeith = (int) (contentSpaceWeith * FusionField.currentDensity);
		maxLeftWeith_h = (int) (maxLeftWeith_h * FusionField.currentDensity);
		minLeftWeith_h = (int) (minLeftWeith_h * FusionField.currentDensity);
	}

	/**
	 * 添加菜单项
	 * 
	 * @param key
	 * @param value
	 */
	public void add(int key, String value) {
		remove(key);
		MenuItem item = new MenuItem(key, value);
		mitems.add(item);
		adapter.notifyDataSetChanged();

	}

	/**
	 * 添加 菜单项
	 * 
	 * @param position
	 *            位置(必须是按0~n)添加
	 * @param key
	 * @param value
	 */
	public void add(int position, int key, String value) {
		MenuItem item = new MenuItem(key, value);
		if (position == mitems.size()) {
			mitems.add(position, item);

			adapter.notifyDataSetChanged();
		}
	}

	/**
	 * 获取文本最大长度
	 */
	private void getContextMaxLength() {
		adapter.notifyDataSetChanged();
		if (mitems != null && mitems.size() > 0) {
			maxWeith = 0;
			if (Build.VERSION.SDK_INT >= 14) {
				// android 4.0手机当前字体大小自适
				TextView tv = new TextView(mContext);
				tv.setTextSize(FusionField.SET_TYPE_TEXT_SIZE);
				float size = tv.getTextSize() / FusionField.SET_TYPE_TEXT_SIZE;
				paint.setTextSize((int) ((size * 12) * FusionField.currentDensity));
			} else {
				paint.setTextSize((int) ((FusionField.SET_TYPE_TEXT_SIZE + 1) * FusionField.currentDensity));
			}

			for (int i = 0; i < mitems.size(); i++) {
				if (paint.measureText(mitems.get(i).MenuValue) > maxWeith) {
					maxWeith = paint.measureText(mitems.get(i).MenuValue);
				}
			}
		}
	}

	/**
	 * 更新菜单项按位置更新
	 * 
	 * @param position
	 * @param key
	 * @param value
	 */
	public void updateItem(int position, int key, String value) {
		if (mitems.size() > position) {
			remover(position);
			MenuItem item = new MenuItem(key, value);
			mitems.add(position, item);
		}
	}

	/**
	 * 删除菜单项
	 * 
	 * @param position
	 */
	public void remover(int position) {
		if (mitems.size() > position) {
			mitems.remove(position);
		}
	}

	/**
	 * 删除菜单项 add by shaxinajun
	 * 
	 * @param key
	 */
	public void remove(int key) {
		MenuItem item = null;
		for (int i = 0; i < mitems.size(); i++) {
			item = mitems.get(i);
			if (item.MenuKey == key) {
				mitems.remove(i);
				break;
			}
		}
	}

	/**
	 * 清空菜单项
	 */
	public void clear() {
		mitems.clear();
		maxWeith = 0;
	}

	ItemTextListAdapter adapter;

	/**
	 * 设置Menu显示位置，不可以自适应屏幕 密度 方法表述
	 * 
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 *            void
	 */
	public void setMenuPosition(int left, int top, int right, int bottom) {
		layout.setPadding(left, (int) (46 * FusionField.currentDensity), right,
				bottom);
	}

	/**
	 * 单击事件
	 */
	private OnClickListener onclick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			// 点击空白外让其消失
			case R.id.hotalk_menu_view_layout:
				close();
				break;
			default:
				break;
			}
		}
	};

	/**
	 * 重新设置菜单项
	 * 
	 * @param items
	 */
	public void setItems(ArrayList<String> items) {
		mitems.clear();
		if (items != null && items.size() > 0) {
			for (int i = 0; i < items.size(); i++) {
				MenuItem item = new MenuItem(0, items.get(i));
				mitems.add(item);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void setPositionShow() {
		try {
			if (popWindow != null && popview != null) {
				if (popWindow.isShowing()) {
					startMenuCloseAnimation();
				} else {
					getContextMaxLength();
					int right = (int) ((320 * FusionField.currentDensity) - (maxWeith
							+ rightMenuLeft + contentSpaceWeith));

					if (right < maxRightWeith) {
						right = maxRightWeith;
					} else if (right > minRightWeith) {
						right = minRightWeith;
					}
					// setMenuPosition(rightMenuLeft, 10, right,
					// bottomLenght_v);
					setMenuPosition(rightMenuLeft, 0, 0, bottomLenght_v);
					Collections.sort(mitems);
					// 规定弹窗的位置
					popWindow.setFocusable(true);
					popWindow.update();
					popWindow.showAtLocation(popview, Gravity.FILL, 0, 0);
					myHandler.sendEmptyMessage(MENU_OPEN_ANIM);
				}
			}
		} catch (Exception e) {
			Log.i("HotalkMenuView", "e:" + e.toString());
			close();
		}
	}

	/**
	 * 显示
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	public void show() {
		try {
			if (popWindow != null && popview != null) {
				if (popWindow.isShowing()) {
					startMenuCloseAnimation();
				} else {
					if (mitems != null && mitems.size() > 0) {
						@SuppressWarnings("deprecation")
						int orientation = display.getOrientation();
						if (orientation == 0) {// 竖屏
							if (FusionField.currentActivity != null
									&& FusionField.currentActivity
											.getCurrentFocus() != null
									&& FusionField.currentActivity
											.getCurrentFocus().getWindowToken() != null) {
								((InputMethodManager) FusionField.currentActivity
										.getSystemService(FusionField.currentActivity.INPUT_METHOD_SERVICE))
										.hideSoftInputFromWindow(
												FusionField.currentActivity
														.getCurrentFocus()
														.getWindowToken(), 0);
							}
							getContextMaxLength();
							int left = (int) ((320 * FusionField.currentDensity) - (maxWeith + contentSpaceWeith));
							if (left < maxLeftWeith) {
								left = maxLeftWeith;
							} else if (left > minLeftWeith) {
								left = minLeftWeith;
							}
							setMenuPosition(left, 0, 0, bottomLenght_v);

						} else
						// 横屏
						{
							getContextMaxLength();
							int left = (int) ((533 * FusionField.currentDensity) - (maxWeith + contentSpaceWeith));
							Log.i("jindegege", "left:" + left
									+ " rightMenuLeft:"
									+ (480 * FusionField.currentDensity));
							if (left < maxLeftWeith_h) {
								left = maxLeftWeith_h;
							} else if (left > minLeftWeith_h) {
								left = minLeftWeith_h;
							}
							setMenuPosition(left, 0, 0, bottomLenght_h);
						}
						Collections.sort(mitems);
						// 规定弹窗的位置
						popWindow.setFocusable(true);
						popWindow.update();
						popWindow.showAtLocation(listview, Gravity.FILL, 0,(int) (46 * FusionField.currentDensity));
						myHandler.sendEmptyMessage(MENU_OPEN_ANIM);
					}
				}
			}
		} catch (Exception e) {
			Log.i("HotalkMenuView", "show() e:" + e.toString());
			close();
		}
	}

	/**
	 * 判读是否显示
	 * 
	 * @return boolean
	 */
	public boolean getIsShow() {
		return popWindow.isShowing();
	}

	/**
	 * 关闭
	 */
	public void close() {
		if (popWindow != null && popWindow.isShowing()) {
			startMenuCloseAnimation();
		}
	}

	/**
	 * 打开menu菜单窗口动画
	 */
	private void startMenuOpenAnimation() {
		// 由于打开菜单高度不一至所以根据菜单的高度来设置打开菜单时间
		menuOpenMillis = (mitems.size() * 100) + 100;
		if (menuOpenMillis > 500) {
			menuOpenMillis = 500;
		}
		switch (num) {
		case 1:
			myMenuOpen = new TranslateAnimation(0f, 0f, (listview.getHeight() + 1), 0f);
			break;
		case 2:
			myMenuOpen = new TranslateAnimation(-(listview.getWidth()+1), 0f, 0f, 0f);
			break;
		case 3:
			myMenuOpen = new TranslateAnimation((listview.getWidth() + 1), 0f, 0f, 0f);
			break;
		case 4:
			myMenuOpen = new TranslateAnimation(0f, 0f, -(listview.getHeight() + 1), 0f);
			break;
		}
		
		myMenuOpen.setDuration(menuOpenMillis);
		listview.startAnimation(myMenuOpen);
	}

	/**
	 * 关闭menu菜单窗口动画
	 */
	private void startMenuCloseAnimation() {
		switch (num) {
		case 1:
			myMenuClose = new TranslateAnimation(0f, 0f, 0f, (listview.getHeight() + 1));
			break;
		case 2:
			myMenuClose = new TranslateAnimation(0f, -(listview.getWidth()+1), 0f, 0f);
			break;
		case 3:
			myMenuClose = new TranslateAnimation(0f, (listview.getWidth() + 1), 0f, 0f);
			break;
		case 4:
			myMenuClose = new TranslateAnimation(0f, 0f, 0f, -(listview.getHeight() + 1));
			break;
		}
		myMenuClose.setDuration(menuCloseMillis);
		listview.startAnimation(myMenuClose);
		myHandler.sendEmptyMessageDelayed(MENU_CLOSE_ANIM, menuCloseMillis);
//		myHandler.sendEmptyMessage(MENU_CLOSE_ANIM);
	}
	
	public class ItemTextListAdapter extends SimpleAdapter {
		private ArrayList<Integer> listImage;
		public ItemTextListAdapter(Context context,ArrayList<Integer> listImage) {
			super(context, null, 0, null, null);
			this.listImage=listImage;
		}
		
		public ItemTextListAdapter(Context context) {
			super(context, null, 0, null, null);
		}

		@Override
		public int getCount() {
			return mitems.size();
		}
		
		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			ItemHolder holder;
			if (convertView == null || convertView.getTag() == null
					|| !(convertView.getTag() instanceof ItemHolder)|| oldnum!=num) {
//				if(num==1||num==4){
//					convertView = LayoutInflater.from(mContext).inflate(R.layout.hotalk_menu_item_vertical_view, null, true);
//				}else if(num==2||num==3||num==5){
					convertView = LayoutInflater.from(mContext).inflate(R.layout.hotalk_menu_item_hor_view, null, true);
//				}
 				oldnum=num;
				holder = new ItemHolder();
				holder.imageView=(ImageView) convertView.findViewById(R.id.menu_item_imageView);
				holder.menuName = (TextView) convertView.findViewById(R.id.menu_item_textview);
				convertView.setTag(holder);
			} else {
				holder = (ItemHolder) convertView.getTag();
			}
//			convertView.setTag(holder);
//			convertView.setTag(R.layout.hotalk_menu_item_vertical_view, holder);
			if(listImage!=null){
				holder.imageView.setVisibility(View.VISIBLE);
				holder.imageView.setImageResource(listImage.get(position));
			}else{
				holder.imageView.setVisibility(View.GONE);
			}
			MenuItem item = mitems.get(position);
			holder.menuName.setText(item.MenuValue);
			holder.menuName.setTextSize(FusionField.SET_TYPE_TEXT_SIZE);
			convertView.setTag(item.MenuKey);
			return convertView;
		}
	}

	public static class ItemHolder {
		ImageView imageView;
		TextView menuName;
	}

	@SuppressWarnings("rawtypes")
	public class MenuItem implements Comparable {
		int MenuKey;
		String MenuValue;

		public MenuItem(int key, String value) {
			MenuKey = key;
			MenuValue = value;
		}

		@Override
		public int compareTo(Object o) {
			return this.MenuKey - ((MenuItem) o).MenuKey;
		}
	}
}
