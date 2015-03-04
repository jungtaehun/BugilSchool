package bugil.bada.bugilapp.rss;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.SimpleAdapter;

public class HTMLParser {
	private String mUrl;
	private Context mContext;
	private Source mSource;
	private ArrayList<HashMap<String, String>> mData;
	private ProgressDialog mDialog;
	private SimpleAdapter mSimpleAdapter;

	public HTMLParser(Context mContext,
			ArrayList<HashMap<String, String>> mData,
			SimpleAdapter mSimpleAdapter, String url) {
		this.mContext = mContext;
		this.mData = mData;
		this.mSimpleAdapter = mSimpleAdapter;
		this.mUrl = url;
	}

	public void start() {
		ProcessTask mProcessTask = new ProcessTask();
		mProcessTask.execute();
	}

	public class ProcessTask extends AsyncTask<String, Integer, Long> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			mDialog = ProgressDialog.show(mContext, "로딩중", "잠시만 기다려 주세요");
		}

		@Override
		protected Long doInBackground(String... params) {
			try {

				mData.clear();

				try {
					int count = checkCount(mUrl + "1");

					for (int i = 1; i <= count; i++) {
						pull(mUrl + i);
					}

				} catch (IOException e) {
					e.printStackTrace();
				}

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

			mSimpleAdapter.notifyDataSetChanged();
		}
	}

	public int checkCount(String url) throws IOException {
		URL mURL = new URL(url);
		InputStream mHtml = mURL.openStream();

		mSource = new Source(new InputStreamReader(mHtml, "UTF-8"));
		Element mTable = mSource.getAllElements("section").get(1);
		int count = mTable.getAllElements(HTMLElementName.SPAN).size();

		return count;
	}

	public void pull(String url) throws IOException {
		URL mURL = new URL(url);
		InputStream mHtml = mURL.openStream();

		mSource = new Source(new InputStreamReader(mHtml, "UTF-8"));

		Element mTable = mSource.getAllElements("section").get(0);

		int count = mTable.getAllElements(HTMLElementName.LI).size();

		Element el = null;
		HashMap<String, String> data = null;

		for (int i = 0; i < count; i++) {
			el = mTable.getAllElements(HTMLElementName.LI).get(i);
			data = new HashMap<String, String>();

			String title = el.getAllElements(HTMLElementName.A).get(0)
					.getContent().toString();

			data.put("title", RemoveHTMLTag(title));

			String href = "http://wondanghs.tistory.com"
					+ el.getAllElements(HTMLElementName.A).get(0)
							.getAttributeValue("href").toString();
			data.put("href", href);

			data.put("date", el.getAllElements("time").get(0).getContent()
					.toString());

			mData.add(data);
		}
	}

	public String RemoveHTMLTag(String changeStr) {
		if (changeStr != null && !changeStr.equals("")) {
			changeStr = changeStr.replaceAll(
					"<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
		} else {
			changeStr = "";
		}
		return changeStr;
	}
}
