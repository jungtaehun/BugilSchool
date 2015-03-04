package bugil.bada.bugilapp.noti;



import java.util.Calendar;

import bugil.bada.bugilapp.bap.Bap;



import bugil.bada.bugilapp.R;



import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;

import android.app.Service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;


@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class bapnoti extends Service {

	private SharedPreferences pref;
	private Calendar calendar;
	private NotificationManager manager;
	private Notification notification = null;
	private PendingIntent intent2;

	int cal;
	int time;

	@Override
	public void onCreate() {

		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		calendar = Calendar.getInstance();

		cal = calendar.get(Calendar.DAY_OF_WEEK);
		time = calendar.get(Calendar.HOUR_OF_DAY);
		//intent = new Intent(this, Bap.class);
		
		String str = null;

		manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		intent2 = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(bapnoti.this, Bap.class),
				0);

		pref = getSharedPreferences("bapList", MODE_PRIVATE);

		if (14 <= time && time <= 24) {
			for (int i = 0; i <= 6; i++) {
				if (cal - 1 == i) {
					str = "night_" + i;
				}
			}
			String str2 = pref.getString(str, " ");

			if (str2.equals(" ")) {
				str2 = "오늘은 석식이 없습니다!";
			}

			notification = new Notification.BigTextStyle(
					new Notification.Builder(getApplicationContext())
							.setContentTitle("오늘의 석식")
							.setContentText("오늘의 석식 급식표입니다")

							.setSmallIcon(R.drawable.meal)
							.addAction(R.drawable.meal, "일주일 급식보기", intent2)
							.setLargeIcon(
									BitmapFactory.decodeResource(
											getResources(), R.drawable.meal)).setOngoing(true)
							.setTicker("오늘의 급식표 입니다!"))

			.bigText(str2).build();
		} else {

			for (int i = 0; i <= 6; i++) {
				if (cal - 1 == i) {
					str = "lunch_" + i;
				}
			}
			String str2 = pref.getString(str, " ");
			if (str2.equals(" ")) {
				str2 = "오늘은 점심이 없습니다!";
			}

			notification = new Notification.BigTextStyle(
					new Notification.Builder(getApplicationContext())
							.setContentTitle("오늘의 점심")
							.setContentText("오늘의 점심 급식표입니다")
							.setContentIntent(
									PendingIntent.getActivity(this, 0, intent,
											0))
							.setSmallIcon(R.drawable.meal)
							
							.addAction(R.drawable.meal, "일주일 급식보기", intent2)
							.setLargeIcon(
									BitmapFactory.decodeResource(
											getResources(), R.drawable.meal)).setOngoing(true)
							.setTicker("오늘의 급식표 입니다!")).bigText(str2).build();

		}

		manager.notify(9394, notification);
		return startId;

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent arg0) {

		return null;

	}

}
