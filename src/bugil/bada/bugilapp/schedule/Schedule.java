package bugil.bada.bugilapp.schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import bugil.bada.bugilapp.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.tistory.whdghks913.croutonhelper.CroutonHelper;

import de.keyboardsurfer.android.widget.crouton.Style;

public class Schedule extends Activity {

	private ArrayList<Item> mItem = new ArrayList<Item>();
	private ListView mListview;
	private CroutonHelper mHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schedule_listview);

		mListview = (ListView) findViewById(R.id.mListView);
		mListview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> av, View view, int position,
					long ld) {
				EntryItem mEntryItem = (EntryItem) mItem.get(position);
				SimpleDateFormat sdFormat = new SimpleDateFormat(
						"yyyy.MM.dd (E)", Locale.KOREA);
				Calendar mCalendar = Calendar.getInstance();

				try {
					mCalendar.setTime(sdFormat.parse(mEntryItem.mSummary));
				} catch (ParseException e) {
					e.printStackTrace();
				}

				getTime(mCalendar.get(Calendar.YEAR),
						mCalendar.get(Calendar.MONTH),
						mCalendar.get(Calendar.DAY_OF_MONTH));
			}
		});

		
		mItem.add(new SectionItem("2015년 3월 일정"));
		mItem.add(new EntryItem("입학식", "2015.03.02 (월)"));
		mItem.add(new EntryItem("전국연합학력평가(전학년)", "2015.03.11 (수)"));
		mItem.add(new EntryItem("임시외박(~15)", "2015.03.12 (목)", true));
		mItem.add(new EntryItem("1학기 방과후 신청/개교기념일 휴무", "2015.03.13 (금)"));
		mItem.add(new EntryItem("학부모 상담주간(~27)", "2015.03.23 (월)"));
		mItem.add(new EntryItem("학부모총회/정기외박", "2015.03.27 (금)", true));

		mItem.add(new SectionItem("2015년 4월 일정"));
		mItem.add(new EntryItem("전국연합학력평가(3학년)", "2015.04.09 (목)"));
		mItem.add(new EntryItem("벚꽃제(학교 개방의 날)", "2015.04.11 (토)", true));
		mItem.add(new EntryItem("학부모컨퍼런스/정기외박", "2015.04.09 (금)", true));

		mItem.add(new SectionItem("2015년 5월 일정"));
		mItem.add(new EntryItem("1회고사(전학년)", "2015.05.04 (월)"));
		mItem.add(new EntryItem("1회고사(전학년)", "2015.05.06 (수)"));
		mItem.add(new EntryItem("1회고사(전학년)", "2015.05.07 (목)"));
		mItem.add(new EntryItem("북일정신함양프로그램", "2015.05.08 (금)"));
		mItem.add(new EntryItem("체육대회/정기외박", "2015.05.22 (금)", true));
		mItem.add(new EntryItem("1차 학교 설명회", "2015.05.30 (토)"));

		mItem.add(new SectionItem("2015년 6월 일정"));
		mItem.add(new EntryItem("전국연합학력평가(1,2학년)/대수능모의평가(3학년)", "2015.06.04 (목)"));
		mItem.add(new EntryItem("학업성취도평가(2학년)", "2015.06.24 (수)"));
		mItem.add(new EntryItem("수업공개기간(~26)", "2015.06.25 (목)"));
		mItem.add(new EntryItem("정기외박", "2015.06.26 (금)", true));
		mItem.add(new EntryItem("하계 방과후 신청", "2015.06.27 (토)"));
		
		mItem.add(new SectionItem("2015년 7월 일정"));
		mItem.add(new EntryItem("전국연합학력평가(3학년)", "2015.07.09 (목)"));
		mItem.add(new EntryItem("2회고사(전학년)", "2015.07.10 (금)"));
		mItem.add(new EntryItem("2회고사(전학년)", "2015.07.13 (월)"));
		mItem.add(new EntryItem("2회고사(전학년)", "2015.07.14 (화)"));
		mItem.add(new EntryItem("하계방학식", "2015.07.17 (금)", true));
		mItem.add(new EntryItem("2차 학교설명회/2학기 방과후 신청", "2015.07.18 (토)"));
		mItem.add(new EntryItem("하계 방과후학교(~8/7)", "2015.07.27 (월)"));
		
		mItem.add(new SectionItem("2015년 8월 일정"));
		mItem.add(new EntryItem("개학/학부모 상담주간(~28)", "2015.08.17 (월)"));
		mItem.add(new EntryItem("1회고사(3학년)", "2015.08.19 (수)"));
		mItem.add(new EntryItem("1회고사(3학년)", "2015.08.20 (목)"));
		mItem.add(new EntryItem("1회고사(3학년)", "2015.08.21 (금)"));
		mItem.add(new EntryItem("3차 학교설명회", "2015.08.22 (토)"));
		
		mItem.add(new SectionItem("2015년 9월 일정"));
		mItem.add(new EntryItem("전국연합학력평가(1,2학년)/대수능모의평가(3학년)", "2015.09.02 (수)"));
		mItem.add(new EntryItem("학부모컨퍼런스", "2015.09.05 (토)"));
		mItem.add(new EntryItem("4차 학교설명회", "2015.09.12 (토)"));
		mItem.add(new EntryItem("수업공개기간(~25)", "2015.09.24 (목)"));
		mItem.add(new EntryItem("정기외박", "2015.09.25 (금)", true));
		mItem.add(new EntryItem("추석연휴", "2015.09.26 (토)", true));
		mItem.add(new EntryItem("추석연휴", "2015.09.27 (일)", true));
		mItem.add(new EntryItem("추석연휴", "2015.09.28 (월)", true));
		mItem.add(new EntryItem("대체휴일", "2015.09.29 (화)", true));
		
		mItem.add(new SectionItem("2015년 10월 일정"));
		mItem.add(new EntryItem("5차 학교설명회", "2015.10.03 (토)"));
		mItem.add(new EntryItem("1회고사(1,2학년)", "2015.10.06 (월)"));
		mItem.add(new EntryItem("1회고사(1,2학년)", "2015.10.07 (화)"));
		mItem.add(new EntryItem("1회고사(1,2학년)", "2015.10.08 (수)"));
		mItem.add(new EntryItem("북일정신함양프로그램", "2015.10.09 (목)"));
		mItem.add(new EntryItem("전국연합학력평가(3학년)", "2015.10.13 (화)"));
		mItem.add(new EntryItem("정기외박", "2015.10.28 (수)", true));
		mItem.add(new EntryItem("재량휴업(1,2학년)", "2015.10.29 (목)", true));
		mItem.add(new EntryItem("재량휴업(1,2학년)", "2015.10.30 (금)", true));
		
		mItem.add(new SectionItem("2015년 11월 일정"));
		mItem.add(new EntryItem("재량휴업(1,2학년)", "2015.11.02 (월)", true));
		mItem.add(new EntryItem("2016 대입수능시험", "2015.11.12 (목)"));
		mItem.add(new EntryItem("2회고사(3학년)", "2015.11.16 (월)"));
		mItem.add(new EntryItem("전국연합학력평가(1,2학년)/2회고사(3학년)", "2015.11.17 (화)"));
		mItem.add(new EntryItem("2회고사(3학년)", "2015.11.18 (수)"));
		mItem.add(new EntryItem("학술예술제", "2015.11.26 (목)"));
		mItem.add(new EntryItem("학술예술제/정기외박", "2015.11.27 (금)", true));
		mItem.add(new EntryItem("동계방과후 신청", "2015.11.28 (토)"));
		
		mItem.add(new SectionItem("2015년 12월 일정"));
		mItem.add(new EntryItem("개학/학부모 상담주간(~28)", "2015.08.17 (월)"));
		mItem.add(new EntryItem("2회고사(1,2학년)", "2015.12.21 (월)"));
		mItem.add(new EntryItem("2회고사(1,2학년)", "2015.12.22 (화)"));
		mItem.add(new EntryItem("2회고사(1,2학년)", "2015.12.23 (수)"));
		mItem.add(new EntryItem("방학식(3학년)", "2015.12.24 (목)", true));
		mItem.add(new EntryItem("방학식(1,2학년)", "2015.12.30 (수)", true));
		

		EntryAdapter adapter = new EntryAdapter(this, mItem);
		mListview.setAdapter(adapter);

		mHelper = new CroutonHelper(this);
	}

	private void getTime(int year, int month, int day) {
		Calendar myTime = Calendar.getInstance();

		long nowTime = myTime.getTimeInMillis();
		myTime.set(year, month, day);
		long touchTime = myTime.getTimeInMillis();

		long diff = (touchTime - nowTime);

		boolean isPast = false;
		if (diff < 0) {
			diff = -diff;
			isPast = true;
		}

		int diffInt = (int) (diff /= 24 * 60 * 60 * 1000);

		String Text;
		if (isPast)
			Text = "선택하신 날짜는 " + diffInt + "일전 날짜입니다";
		else
			Text = "선택하신 날짜까지 " + diffInt + "일 남았습니다";

		mHelper.clearCroutonsForActivity();
		mHelper.setText(Text);
		mHelper.setStyle(Style.INFO);
		mHelper.show();
	}

	public interface Item {
		public boolean isSection();
	}
}