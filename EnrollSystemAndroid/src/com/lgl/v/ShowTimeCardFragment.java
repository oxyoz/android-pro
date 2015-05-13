package com.lgl.v;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lgl.c.AddTimeCard;
import com.lgl.c.RequestHandlerCode;
import com.lgl.c.RequestThreadCode;
import com.lgl.c.TimeCardData;
import com.main.v.R;
import com.oz.v.BasicFragment;

public class ShowTimeCardFragment extends BasicFragment {

	private View viewContainer;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		viewContainer = inflater.inflate(R.layout.fragment_show_time_card,
				container, false);

		return viewContainer;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		// 初始化各个控件
		init();

	}

	/* 多线程处理 */
	@Override
	public void run() {

		switch (this.requestThreadCode) {

		case PRAPARE_ADD_DATA: {

			AddTimeCard addTimeCard = new AddTimeCard();

			TimeCardData.setListData(addTimeCard.getTimeCard());

			requestHandler(RequestHandlerCode.SET_WEIGET_DATA);

		}
			break;

		default:
			break;
		}

	}

	/* 异步消息处理 */
	@Override
	public void handlerMethod(Message msg) {

		if (msg.what == RequestHandlerCode.SET_WEIGET_DATA.ordinal()) {

			if (TimeCardData.getListData() != null) {

				TimeCardAdapter adapter = new TimeCardAdapter(getActivity(),
						TimeCardData.getListData());

				this.listShowTimeCard.setAdapter(adapter);

			}
		}

	}

	// 多线程请求码
	private RequestThreadCode requestThreadCode = RequestThreadCode.ACQUIESCE;

	// 请求开启多线程
	private void requestThread(RequestThreadCode requestThreadCode) {

		// 设置请求代码
		this.requestThreadCode = requestThreadCode;
		// 开启请求处理线程
		new Thread(this).start();

	}

	// 请求异步处理
	private void requestHandler(RequestHandlerCode requestHandlerCode) {

		Message msg = new Message();
		// 设置请求代码
		msg.what = requestHandlerCode.ordinal();
		// 发送异步消息
		getHandler().sendMessage(msg);

	}

	private void init() {
		// 实例化各个控件对象
		instanceWeiget();
		// 设置各个控件的事件监听器
		setWeigetEvent();
		// 设置控件显示的数据
		setWeigetData();
	}

	private void setWeigetData() {
		// 设置预加载数据
		requestThread(RequestThreadCode.PRAPARE_ADD_DATA);

	}

	private void setWeigetEvent() {

	}

	private ListView listShowTimeCard;

	private void instanceWeiget() {

		listShowTimeCard = (ListView) this.viewContainer
				.findViewById(R.id.list_show_time_card);

	}

}
