package bugil.bada.bugilapp;

import bugil.bada.bugilapp.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.DownloadListener;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Webview extends Activity {

	private WebView mWebView;
	private WebSettings mWebSettings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);

		mWebView = (WebView) findViewById(R.id.mWebView);

		mWebSettings = mWebView.getSettings();
		// mWebSettings.setSavePassword(false);
		// mWebSettings.setAppCacheMaxSize(10000);
		mWebSettings.setSaveFormData(false);
		mWebSettings.setSupportZoom(true);
		mWebSettings.setBuiltInZoomControls(true);
		mWebSettings.setJavaScriptEnabled(true);
		mWebSettings.setDefaultTextEncodingName("Euc-kr");
		// mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

		mWebView.setWebChromeClient(new webViewChrome());
		mWebView.setWebViewClient(new webViewClient());

		mWebView.setDownloadListener(new DownloadListener() {

			@Override
			public void onDownloadStart(String url, String userAgent,
					String contentDisposition, String mimetype,
					long contentLength) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
			}
		});

		Intent mIntent = getIntent();

		mWebView.loadUrl(mIntent.getStringExtra("url"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.webview, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int ItemId = item.getItemId();

		if (ItemId == R.id.anotherApp) {
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mWebView.getUrl())));
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public class webViewChrome extends WebChromeClient {

		@Override
		public boolean onJsAlert(WebView view, String url, String message,
				JsResult result) {
			return super.onJsAlert(view, url, message, result);
		}

		@Override
		public boolean onJsBeforeUnload(WebView view, String url,
				String message, JsResult result) {
			return super.onJsBeforeUnload(view, url, message, result);
		}

		@Override
		public boolean onJsConfirm(WebView view, String url, String message,
				JsResult result) {
			return super.onJsConfirm(view, url, message, result);
		}

		@Override
		public boolean onJsPrompt(WebView view, String url, String message,
				String defaultValue, JsPromptResult result) {
			return super.onJsPrompt(view, url, message, defaultValue, result);
		}
	}

	public class webViewClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView mView, String url) {
			mView.loadUrl(url);

			return super.shouldOverrideUrlLoading(mView, url);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			if (mWebView.canGoBack()) {
				mWebView.goBack();
			} else {
				mWebView.clearCache(true);
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
