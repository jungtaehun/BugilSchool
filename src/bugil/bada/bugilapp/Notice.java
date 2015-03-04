package bugil.bada.bugilapp;

import bugil.bada.bugilapp.R;
import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Notice extends Activity {
    WebView aWeb;
    private Handler mHandler;
    private ProgressDialog mProgressDialog;

    @SuppressLint("SetJavaScriptEnabled")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //액션바 숨기기
        Window win = getWindow();
        win.requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_notice);
        //핸들러 실행
        mHandler = new Handler();
        
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                mProgressDialog = ProgressDialog.show(Notice.this,"", 
                        "잠시만 기다려 주세요.",true);
                mHandler.postDelayed( new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            if (mProgressDialog!=null&&mProgressDialog.isShowing()){
                                mProgressDialog.dismiss();
                            }
                        }
                        catch ( Exception e )
                        {
                            e.printStackTrace();
                        }
                    }
                }, 2000);
            }
        } );
        //webview 실행
        aWeb = (WebView)findViewById(R.id.webView1);
        aWeb.setWebViewClient(new MyWeb());
        WebSettings go = aWeb.getSettings();
        go.setJavaScriptEnabled(true);
        go.setBuiltInZoomControls(true);
        aWeb.loadUrl("http://m.bugil.hs.kr/mobile/news/notice_list.jsp");
    }
    //핸드폰의 뒤로가가 버튼 클릭시 뒤로 가기
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && aWeb.canGoBack()) {
			aWeb.goBack();
			return true;
	        }
		
		return super.onKeyDown(keyCode, event);
	}

    
}
//webview client가 url을 불러오기
class MyWeb extends WebViewClient {
    public boolean shouldOverrideUrlLoading(WebView aview, String qurl) {
        aview.loadUrl(qurl);
        return true;
    }
}
