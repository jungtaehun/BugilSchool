package bugil.bada.bugilapp.timetable;


import java.util.Calendar;

import bugil.bada.bugilapp.SettingsActivity;
import bugil.bada.bugilapp.R;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.tistory.whdghks913.croutonhelper.CroutonHelper;

import de.keyboardsurfer.android.widget.crouton.Style;

public class TimeTableScrollTab extends FragmentActivity {
	public final String[] DAY = { "월요일", "화요일", "수요일", "목요일", "금요일" };

	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;

	// String mFileName, tableName;
	public int Grade, WClass;

	private CroutonHelper mHelper;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timetablescrolltab);

		Intent mIntent = getIntent();

		Grade = mIntent.getIntExtra("Grade", 1);
		WClass = mIntent.getIntExtra("WClass", 2);

		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getApplicationContext(), getSupportFragmentManager());

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		ActionBar actionBar = getActionBar();
		actionBar.setTitle(Grade + "학년 " + WClass + "반 시간표");

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}

		final SharedPreferences mPref = PreferenceManager
				.getDefaultSharedPreferences(this);
		if (!mPref.getBoolean("YourGradeClass", false)
				&& !mPref.getBoolean("DontShowGradeClass", false)) {

			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("학급 정보 설정");
			alert.setPositiveButton("확인", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					SharedPreferences.Editor mEdit = mPref.edit();
					mEdit.putInt("YourGrade", Grade);
					mEdit.putInt("YourClass", WClass);
					mEdit.putBoolean("YourGradeClass", true).commit();

					dialog.dismiss();
				}
			});
			alert.setNegativeButton("취소", null);
			alert.setNeutralButton("다시표시안함", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					SharedPreferences.Editor mEdit = mPref.edit();
					mEdit.remove("YourGradeClass");
					mEdit.remove("YourGrade");
					mEdit.remove("YourClass");
					mEdit.putBoolean("DontShowGradeClass", true).commit();

					dialog.dismiss();

				}
			});
			alert.setMessage(R.string.yourGradeClass);
			alert.show();
		}

		if (mViewPager.getChildCount() > 0) {
			setCurrentItem();
		}

		mHelper = new CroutonHelper(this);
		mHelper.setText("이 시간표는 100% 확신할수 없으며 언제든지 변동될수 있습니다\n어플에서 제공하는 시간표외 항상 정확한 수업 정보를 숙지하시길 바랍니다");
		mHelper.setStyle(Style.CONFIRM);
		mHelper.setDuration(5000);
		mHelper.setAutoTouchCencle(true);
		mHelper.show();
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		Context mContext;

		public SectionsPagerAdapter(Context context, FragmentManager fm) {
			super(fm);
			mContext = context;
		}

		@Override
		public Fragment getItem(int position) {
			return new TimeTableShow(mContext, position, Grade, WClass);
		}

		@Override
		public int getCount() {
			return 5;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return DAY[position];
		}
	}

	private void setCurrentItem() {
		Calendar calendar = Calendar.getInstance();
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

		if (dayOfWeek > 1 && dayOfWeek < 7) {
			mViewPager.setCurrentItem(dayOfWeek - 2);
		} else {
			mViewPager.setCurrentItem(0);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.timetable, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.today) {
			setCurrentItem();
		} else if (id == R.id.mSetting) {
			startActivity(new Intent(this, SettingsActivity.class));
		} else if (id == R.id.mShare) {
			showShareIntent();
		}

		return super.onOptionsItemSelected(item);
	}

	public void showShareIntent() {
		/**
		 * DB에 저장된 시간표를 가져온다
		 */
		String SQL = "select * from bugil";

		Cursor mCursor = TimeTableShow.mSQDB.rawQuery(SQL, null);

		/**
		 * 요일 설정
		 */
		mCursor.moveToPosition((mViewPager.getCurrentItem() * 7) + 1);

		String[] TimeTable = new String[14];

		for (int period = 1; period <= 7; period++) {
			String subject, room;

			if (Grade == 1) {
				subject = mCursor.getString((WClass * 2) - 2);
				room = "";
			} else if (Grade == 2) {
				subject = mCursor.getString(16 + (WClass * 2));
				room = "";
			} else {
				subject = mCursor.getString(34 + (WClass * 2));
				room = "";
			}

			if (subject != null && !subject.isEmpty()
					&& subject.indexOf("\n") != -1)
				subject = subject.replace("\n", "(") + ")";

			TimeTable[(period * 2) - 2] = subject;
			TimeTable[(period * 2) - 1] = room;

			mCursor.moveToNext();
		}

		String title = String.format(
				getString(R.string.shareTimeTable_message_title),
				DAY[mViewPager.getCurrentItem()]);
		Intent msg = new Intent(Intent.ACTION_SEND);
		msg.addCategory(Intent.CATEGORY_DEFAULT);
		msg.putExtra(Intent.EXTRA_TITLE, title);
		msg.putExtra(Intent.EXTRA_TEXT, String.format(
				getString(R.string.shareTimeTable_message_message),
				DAY[mViewPager.getCurrentItem()], TimeTable[0], TimeTable[1],
				TimeTable[2], TimeTable[3], TimeTable[4], TimeTable[5],
				TimeTable[6], TimeTable[7], TimeTable[8], TimeTable[9],
				TimeTable[10], TimeTable[11], TimeTable[12], TimeTable[13]));
		msg.setType("text/plain");
		startActivity(Intent.createChooser(msg, title));
	}
}
