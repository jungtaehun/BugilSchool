package bugil.bada.bugilapp;

import bugil.bada.bugilapp.R;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;


public class Bada extends SherlockActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bada);
        //제작동아리 정보 리스트뷰 출력
        ArrayAdapter<CharSequence> Adapter;
        Adapter = ArrayAdapter.createFromResource(this, R.array.bada, android.R.layout.simple_list_item_1);
        ListView list = (ListView)findViewById(R.id.badalist);
        list.setAdapter(Adapter);
    }

    public boolean onCreateOptionMenu(Menu menu){
    	MenuInflater Inf = getSupportMenuInflater();
    	return true;
    	}
}
    
