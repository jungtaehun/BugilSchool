package bugil.bada.bugilapp.timetable;

import java.io.File;

import bugil.bada.bugilapp.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

@SuppressLint("ValidFragment")
public class TimeTableShow extends Fragment {
	private Context mContext;
	private final int DayOfWeek;
	public final String dbName = "HwaJungHS.db",
			tableName = "bugil";
	private final int Grade, WClass;

	public final String mFilePath = Environment.getExternalStorageDirectory()
			.getAbsolutePath() + "/HwaJungHS/";
	public final int dbVersion = 2;

	public static SQLiteDatabase mSQDB;

	private ListView mListView;
	public TimeTableShowAdapter mAdapter;

	public TimeTableShow(Context mContext, int DayOfWeek, int Grade, int WClass) {
		this.mContext = mContext;
		this.DayOfWeek = DayOfWeek;
		this.Grade = Grade;
		this.WClass = WClass;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.listview, null);

		if (getDBUpdate()) {
			copyAssets copyAssets = new copyAssets();
			copyAssets.assetsFileCopy(mContext, mFilePath, dbName);
		}

		mSQDB = SQLiteDatabase.openDatabase(mFilePath + dbName, null,
				SQLiteDatabase.OPEN_READWRITE);

		mAdapter = new TimeTableShowAdapter(mContext);
		mListView = (ListView) view.findViewById(R.id.mListView);
		mListView.setAdapter(mAdapter);
		mListView.setDivider(new ColorDrawable(Color.TRANSPARENT));
		mListView.setDividerHeight(20);

		/**
		 * DB에 저장된 시간표를 가져온다
		 */
		String SQL = "select * from " + tableName;

		Cursor mCursor = mSQDB.rawQuery(SQL, null);
		/**
		 * 요일 설정
		 */
		mCursor.moveToPosition((DayOfWeek * 7) + 1);

		for (int period = 1; period <= 7; period++) {
			String subject, room;

			if (Grade == 1) {
				subject = mCursor.getString((WClass * 2) - 2);
				room = null;
			} else if (Grade == 2) {
				subject = mCursor.getString(20 + (WClass * 2));
				room = null;
			} else {
				subject = mCursor.getString(42 + (WClass * 2));
				room = null;
			}

			if (subject != null && !subject.isEmpty()
					&& subject.indexOf("\n") != -1)
				subject = subject.replace("\n", "(") + ")";

			mAdapter.addItem(period, subject, room);

			mCursor.moveToNext();
		}

		return view;
	}

	private boolean getDBUpdate() {
		SharedPreferences mPref = PreferenceManager
				.getDefaultSharedPreferences(mContext);

		boolean fileInfo = !(new File(mFilePath + dbName).exists());
		boolean versionInfo = mPref.getInt("dbVersion", 0) != dbVersion;

		if (fileInfo || versionInfo) {
			mPref.edit().putInt("dbVersion", dbVersion).commit();
			return true;
		}
		return false;
	}
}