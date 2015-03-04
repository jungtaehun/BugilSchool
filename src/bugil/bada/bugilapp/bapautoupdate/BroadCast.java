package bugil.bada.bugilapp.bapautoupdate;

import java.util.Calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class BroadCast extends BroadcastReceiver {
	private SharedPreferences mPref;

	@Override
	public void onReceive(Context mContext, Intent mIntent) {
		String ACTION = mIntent.getAction();
		mPref = PreferenceManager.getDefaultSharedPreferences(mContext);

		boolean autoUpdate = mPref.getBoolean("autoBapUpdate", false);
		if (!autoUpdate)
			return;

		int updateLife = 0;

		try {
			updateLife = Integer.parseInt(mPref.getString("updateLife", "0"));
		} catch (Exception e) {
			return;
		}

		Calendar mCalendar = Calendar.getInstance();
		int weekday = mCalendar.get(Calendar.DAY_OF_WEEK);

		if (Intent.ACTION_BOOT_COMPLETED.equals(ACTION)) {
			/**
			 * 부팅후 실행
			 */
			if (weekday == Calendar.SUNDAY && updateLife == -1) {
				// 급식 업데이트 호출
				if (haveToUpdate(mContext, mCalendar))
					mContext.startService(new Intent(mContext,
							updateService.class));
			} else if (weekday == Calendar.SATURDAY && updateLife == 0) {
				// 급식 업데이트 호출
				if (haveToUpdate(mContext, mCalendar))
					mContext.startService(new Intent(mContext,
							updateService.class));
			}

			updateAlarm updateAlarm = new updateAlarm(mContext);
			if (updateLife == 1) {
				// 자동 예약
				updateAlarm.autoUpdate();
			} else if (updateLife == 0) {
				// 토요일 예약
				updateAlarm.SaturdayUpdate();
			} else if (updateLife == -1) {
				// 일요일 예약
				updateAlarm.SundayUpdate();
			}

		} else if ("ACTION_UPDATE".equals(ACTION)) {
			/**
			 * 업데이트 액션
			 */
			if (haveToUpdate(mContext, mCalendar)) {
				mContext.startService(new Intent(mContext, updateService.class));
			}
		} else if ("ACTION_UPDATE_AUTO".equals(ACTION)) {
			/**
			 * 자동 업데이트 액션
			 */
			if (haveToUpdate(mContext, mCalendar)) {
				mContext.startService(new Intent(mContext, updateService.class));
			}
		}
	}

	private boolean haveToUpdate(Context mContext, Calendar mCalendar) {
		SharedPreferences bapList = mContext.getSharedPreferences("bapList", 0);

		int mCalendarMonth = mCalendar.get(Calendar.MONTH);
		int mCalendarDay = mCalendar.get(Calendar.DAY_OF_MONTH);

		int updateMonth = bapList.getInt("updateMonth", 0);
		int updateDay = bapList.getInt("updateDay", 0);

		if (updateMonth == 0 && updateDay == 0)
			return true;

		if (updateMonth == mCalendarMonth && updateDay == mCalendarDay)
			return false;

		return true;
	}
}
