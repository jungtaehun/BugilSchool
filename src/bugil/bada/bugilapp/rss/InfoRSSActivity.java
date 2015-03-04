package bugil.bada.bugilapp.rss;

import bugil.bada.bugilapp.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;

public class InfoRSSActivity extends FragmentActivity implements
		ActionBar.OnNavigationListener {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rss_main);

		// Set up the action bar to show a dropdown list.
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		// Set up the dropdown list navigation in the action bar.
		actionBar.setListNavigationCallbacks(
		// Specify a SpinnerAdapter to populate the dropdown list.
				new ArrayAdapter<String>(getActionBarThemedContextCompat(),
						android.R.layout.simple_list_item_1,
						android.R.id.text1, new String[] {
								getString(R.string.rss_full),
								getString(R.string.rss_info),
								getString(R.string.rss_noti) }), this);

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
		// When the given dropdown item is selected, show its contents in the
		// container view.
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.container,
						getFragmentView(getApplicationContext(), position))
				.commit();
		return true;
	}

	private Fragment getFragmentView(Context mContext, int position) {
		switch (position) {
		case 0:
			return new RssFull(mContext);
		case 1:
			/**
			 * mUrl = http://wondanghs.tistory.com/category/대회정보?page=
			 */
			return new RssInfo(
					mContext,
					"http://wondanghs.tistory.com/category/%EB%8C%80%ED%9A%8C%EC%A0%95%EB%B3%B4?page=");
		case 2:
			/**
			 * mUrl = http://wondanghs.tistory.com/category/알림판?page=
			 */
			return new RssInfo(mContext,
					"http://wondanghs.tistory.com/category/%EC%95%8C%EB%A6%BC%ED%8C%90?page=");
		}
		return null;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

}