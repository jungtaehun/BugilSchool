package bugil.bada.bugilapp;


import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;
import bugil.bada.bugilapp.bapautoupdate.updateAlarm;
import bugil.bada.bugilapp.noti.bapnoti;
import bugil.bada.bugilapp.noti.timenoti;

import com.tistory.whdghks913.croutonhelper.CroutonHelper;

import de.keyboardsurfer.android.widget.crouton.Style;

public class SettingsActivity extends PreferenceActivity {
	private SharedPreferences mPref;
	private SharedPreferences.Editor mEdit;
	private NotificationManager manager;

	private AlarmManager am;
	private PendingIntent pendingIntent;
	private PendingIntent pendingIntent2;
	private CroutonHelper mHelper;

	private int HIDDEN_MODE = 0;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.wondang_settings);

		setOnPreferenceClick(findPreference("openSource"));
		setOnPreferenceClick(findPreference("infoAutoUpdate"));
		setOnPreferenceClick(findPreference("deleteGradeClass"));
		setOnPreferenceClick(findPreference("appVersion"));
		setOnPreferenceClick(findPreference("infobapnoti"));
		setOnPreferenceChange(findPreference("updateLife"));
		setOnPreferenceChange(findPreference("autoBapUpdate"));
		setOnPreferenceChange(findPreference("userName"));
		setOnPreferenceChange(findPreference("bapalarm"));

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			ActionBar actionBar = getActionBar();
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}

		mPref = PreferenceManager
				.getDefaultSharedPreferences(SettingsActivity.this);
		mEdit = mPref.edit();
		mHelper = new CroutonHelper(this);
		
		if (mPref.getBoolean("HiddenMenu", false)) {
			addPreferencesFromResource(R.xml.hidden_settings);
			setOnPreferenceChange(findPreference("customBapKeyWord"));
		}

		try {
			PackageManager packageManager = this.getPackageManager();
			PackageInfo infor = packageManager.getPackageInfo(getPackageName(),
					PackageManager.GET_META_DATA);
			findPreference("appVersion").setSummary(infor.versionName);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	private void setOnPreferenceChange(Preference mPreference) {
		mPreference.setOnPreferenceChangeListener(onPreferenceChangeListener);

		if (mPreference instanceof ListPreference) {
			ListPreference listPreference = (ListPreference) mPreference;
			int index = listPreference.findIndexOfValue(PreferenceManager
					.getDefaultSharedPreferences(mPreference.getContext())
					.getString(mPreference.getKey(), ""));
			mPreference
					.setSummary(index >= 0 ? listPreference.getEntries()[index]
							: null);
		} else if (mPreference instanceof EditTextPreference) {
			String values = ((EditTextPreference) mPreference).getText();
			if (values == null)
				values = "";
			onPreferenceChangeListener.onPreferenceChange(mPreference, values);
		}
	}

	private void setOnPreferenceClick(Preference mPreference) {
		mPreference.setOnPreferenceClickListener(onPreferenceClickListener);
	}
	

	private Preference.OnPreferenceChangeListener onPreferenceChangeListener = new Preference.OnPreferenceChangeListener() {

		@Override
		public boolean onPreferenceChange(Preference preference, Object newValue) {
			String stringValue = newValue.toString();

			if (preference instanceof EditTextPreference) {
				preference.setSummary(stringValue);

			} else if (preference instanceof ListPreference) {

				/**
				 * ListPreference??경우 stringValue�????entryValues?�기 ?�문??바로
				 * Summary�???? ?�용?��? 못한???�라???�정??entries?�서 String??로딩?�여
				 * ?�용?�다
				 */

				ListPreference listPreference = (ListPreference) preference;
				int index = listPreference.findIndexOfValue(stringValue);

				preference
						.setSummary(index >= 0 ? listPreference.getEntries()[index]
								: null);

				updateAlarm updateAlarm = new updateAlarm(SettingsActivity.this);
				updateAlarm.cancle();

				if (index == 0) {
					updateAlarm.autoUpdate();
				} else if (index == 1) {
					updateAlarm.SaturdayUpdate();
				} else if (index == 2) {
					updateAlarm.SundayUpdate();
				}

			} else if (preference instanceof CheckBoxPreference) {
				String getKey = preference.getKey();
				SharedPreferences mPref = PreferenceManager
						.getDefaultSharedPreferences(SettingsActivity.this);
				if(getKey.equals("autoBapUpdate")){

				if (mPref.getBoolean("firstOfAutoUpdate", true)) {
					mPref.edit().putBoolean("firstOfAutoUpdate", false)
							.commit();
					showNotifi();
				}else if (!mPref.getBoolean("autoBapUpdate", false)
						&& preference.isEnabled()) {
					int updateLife = 1;

					try {
						updateLife = Integer.parseInt(findPreference(
								"openSource").getSharedPreferences().getString(
								"updateLife", "1"));
					} catch (Exception e) {

					}

					updateAlarm updateAlarm = new updateAlarm(
							SettingsActivity.this);

					if (updateLife == 1) {
						updateAlarm.autoUpdate();
					} else if (updateLife == 0) {
						updateAlarm.SaturdayUpdate();
					} else if (updateLife == -1) {
						updateAlarm.SundayUpdate();
					}

				} else {
					updateAlarm updateAlarm = new updateAlarm(
							SettingsActivity.this);
					updateAlarm.cancle();
				}
				}else if(getKey.equals("bapalarm")){
				
				if (!mPref.getBoolean("bapalarm", false)
						&& preference.isEnabled()) {

					Log.e("켜짐12","?��?��!");
					Calendar call = Calendar.getInstance();
					call.set(Calendar.HOUR_OF_DAY, 8);
					call.set(Calendar.MINUTE, 1);
					Calendar caln = Calendar.getInstance();
					caln.set(Calendar.HOUR_OF_DAY, 14);
					caln.set(Calendar.MINUTE, 1);

					Intent intent = new Intent(SettingsActivity.this,
							bapnoti.class);
					pendingIntent = PendingIntent.getService(
							SettingsActivity.this, 0, intent, 0);
					pendingIntent2 = PendingIntent.getService(
							SettingsActivity.this, 0, intent, 0);
					am = (AlarmManager) getSystemService(ALARM_SERVICE);
					am.setRepeating(AlarmManager.RTC_WAKEUP,
							call.getTimeInMillis(), 24 * 60 * 60 * 1000,
							pendingIntent);
					am.setRepeating(AlarmManager.RTC_WAKEUP,
							call.getTimeInMillis(), 24 * 60 * 60 * 1000,
							pendingIntent2);
					intent1();
					

				}else{
					manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
					Log.e("꺼짐12","?��?��!");
					
					am = (AlarmManager) getSystemService(ALARM_SERVICE);
					Intent intent = new Intent(SettingsActivity.this,
							bapnoti.class);
					pendingIntent2 = PendingIntent.getService(
							SettingsActivity.this, 0, intent, 0);
					Intent intent2 = new Intent(SettingsActivity.this,
							bapnoti.class);
					pendingIntent2 = PendingIntent.getService(SettingsActivity.this, 0, intent2, 0);
					am.cancel(pendingIntent);
							
					
					am.cancel(pendingIntent2);
					
					manager.cancel(9394);
				}
				}

			}

			return true;
		}

	};

	private Preference.OnPreferenceClickListener onPreferenceClickListener = new Preference.OnPreferenceClickListener() {

		@Override
		public boolean onPreferenceClick(Preference preference) {
			String getKey = preference.getKey();

			if ("openSource".equals(getKey)) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						SettingsActivity.this);
				alert.setTitle(R.string.license_title);
				alert.setPositiveButton(R.string.OK,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
				alert.setMessage(R.string.license);
				alert.show();

			} else if ("infoAutoUpdate".equals(getKey)) {
				showNotifi();
			} else if ("deleteGradeClass".equals(getKey)) {
				mEdit.remove("YourGrade");
				mEdit.remove("YourClass");
				mEdit.remove("YourGradeClass");
				mEdit.remove("DontShowGradeClass");
				mEdit.commit();
			} else if ("appVersion".equals(getKey)) {
				if (HIDDEN_MODE == 20) {
					mEdit.putBoolean("HiddenMenu", true).commit();

					Toast.makeText(getApplicationContext(),
							"?��겨진 메뉴�??? ?��?��?�� ?��?��?��?��?�� ?��?��?�� ?��주세?��", Toast.LENGTH_SHORT)
							.show();
				} else {
					++HIDDEN_MODE;
				}
			} else if ("infobapnoti".equals(getKey)) {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
					intent1();
				} else {
					mHelper.setText("?��리빈 ?��?�� 기기?��?���??? ?��?���??? �????��?��?��?��!");
					mHelper.setStyle(Style.CONFIRM);
					mHelper.setDuration(5000);
					mHelper.setAutoTouchCencle(true);
					mHelper.show();
				}

			}

			return true;
		}
	};

	private void showNotifi() {
		AlertDialog.Builder alert = new AlertDialog.Builder(
				SettingsActivity.this);
		alert.setTitle(R.string.info_autoUpdate_title);
		alert.setPositiveButton(R.string.OK,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		alert.setMessage(R.string.info_autoUpdate);
		alert.show();
	}

	private void intent1() {
		Intent intent = new Intent(this, bapnoti.class);
		stopService(intent);
		startService(intent);
	}


}