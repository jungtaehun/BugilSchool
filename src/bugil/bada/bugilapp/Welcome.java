package bugil.bada.bugilapp;

import bugil.bada.bugilapp.R;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.actionbarsherlock.app.SherlockActivity;



public class Welcome extends SherlockActivity {
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
        setContentView(R.layout.activity_welcome);
        //핸들러 사용
mHandler = new Handler();
        
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                mProgressDialog = ProgressDialog.show(Welcome.this,"", 
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
        //webview 출력
        aWeb = (WebView)findViewById(R.id.welcomeweb);
        aWeb.setWebViewClient(new AAWeb());
        WebSettings go = aWeb.getSettings();
        go.setJavaScriptEnabled(true);
        go.setBuiltInZoomControls(true);
        aWeb.loadUrl("http://m.bugil.hs.kr/mobile/admission/admission01.jsp");
        
    }
   
}
//webview Client가 url를 불러오게 하기
class AAWeb extends WebViewClient {
    public boolean shouldOverrideUrlLoading(WebView aaview, String qurl) {
        aaview.loadUrl(qurl);
        return true;
    }
}
