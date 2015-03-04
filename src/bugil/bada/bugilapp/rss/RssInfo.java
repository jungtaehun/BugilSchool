package bugil.bada.bugilapp.rss;


import java.util.ArrayList;
import java.util.HashMap;

import bugil.bada.bugilapp.Webview;
import bugil.bada.bugilapp.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.tistory.whdghks913.croutonhelper.CroutonHelper;

import de.keyboardsurfer.android.widget.crouton.Style;

@SuppressLint("ValidFragment")
public class RssInfo extends Fragment {
	private Context mContext;

	private SimpleAdapter mSimpleAdapter;
	private ArrayList<HashMap<String, String>> mData;
	private ListView mListView;
	private HTMLParser mHTMLParser;

	private final String url;

	public RssInfo(Context context, String url) {
		this.mContext = context;
		this.url = url;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.listview, null);

		mData = new ArrayList<HashMap<String, String>>();
		mListView = (ListView) view.findViewById(R.id.mListView);

		mSimpleAdapter = new SimpleAdapter(mContext, mData,
				R.layout.rss_listview_row, new String[] { "title", "href",
						"date" }, new int[] { R.id.board_subject,
						R.id.board_writer, R.id.board_date, });

		mListView.setAdapter(mSimpleAdapter);

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View mView,
					int position, long id) {
				String url = mData.get(position).get("href");

				Intent webViewIntent = new Intent(mContext, Webview.class);
				webViewIntent.putExtra("url", url);

				startActivity(webViewIntent);
			}
		});

		mListView.setDivider(null);
		mListView.setDividerHeight(20);
		mListView.setSelector(new ColorDrawable(Color.TRANSPARENT));

		if (isNetwork()) {
			mHTMLParser = new HTMLParser(getActivity(), mData, mSimpleAdapter,
					url);
			mHTMLParser.start();

		} else {
			CroutonHelper mHelper = new CroutonHelper(getActivity());
			mHelper.setText("인터넷이 연결되어 있지 않습니다");
			mHelper.setStyle(Style.ALERT);
			mHelper.show();
		}

		return view;
	}

	private boolean isNetwork() {
		ConnectivityManager manager = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mobile = manager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo wifi = manager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		if (wifi.isConnected() || mobile.isConnected())
			return true;
		return false;
	}
}