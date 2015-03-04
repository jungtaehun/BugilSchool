package bugil.bada.bugilapp;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import bugil.bada.bugilapp.R;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;
import com.tyczj.extendedcalendarview.CalendarProvider;
import com.tyczj.extendedcalendarview.Event;
import com.tyczj.extendedcalendarview.ExtendedCalendarView;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.graphics.Typeface;
import android.text.format.Time;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class Cal extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cal);
		ExtendedCalendarView calendar = (ExtendedCalendarView)findViewById(R.id.calendar);

		
		ContentValues values = new ContentValues();
	    values.put(CalendarProvider.COLOR, Event.COLOR_RED);
	    values.put(CalendarProvider.DESCRIPTION, "Some Description");
	    values.put(CalendarProvider.LOCATION, "Some location");
	    values.put(CalendarProvider.EVENT, "Event name");

	    Calendar cal = Calendar.getInstance();

	    cal.set(2015, 1, 5, 0, 0);
	    cal.set(2015, 1, 5, 12, 0);
	    values.put(CalendarProvider.START, cal.getTimeInMillis());
	    TimeZone tz = TimeZone.getDefault();
	    values.put(CalendarProvider.END, cal.getTimeInMillis());

	    Uri uri = getContentResolver().insert(CalendarProvider.CONTENT_URI, values);
		
		
		
	}
}
