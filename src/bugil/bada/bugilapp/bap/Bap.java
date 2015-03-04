package bugil.bada.bugilapp.bap;

import java.util.Calendar;

import toast.library.meal.MealLibrary;
import bugil.bada.bugilapp.R;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.DatePicker;
import android.widget.ListView;

import com.tistory.whdghks913.croutonhelper.CroutonHelper;

import de.keyboardsurfer.android.widget.crouton.Style;

public class Bap extends Activity {
	/**
	 * 한번 급식정보를 받을때마다 272kb 데이터 소모
	 */

	private ListView mListView;
	private BapListViewAdapter mAdapter;

	private ProcessTask mProcessTask;

	private String[] calender, morning, lunch, night;

	private CroutonHelper mHelper;

	private SharedPreferences bapList;
	private SharedPreferences.Editor bapListeditor;

	private ProgressDialog mDialog;

	private boolean isSync = false;

	private Calendar mCalendar;
	private final int YEAR, MONTH, DAY_OF_MONTH;

	public Bap() {
		mCalendar = Calendar.getInstance();
		YEAR = mCalendar.get(Calendar.YEAR);
		MONTH = mCalendar.get(Calendar.MONTH);
		DAY_OF_MONTH = mCalendar.get(Calendar.DAY_OF_MONTH);
	}

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			ActionBar actionBar = getActionBar();
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}

		mHelper = new CroutonHelper(this);

		SharedPreferences mPref = PreferenceManager
				.getDefaultSharedPreferences(this);
		boolean isHidden = mPref.getBoolean("customBap", false);
		String KeyWord = mPref.getString("customBapKeyWord", null);
		mAdapter = new BapListViewAdapter(this, isHidden, KeyWord);

		bapList = getSharedPreferences("bapList", 0);
		bapListeditor = bapList.edit();

		mListView = (ListView) findViewById(R.id.mListView);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> av, View view, int position,
					long id) {
				final BapListData mData = mAdapter.getItem(position);

				final String mCalender = mData.mCalender;
				final String mMorning = mData.mMorning;
				final String mLunch = mData.mLunch;
				final String mNight = mData.mNight;
				final String mShareBapMsg = String.format(
						getString(R.string.shareBap_message_msg), mCalender,
						mMorning, mLunch, mNight);

				AlertDialog.Builder alert = new AlertDialog.Builder(Bap.this);
				alert.setTitle(R.string.bapInfoAlert_title);
				alert.setPositiveButton(R.string.EXIT, null);
				alert.setNeutralButton(R.string.allergy_title,
						new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								allergyAlert();
								dialog.dismiss();
							}
						});
				alert.setNegativeButton(R.string.bapInfoAlert_share,
						new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								bapShare(mCalender, mShareBapMsg);
								dialog.dismiss();
							}
						});
				alert.setMessage(mShareBapMsg);
				alert.show();
			}
		});

		if (bapList.getBoolean("checker", false)) {
			restoreBap();
			getBapList();
			autoScroll();

			mHelper.clearCroutonsForActivity();
			mHelper.setText(R.string.savedList);
			mHelper.setStyle(Style.CONFIRM);
			mHelper.show();

		} else {
			if (isNetwork()) {
				calender = new String[7];
				morning = new String[7];
				lunch = new String[7];
				night = new String[7];

				mProcessTask = new ProcessTask();
				mProcessTask.execute();

			} else {
				mHelper.clearCroutonsForActivity();
				mHelper.setText(R.string.noNetwork);
				mHelper.setStyle(Style.ALERT);
				mHelper.show();
			}
		}
	}

	public void allergyAlert() {
		AlertDialog.Builder alert = new AlertDialog.Builder(Bap.this);
		alert.setTitle(R.string.allergy_title);
		alert.setPositiveButton(R.string.EXIT, null);
		alert.setMessage(R.string.allergy_msg);
		alert.show();
	}

	private void bapShare(String mCalender, String mShareBapMsg) {
		Intent msg = new Intent(Intent.ACTION_SEND);
		msg.addCategory(Intent.CATEGORY_DEFAULT);
		msg.putExtra(Intent.EXTRA_TITLE, String.format(
				getString(R.string.shareBap_message_title), mCalender));
		msg.putExtra(Intent.EXTRA_TEXT, mShareBapMsg);
		msg.setType("text/plain");
		startActivity(Intent.createChooser(msg,
				getString(R.string.shareBap_title)));
	}

	private void restoreBap() {
		calender = restore("calender");
		morning = restore("morning");
		lunch = restore("lunch");
		night = restore("night");
	}

	private void autoScroll() {
		Calendar Date = Calendar.getInstance();
		int dateIndex = Date.get(Calendar.DAY_OF_WEEK);
		mListView.setSelection(dateIndex - 1);
	}

	private String getDate(int num) {
		if (num == 0)
			return "일요일";
		else if (num == 1)
			return "월요일";
		else if (num == 2)
			return "화요일";
		else if (num == 3)
			return "수요일";
		else if (num == 4)
			return "목요일";
		else if (num == 5)
			return "금요일";
		else if (num == 6)
			return "토요일";
		return null;
	}

	private void save(String name, String[] value) {
		for (int i = 0; i < value.length; i++) {
			bapListeditor.putString(name + "_" + i, value[i]);
		}

		bapListeditor.putBoolean("checker", true);
		bapListeditor.putInt(name, value.length);

		bapListeditor.putInt("updateMonth", MONTH);
		bapListeditor.putInt("updateDay", DAY_OF_MONTH);

		bapListeditor.commit();
	}

	private String[] restore(String name) {
		int length = bapList.getInt(name, 0);
		String[] string = new String[length];

		for (int i = 0; i < length; i++) {
			string[i] = bapList.getString(name + "_" + i, "");
		}
		return string;
	}

	private boolean isNetwork() {
		ConnectivityManager manager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mobile = manager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo wifi = manager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		if (wifi.isConnected() || mobile.isConnected())
			return true;
		else
			return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.bap, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int ItemId = item.getItemId();

		if (ItemId == R.id.sync) {
			if (isNetwork()) {
				if (!isSync) {
					mProcessTask = new ProcessTask();
					mProcessTask.execute();

				} else {
					mHelper.clearCroutonsForActivity();
					mHelper.setText(R.string.Syncing);
					mHelper.setStyle(Style.INFO);
					mHelper.show();
				}

			} else {
				mHelper.clearCroutonsForActivity();
				mHelper.setText(R.string.noNetwork);
				mHelper.setStyle(Style.ALERT);
				mHelper.show();
			}

		} else if (ItemId == R.id.past) {
			if (isNetwork()) {
				mCalendar.add(Calendar.DAY_OF_MONTH, -7);

				loadOrUpdate();

			} else {
				mHelper.clearCroutonsForActivity();
				mHelper.setText(R.string.noNetwork);
				mHelper.setStyle(Style.ALERT);
				mHelper.show();
			}

		} else if (ItemId == R.id.future) {
			if (isNetwork()) {
				mCalendar.add(Calendar.DAY_OF_MONTH, 7);

				loadOrUpdate();

			} else {
				mHelper.clearCroutonsForActivity();
				mHelper.setText(R.string.noNetwork);
				mHelper.setStyle(Style.ALERT);
				mHelper.show();
			}

		} else if (ItemId == R.id.setCalendar) {
			if (isNetwork()) {
				int year = mCalendar.get(Calendar.YEAR);
				int month = mCalendar.get(Calendar.MONTH);
				int day = mCalendar.get(Calendar.DAY_OF_MONTH);

				DatePickerDialog mDialog = new DatePickerDialog(this,
						new OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								mCalendar.set(year, monthOfYear, dayOfMonth);
								loadOrUpdate();
							}
						}, year, month, day);
				mDialog.show();

			} else {
				mHelper.clearCroutonsForActivity();
				mHelper.setText(R.string.noNetwork);
				mHelper.setStyle(Style.ALERT);
				mHelper.show();
			}
		}

		return super.onOptionsItemSelected(item);
	}

	private void loadOrUpdate() {
		if (YEAR == mCalendar.get(Calendar.YEAR)
				&& MONTH == mCalendar.get(Calendar.MONTH)
				&& DAY_OF_MONTH == mCalendar.get(Calendar.DAY_OF_MONTH)) {

			mAdapter.clearData();
			restoreBap();
			getBapList();
			autoScroll();
			mAdapter.notifyDataSetChanged();

		} else {
			mProcessTask = new ProcessTask();
			mProcessTask.execute();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();

		if (mDialog != null)
			mDialog.dismiss();

		mHelper.cencle(true);
	}

	public void getBapList() {
		for (int i = 0; i < 7; i++) {
			mAdapter.addItem(calender[i], getDate(i), morning[i], lunch[i],
					night[i]);
		}

		mAdapter.notifyDataSetChanged();
	}

	public class ProcessTask extends AsyncTask<String, Integer, Long> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			if (mDialog == null)
				mDialog = ProgressDialog.show(Bap.this, "",
						getString(R.string.loadingList));

			isSync = true;
			mAdapter.clearData();
		}

		@Override
		protected Long doInBackground(String... params) {
			final String CountryCode = "cne.go.kr"; // 접속 할 교육청 도메인
			final String schulCode = "N100000241"; // 학교 고유 코드
			final String schulCrseScCode = "4"; // 학교 종류 코드 1
			final String schulKndScCode = "04"; // 학교 종류 코드 2

			int num_year = mCalendar.get(Calendar.YEAR);
			int num_month = mCalendar.get(Calendar.MONTH);
			int num_day = mCalendar.get(Calendar.DAY_OF_MONTH);

			final String year = Integer.toString(num_year);
			String month = Integer.toString(num_month + 1);
			String day = Integer.toString(num_day);

			if (month.length() <= 1)
				month = "0" + month;
			if (day.length() <= 1)
				day = "0" + day;

			try {
				calender = MealLibrary.getDateNew(CountryCode, schulCode,
						schulCrseScCode, schulKndScCode, "1", year, month, day);
				morning = MealLibrary.getMealNew(CountryCode, schulCode,
						schulCrseScCode, schulKndScCode, "1", year, month, day);
				lunch = MealLibrary.getMealNew(CountryCode, schulCode,
						schulCrseScCode, schulKndScCode, "2", year, month, day);
				night = MealLibrary.getMealNew(CountryCode, schulCode,
						schulCrseScCode, schulKndScCode, "3", year, month, day);

				if (YEAR == num_year && MONTH == num_month
						&& DAY_OF_MONTH == num_day) {
					save("calender", calender);
					save("morning", morning);
					save("lunch", lunch);
					save("night", night);
				}

				isSync = false;

			} catch (Exception e) {
				Bap.this.runOnUiThread(new Runnable() {
					@Override
					public void run() {

						// 파싱 에러
						mAdapter.clearData();
						mAdapter.notifyDataSetChanged();

						mHelper.clearCroutonsForActivity();
						mHelper.setText(R.string.noNetwork);
						mHelper.setStyle(Style.ALERT);
						mHelper.show();

						isSync = false;
					}
				});

				return -1l;
			}
			return 0l;
		}

		@Override
		protected void onPostExecute(Long result) {
			super.onPostExecute(result);

			mDialog.dismiss();
			mDialog = null;

			if (result == -1l)
				return;

			getBapList();

			mHelper.clearCroutonsForActivity();
			mHelper.setText(R.string.loadList);
			mHelper.setStyle(Style.CONFIRM);
			mHelper.show();
		}
	}
}
