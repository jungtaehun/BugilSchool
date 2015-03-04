package bugil.bada.bugilapp;

import bugil.bada.bugilapp.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		startActivity(new Intent(this, SplashActivity.class));
		
		setContentView(R.layout.splash1);
		Splash.this.finish();
	}

}
