package bugil.bada.bugilapp.noti;



import java.util.Calendar;

import bugil.bada.bugilapp.SettingsActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class AutoStartNotifyReceiver extends BroadcastReceiver {
    private final String BOOT_COMPLETED_ACTION = "android.intent.action.BOOT_COMPLETED";
    public void onReceive(Context mContext, Intent intent) {
    	SharedPreferences mPref;
        if(intent.getAction().equals(BOOT_COMPLETED_ACTION)){
        	
        	mPref = PreferenceManager.getDefaultSharedPreferences(mContext);

    		boolean bapalarm = mPref.getBoolean("bapalarm", false);
    		boolean infotimealarm = mPref.getBoolean("infotimealarm", false);
        	if(bapalarm){
        	Calendar call = Calendar.getInstance();
			call.set(Calendar.HOUR_OF_DAY, 8);
			call.set(Calendar.MINUTE, 1);
			Calendar caln = Calendar.getInstance();
			caln.set(Calendar.HOUR_OF_DAY, 0);
			caln.set(Calendar.MINUTE, 1);
			intent = new Intent(mContext, bapnoti.class);
			PendingIntent pendingIntent = PendingIntent.getService(mContext, 0, intent, 0);
			AlarmManager am = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
			am.setRepeating(AlarmManager.RTC_WAKEUP,
					call.getTimeInMillis(), 24 * 60 * 60 * 1000,
					pendingIntent);
			am.setRepeating(AlarmManager.RTC_WAKEUP,
					caln.getTimeInMillis(), 24 * 60 * 60 * 1000,
					pendingIntent);
			Toast toast = Toast.makeText(mContext, "메세지 입력1", 
					Toast.LENGTH_SHORT); 
toast.show(); 
			
        	}
        	if(infotimealarm){

				Log.e("켜짐12","?щ젅!");
				Calendar call = Calendar.getInstance();
				call.set(Calendar.HOUR_OF_DAY, 8);
				call.set(Calendar.MINUTE, 1);

				Intent intent2 = new Intent(mContext,
						timenoti.class);
				PendingIntent pendingIntent2 = PendingIntent.getService(
						mContext, 0, intent2, 0);
				AlarmManager am = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
				am.setRepeating(AlarmManager.RTC_WAKEUP,
						call.getTimeInMillis(), 24 * 60 * 60 * 1000,
						pendingIntent2);
				Toast toast = Toast.makeText(mContext, "메세지 입력2", 
						Toast.LENGTH_SHORT); 
	toast.show();


        	}
        }
    }
}