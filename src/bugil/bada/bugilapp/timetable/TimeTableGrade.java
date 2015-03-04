package bugil.bada.bugilapp.timetable;

import java.util.ArrayList;

import bugil.bada.bugilapp.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TimeTableGrade extends FragmentActivity implements
		ActionBar.OnNavigationListener {

	/**
	 * DropdownMenu를 구현하기 위한 클래스
	 */
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rss_main);

		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		actionBar.setListNavigationCallbacks(new ArrayAdapter<String>(
				getActionBarThemedContextCompat(),
				android.R.layout.simple_list_item_1, android.R.id.text1,
				new String[] { "1학년", "2학년", "3학년", }), this);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	private Context getActionBarThemedContextCompat() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			return getActionBar().getThemedContext();
		} else {
			return this;
		}
	}

	@Override
	public boolean onNavigationItemSelected(int position, long id) {
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.container,
						getFragmentView(getApplicationContext(), position))
				.commit();
		return true;
	}

	private Fragment getFragmentView(Context mContext, int position) {
		return new TimeTableClass(mContext, position + 1);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	/**
	 * 반 선택을 위한 클래스
	 */
	@SuppressLint("ValidFragment")
	public class TimeTableClass extends Fragment {
		private Context mContext;
		private int Grade;

		private ListView mListView;
		private ListViewAdapter mAdapter;

		public TimeTableClass(Context mContext, int Grade) {
			this.mContext = mContext;
			this.Grade = Grade;
		}

		@SuppressLint("NewApi")
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.listview, null);

			mListView = (ListView) view.findViewById(R.id.mListView);

			mAdapter = new ListViewAdapter(mContext);
			mListView.setAdapter(mAdapter);

			mListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View v,
						int position, long id) {
					int Grade = mAdapter.mListData.get(position).Grade;
					int WClass = mAdapter.mListData.get(position).WClass;

					Intent mIntent = new Intent(mContext,
							TimeTableScrollTab.class);
					mIntent.putExtra("Grade", Grade);
					mIntent.putExtra("WClass", WClass);
					startActivity(mIntent);
				}
			});

			for (int i = 1; i <= 11; i++) {
				mAdapter.addItem(Grade + "학년 " + i + "반", Grade, i);
			}

			return view;
		}
	}

	class ViewHolder {
		public TextView choose_class;
	}

	public class ListViewAdapter extends BaseAdapter {
		private Context mContext;
		public ArrayList<ListData> mListData = new ArrayList<ListData>();

		public ListViewAdapter(Context mContext) {
			super();
			this.mContext = mContext;
		}

		public void addItem(String choose_class, int Grade, int WClass) {
			ListData addInfo = new ListData();
			addInfo.choose_class = choose_class;
			addInfo.Grade = Grade;
			addInfo.WClass = WClass;

			mListData.add(addInfo);
		}

		@Override
		public int getCount() {
			return mListData.size();
		}

		@Override
		public Object getItem(int position) {
			return mListData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();

				LayoutInflater inflater = (LayoutInflater) mContext
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.timetable_class_row,
						null);

				holder.choose_class = (TextView) convertView
						.findViewById(R.id.choose_class);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			ListData mData = mListData.get(position);
			holder.choose_class.setText(mData.choose_class);

			return convertView;
		}
	}

	class ListData {
		/**
		 * 리스트 정보를 담고 있을 객체 생성
		 */

		public String choose_class;
		public int Grade;
		public int WClass;
	}
}
