package bugil.bada.bugilapp.bap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import bugil.bada.bugilapp.R;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

class BapViewHolder {
	public TextView mMorning;
	public TextView mLunch;
	public TextView mNight;
	public TextView mDate;
	public TextView mCalender;
}

class BapListViewAdapter extends BaseAdapter {
	private Context mContext = null;
	private ArrayList<BapListData> mListData = new ArrayList<BapListData>();
	private Calendar currentTime = Calendar.getInstance();
	private final int YEAR, MONTH, DAY_OF_MONTH;

	private final boolean isKeyWord;
	private final String mKeyWord;

	public BapListViewAdapter(Context mContext, boolean isKeyWord,
			String mKeyWord) {
		super();

		this.mContext = mContext;
		YEAR = currentTime.get(Calendar.YEAR);
		MONTH = currentTime.get(Calendar.MONTH);
		DAY_OF_MONTH = currentTime.get(Calendar.DAY_OF_MONTH);

		this.isKeyWord = isKeyWord;

		if (mStringCheck(mKeyWord))
			this.mKeyWord = null;
		else
			this.mKeyWord = mKeyWord;

	}

	public void addItem(String mCalender, String mDate, String mMorning,
			String mLunch, String mNight) {

		BapListData addInfo = new BapListData();
		addInfo.mCalender = mCalender;
		addInfo.mDate = mDate;
		addInfo.mMorning = mMorning;
		addInfo.mLunch = mLunch;
		addInfo.mNight = mNight;

		mListData.add(addInfo);
	}

	public void clearData() {
		mListData.clear();
	}

	@Override
	public int getCount() {
		return mListData.size();
	}

	@Override
	public BapListData getItem(int position) {
		return mListData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BapViewHolder holder;

		if (convertView == null) {
			holder = new BapViewHolder();

			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.bap_list_item, null);

			holder.mDate = (TextView) convertView.findViewById(R.id.mDate);
			holder.mCalender = (TextView) convertView
					.findViewById(R.id.mCalender);
			holder.mMorning = (TextView) convertView
					.findViewById(R.id.mMorning);
			holder.mLunch = (TextView) convertView.findViewById(R.id.mLunch);
			holder.mNight = (TextView) convertView.findViewById(R.id.mNight);

			convertView.setTag(holder);

		} else {
			holder = (BapViewHolder) convertView.getTag();
		}

		BapListData mData = mListData.get(position);

		String mDate = mData.mDate;
		if ("일요일".equals(mDate)) {
			holder.mCalender.setTextColor(Color.RED);
			holder.mDate.setTextColor(Color.RED);

		} else if ("토요일".equals(mDate)) {
			holder.mCalender.setTextColor(Color.BLUE);
			holder.mDate.setTextColor(Color.BLUE);

		} else {
			holder.mCalender.setTextColor(Color.BLACK);
			holder.mDate.setTextColor(Color.BLACK);
		}

		String Calender = mData.mCalender;
		String mMorning = mData.mMorning;
		String mLunch = mData.mLunch;
		String mNight = mData.mNight;

		if (mStringCheck(mMorning))
			mMorning = mData.mMorning = "아침이 없습니다";
		if (mStringCheck(mLunch))
			mLunch = mData.mLunch = "점심이 없습니다";
		if (mStringCheck(mNight))
			mNight = mData.mNight = "저녁이 없습니다";

		holder.mCalender.setText(Calender);
		holder.mDate.setText(mDate);
		holder.mMorning.setText(mMorning);
		holder.mLunch.setText(mLunch);
		holder.mNight.setText(mNight);

		LinearLayout bapListLayout = (LinearLayout) convertView
				.findViewById(R.id.bapListLayout);

		try {
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy.MM.dd(E)",
					Locale.KOREA);

			Calendar Date = Calendar.getInstance();
			Date.setTime(sdFormat.parse(Calender));

			if (Date.get(Calendar.YEAR) == YEAR
					&& Date.get(Calendar.MONTH) == MONTH
					&& Date.get(Calendar.DAY_OF_MONTH) == DAY_OF_MONTH) {

				bapListLayout.setBackgroundColor(mContext.getResources()
						.getColor(R.color.background));

				return convertView;

			} else {
				bapListLayout.setBackgroundColor(mContext.getResources()
						.getColor(android.R.color.transparent));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (isKeyWord) {
			if (keyWordCheck(mLunch) || keyWordCheck(mNight)
					|| keyWordCheck(mMorning)) {
				bapListLayout.setBackgroundColor(mContext.getResources()
						.getColor(R.color.background_blue));
				return convertView;
			}
		}

		return convertView;
	}

	private boolean keyWordCheck(String Meal) {
		if (Meal.indexOf(mKeyWord) == -1)
			return false;
		return true;
	}

	private boolean mStringCheck(String mString) {
		if (mString == null || "".equals(mString) || " ".equals(mString))
			return true;
		return false;
	}
}

class BapListData {
	/**
	 * 리스트 정보를 담고 있을 객체 생성
	 */
	public String mCalender;
	public String mDate;

	public String mMorning;
	public String mLunch;
	public String mNight;
}