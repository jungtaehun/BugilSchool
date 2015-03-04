package bugil.bada.bugilapp;

import bugil.bada.bugilapp.R;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.tistory.whdghks913.croutonhelper.CroutonHelper;

public class THFeedbacks extends Activity {

	private CroutonHelper mHelper;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teedbacks);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			ActionBar actionBar = getActionBar();
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}

		
	}
	
	public void feedback(View view){
		Intent it = new Intent(Intent.ACTION_SEND);     
		  
		String[] ccs = {"wxg1297@gmail.com"};     
		   
		it.putExtra(Intent.EXTRA_EMAIL, ccs);     
		it.putExtra(Intent.EXTRA_TEXT, "여기에 피드백 내용을 입력해 주세요!");     
		    
		it.setType("message/rfc822");     
		startActivity(Intent.createChooser(it, "Choose Email Client"));  
		
	}

	


}
