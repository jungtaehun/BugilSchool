package bugil.bada.bugilapp.timetable;

import java.util.ArrayList;

import bugil.bada.bugilapp.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

class ViewHolder {
	public TextView Time;
	public TextView Subject;
	public TextView Room;
}

public class TimeTableShowAdapter extends BaseAdapter {
	private Context mContext = null;
	public ArrayList<ListData> mListData = new ArrayList<ListData>();

	public TimeTableShowAdapter(Context mContext) {
		super();
		this.mContext = mContext;
	}

	public void addItem(int Time, String Subject, String Room) {
		ListData addInfo = new ListData();
		addInfo.Time = Time;
		addInfo.Subject = Subject;
		addInfo.Room = Room;

		mListData.add(addInfo);
	}

	@Override
	public int getCount() {
		return mListData.size();
	}

	@Override
	public ListData getItem(int position) {
		return mListData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();

			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.timetable_time_row, null);

			holder.Time = (TextView) convertView.findViewById(R.id.Time);
			holder.Subject = (TextView) convertView.findViewById(R.id.Subject);
			holder.Room = (TextView) convertView.findViewById(R.id.Room);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		ListData mData = mListData.get(position);

		holder.Time.setText(mData.Time + "교시");
		holder.Subject.setText(mData.Subject);

		if (mData.Room == null)
			holder.Room.setVisibility(View.GONE);
		else {
			holder.Room.setVisibility(View.VISIBLE);
			holder.Room.setText(mData.Room);
		}

		return convertView;
	}
}

class ListData {
	/**
	 * 리스트 정보를 담고 있을 객체 생성
	 */

	public int Time;
	public String Subject;
	public String Room;
}