package bugil.bada.bugilapp.rss;


import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import bugil.bada.bugilapp.Webview;
import bugil.bada.bugilapp.R;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
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
public class RssFull extends Fragment {
	private Context mContext;
	private ArrayList<HashMap<String, String>> mArrayList = new ArrayList<HashMap<String, String>>();
	private HashMap<String, String> mHash;
	private SimpleAdapter mAdapter;
	private ListView mListView;
	private ProgressDialog mDialog;

	private final String RSS_ADRESS = "http://wondanghs.tistory.com/rss";

	public RssFull(Context mContext) {
		this.mContext = mContext;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.listview, null);

		mListView = (ListView) view.findViewById(R.id.mListView);
		mAdapter = new SimpleAdapter(mContext, mArrayList,
				R.layout.rss_listview_row, new String[] { "subject", "date",
						"category" }, new int[] { R.id.board_subject,
						R.id.board_date, R.id.board_writer });
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> av, View mView,
					int position, long id) {
				String link = mArrayList.get(position).get("link");

				Intent webViewIntent = new Intent(mContext, Webview.class);
				webViewIntent.putExtra("url", link);

				startActivity(webViewIntent);
			}
		});

		mListView.setDivider(null);
		mListView.setDividerHeight(20);
		mListView.setSelector(new ColorDrawable(Color.TRANSPARENT));

		if (isNetwork()) {
			ProcessTask mProcessTask = new ProcessTask();
			mProcessTask.execute();

		} else {
			CroutonHelper mHelper = new CroutonHelper(getActivity());
			mHelper.setText(R.string.internet_error);
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

	private void rssParse(String url) throws Exception {
		mArrayList.clear();

		try {
			URL mUrl = new URL(url);

			XmlPullParserFactory parserCreator = XmlPullParserFactory
					.newInstance();
			XmlPullParser mParser = parserCreator.newPullParser();

			mParser.setInput(mUrl.openStream(), null);

			int parserEvent = mParser.getEventType();
			String tag = "";

			boolean inTitle = false; // 제목여부판단
			boolean inItem = false; // 아이템변경 판단
			// boolean inWriter = false; // 작성자 판단
			boolean inDate = false; // 작성일
			boolean inLink = false; // 링크
			boolean inCategory = false; // 카테고리
			// boolean inContent = false; // 내용

			// XML 날짜 형식 변환하기
			SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy년 MM월 dd일 HH시 mm분 ss초");
			Date pubdate = null;

			do {
				// Log.i("test", "" + count);
				switch (parserEvent) {

				case XmlPullParser.START_TAG:
					tag = mParser.getName();
					// 만약 item으로 시작하는 태그라면
					if (tag.compareTo("item") == 0) {
						inItem = true;
						mHash = new HashMap<String, String>();
					}
					// 만약 title 로 시작하는 태그라면
					if (tag.compareTo("title") == 0) {
						inTitle = true;
					}
					// 만약 link 로 시작하는 태그라면
					if (tag.compareTo("link") == 0) {
						inLink = true;
					}
					// 만약 category 로 시작하는 태그라면
					if (tag.compareTo("category") == 0) {
						inCategory = true;
					}
					// // 만약 dc:creator 이거나 author 태그라면
					// if (tag.compareTo("dc:creator") == 0
					// || tag.compareTo("author") == 0) {
					// inWriter = true;
					// }
					// 만약 pubDate 태그라면
					if (tag.compareTo("pubDate") == 0) {
						inDate = true;
					}
					// // 만약 description 태그라면
					// if (tag.compareTo("description") == 0) {
					// inContent = true;
					// }
					break;

				case XmlPullParser.TEXT:
					tag = mParser.getName();
					// 제목
					if (inItem && inTitle) {
						mHash.put("subject", mParser.getText());
					}
					// 작성일
					if (inItem && inDate) {
						pubdate = new Date(Date.parse(mParser.getText()));
						mHash.put("date", sdf.format(pubdate));
					}
					// // 작성자
					// if (inItem && inWriter) {
					// mHash.put("writer", mParser.getText());
					// }
					// 링크
					if (inItem && inLink) {
						mHash.put("link", mParser.getText());
					}
					if (inItem && inCategory) {
						mHash.put("category", mParser.getText());
					}
					// // 내용
					// if (inItem && inContent) {
					// // map.put("content", mParser.getText());
					// contents.add(mParser.getText());
					// }
					break;

				case XmlPullParser.END_TAG:
					tag = mParser.getName();
					// 태그가 끝나면
					if (tag.compareTo("item") == 0) {
						inItem = false;
						mArrayList.add(mHash);
					}
					if (tag.compareTo("title") == 0) {
						inTitle = false;
					}
					// if (tag.compareTo("dc:creator") == 0
					// || tag.compareTo("author") == 0) {
					// inWriter = false;
					// }
					if (tag.compareTo("pubDate") == 0) {
						inDate = false;
					}
					if (tag.compareTo("link") == 0) {
						inLink = false;
					}
					if (tag.compareTo("category") == 0) {
						inCategory = false;
					}
					// if (tag.compareTo("description") == 0) {
					// inContent = false;
					// }
					break;
				}

				parserEvent = mParser.next();

			} while (parserEvent != XmlPullParser.END_DOCUMENT);

		} catch (Exception e) {
			throw e;
		}
	}

	public class ProcessTask extends AsyncTask<String, Integer, Long> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			if (mDialog == null)
				mDialog = ProgressDialog.show(getActivity(), getString(R.string.Loading_title),
						getString(R.string.Loading_summary));
		}

		@Override
		protected Long doInBackground(String... params) {
			try {
				rssParse(RSS_ADRESS);
			} catch (Exception e) {
				return -1l;
			}
			return 0l;
		}

		@Override
		protected void onPostExecute(Long result) {
			super.onPostExecute(result);

			if (mDialog != null)
				mDialog.dismiss();

			if (result == -1l)
				return;

			mAdapter.notifyDataSetChanged();
		}
	}

}