package bugil.bada.bugilapp;


import java.util.Calendar;

import bugil.bada.bugilapp.bap.Bap;
import bugil.bada.bugilapp.intro.Intro;
import bugil.bada.bugilapp.noti.timenoti;
import bugil.bada.bugilapp.schedule.Schedule;
import bugil.bada.bugilapp.timetable.TimeTableGrade;
import bugil.bada.bugilapp.timetable.TimeTableScrollTab;
import bugil.bada.bugilapp.R;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.tistory.whdghks913.croutonhelper.CroutonHelper;

import de.keyboardsurfer.android.widget.crouton.Style;

public class MainActivity extends SherlockActivity {

	private ListView mListView;
	private ListViewAdapter mAdapter;

	private CroutonHelper mHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mListView = (ListView) findViewById(R.id.mList);

		mAdapter = new ListViewAdapter(this);
		mListView.setAdapter(mAdapter);

		Resources mRes = getResources();
		
		mAdapter.addItem(mRes.getDrawable(R.drawable.bugil), "학교안내", "북일고의 역사부터 교가까지");
		mAdapter.addItem(mRes.getDrawable(R.drawable.notice), "공지사항", "북일고의 새로운 소식을 전해줍니다.");
		mAdapter.addItem(mRes.getDrawable(R.drawable.meal), "급식안내", "오늘 나오는 급식은?");
		mAdapter.addItem(mRes.getDrawable(R.drawable.time), "시간표",
				"학급 시간표를 확인합니다");
		mAdapter.addItem(mRes.getDrawable(R.drawable.club), "동아리", "북일고에는 어떤 동아리가 있을까요?");
		mAdapter.addItem(mRes.getDrawable(R.drawable.calendar), "학사일정",
				"학교의 일정을 확인 할 수 있습니다");
		mAdapter.addItem(mRes.getDrawable(R.drawable.intro), "입학안내", "북일고 입학전형을 알려드립니다.");
		mAdapter.addItem(mRes.getDrawable(R.drawable.cafe), "입학카페", "클릭시 북일고 입학카페로 이동합니다.");
		mAdapter.addItem(mRes.getDrawable(R.drawable.road), "오시는 길",
				"북일고의 위치를 알려드립니다.");
		mAdapter.addItem(mRes.getDrawable(R.drawable.calling), "연락처", "학교로 전화하기");
		

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// ListData mData = mAdapter.mListData.get(position);

				if (position == 0) {
					// 학교 안내
					startActivity(new Intent(MainActivity.this, Intro.class));
				} else if (position == 1) {
				//공지사항
					startActivity(new Intent(MainActivity.this, Notice.class));
				} else if (position == 2) {
					// 급식
					startActivity(new Intent(MainActivity.this, Bap.class));
				} else if (position == 3) {
					// 시간표
					SharedPreferences mPref = PreferenceManager
							.getDefaultSharedPreferences(MainActivity.this);

					if (mPref.getBoolean("YourGradeClass", false)) {
						Intent mIntent = new Intent(MainActivity.this,
								TimeTableScrollTab.class);
						mIntent.putExtra("Grade", mPref.getInt("YourGrade", 1));
						mIntent.putExtra("WClass", mPref.getInt("YourClass", 2));
						startActivity(mIntent);

					} else {
						startActivity(new Intent(MainActivity.this,
								TimeTableGrade.class));
					}
					//toast();
				} else if (position == 4) {
					// 동아리
					startActivity(new Intent(MainActivity.this, Club.class));
				} else if (position == 5) {
				//학사일정
					startActivity(new Intent(MainActivity.this, Schedule.class));
				} else if (position == 6) {
				//입학안내
					startActivity(new Intent(MainActivity.this, Welcome.class));
				} else if (position == 7) {
				//입학카페
					startActivity(new Intent(MainActivity.this, Cafe.class));
				} else if (position == 8) {
				//오시는 길
					startActivity(new Intent(MainActivity.this, Road.class));
				} else if (position == 9) {
				//연락처
					startActivity(new Intent(MainActivity.this, Call.class));
				}
			}
		});

		String userName = PreferenceManager.getDefaultSharedPreferences(this)
				.getString("userName", null);

		mHelper = new CroutonHelper(this);

		if (userName == null || "".equals(userName))
			mHelper.setText("환영합니다~!");
		else
			mHelper.setText(userName + "님 환영합니다~!");

		mHelper.setStyle(Style.INFO);
		mHelper.show();

		try {
			final SharedPreferences mPref = PreferenceManager
					.getDefaultSharedPreferences(this);

			PackageManager packageManager = this.getPackageManager();
			PackageInfo infor = packageManager.getPackageInfo(getPackageName(),
					PackageManager.GET_META_DATA);
			final int code = infor.versionCode;

			if (mPref.getInt("versionCode", 0) != code) {
				AlertDialog.Builder alert = new AlertDialog.Builder(this);
				alert.setTitle(R.string.update_app);
				alert.setPositiveButton(R.string.OK,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								mPref.edit().putInt("versionCode", code)
										.commit();
								dialog.dismiss();
							}
						});
				alert.setMessage(R.string.changeLog);
				alert.show();
				
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.main, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int ItemId = item.getItemId();

		// if (ItemId == R.id.info) {
		// startActivity(new Intent(this, MadeBy.class));
		// } else
		if (ItemId == R.id.setting) {
			startActivity(new Intent(this, SettingsActivity.class));
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		super.onPause();

		mHelper.cencle(true);
	}
	public void toast(){
		mHelper.setText("추가 예정입니다!");
		mHelper.setStyle(Style.INFO);
		mHelper.show();
	}
	 
}
