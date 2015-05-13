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
		// ��ʼ�������ؼ�
		init();

	}

	/* ���̴߳��� */
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

	/* �첽��Ϣ���� */
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

	// ���߳�������
	private RequestThreadCode requestThreadCode = RequestThreadCode.ACQUIESCE;

	// ���������߳�
	private void requestThread(RequestThreadCode requestThreadCode) {

		// �����������
		this.requestThreadCode = requestThreadCode;
		// �����������߳�
		new Thread(this).start();

	}

	// �����첽����
	private void requestHandler(RequestHandlerCode requestHandlerCode) {

		Message msg = new Message();
		// �����������
		msg.what = requestHandlerCode.ordinal();
		// �����첽��Ϣ
		getHandler().sendMessage(msg);

	}

	private void init() {
		// ʵ���������ؼ�����
		instanceWeiget();
		// ���ø����ؼ����¼�������
		setWeigetEvent();
		// ���ÿؼ���ʾ������
		setWeigetData();
	}

	private void setWeigetData() {
		// ����Ԥ��������
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
