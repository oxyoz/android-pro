package com.oz.enroll.receiver;

import com.oz.enroll.config.Action;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class CoreReceiver extends BroadcastReceiver {

	private Context context;
	
	private Intent intent;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		this.context = context;
		
		this.intent = intent;
		
		if (Action.receiver.RECEIVER_ENROLL_ADDSTUDENTINFO.equals(intent
				.getAction())) {

			this.actionService(Action.service.SERVICE_ENROLL_ADDSTUDENTINFO);
			
		}

		if (Action.receiver.RECEIVER_ENROLL_INADD.equals(intent.getAction())) {

			this.actionService(Action.service.SERVICE_ENROLL_INADD);
			
		}

		if (Action.receiver.RECEIVER_ENROLL_INADDDEF.equals(intent.getAction())) {

			this.actionService(Action.service.SERVICE_ENROLL_INADDDEF);
			
		}

		if (Action.receiver.RECEIVER_ENROLL_INADDOTHER.equals(intent
				.getAction())) {

			this.actionService(Action.service.SERVICE_ENROLL_INADDOTHER);
			
		}

		if (Action.receiver.RECEIVER_ENROLL_OUTADD.equals(intent.getAction())) {

			this.actionService(Action.service.SERVICE_ENROLL_OUTADD);

		}

		if (Action.receiver.RECEIVER_ENROLL_STUDENTINFO.equals(intent
				.getAction())) {

			this.actionService(Action.service.SERVICE_ENROLL_STUDENTINFO);
			Log.i("CoreReceiver action", intent.getAction()+"---"+intent.getIntExtra("what", 0));
		}

	}

	private void actionService(String action) {

		Intent it = new Intent(action);
		
		it.putExtra("what", this.intent.getIntExtra("what", 0));
		
		it.putExtra("check", this.intent.getStringExtra("check"));
		
		this.context.startService(it);

	}

}
