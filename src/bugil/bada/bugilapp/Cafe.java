package bugil.bada.bugilapp;

import bugil.bada.bugilapp.R;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Cafe extends SherlockActivity {
    WebView mWeb;
    private Handler mHandler;
    private ProgressDialog mProgressDialog;

    @SuppressLint("SetJavaScriptEnabled")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바(액션바) 지우기
        Window win = getWindow();
        win.requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cafe);
        //핸들러 사용
        mHandler = new Handler();
        
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                mProgressDialog = ProgressDialog.show(Cafe.this,"", 
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
        //webview출력 및 카페 로드
        mWeb = (WebView)findViewById(R.id.web);
        mWeb.setWebViewClient(new mmWebClient());
        WebSettings set = mWeb.getSettings();
        set.setJavaScriptEnabled(true);
        set.setBuiltInZoomControls(true);
        mWeb.loadUrl("http://m.cafe.naver.com/bukils");
    }
    //클릭
    public void mOnClick(View v){
    	int id = v.getId();
    	//back버튼 클릭시 뒤로 가기
		if (id == R.id.back) {
			if(mWeb.canGoBack()) {
    			mWeb.goBack();
    		}
			//forward버튼 클릭 시 앞으로 가기
		} else if (id == R.id.forward) {
			if(mWeb.canGoForward()) {
    			mWeb.goForward();
    		}
			//home버튼 클릭시 처음 화면으로 다시 돌아가기
		} else if (id == R.id.home) {
			mWeb.loadUrl("http://m.cafe.naver.com/bukils");
			//exit버튼 클릭시 종료하기
		} else if (id == R.id.exit) {
			finish();
		}
    	
    }
//view가 URL을 불러내도록 하기
    class mmWebClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    //휴대폰의 back버튼 클릭 시 뒤로 가기
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWeb.canGoBack()) {
			mWeb.goBack();
			return true;
	        }
		return super.onKeyDown(keyCode, event);
	}
    public boolean onCreateOptionsMenu(Menu menu) {
    	getSupportMenuInflater().inflate(R.menu.cafe, menu);
        return true;

    }
    
}
