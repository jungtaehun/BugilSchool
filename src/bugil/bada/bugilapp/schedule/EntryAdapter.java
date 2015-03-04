package bugil.bada.bugilapp.schedule;


import java.util.ArrayList;

import bugil.bada.bugilapp.schedule.Schedule.Item;

import bugil.bada.bugilapp.R;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EntryAdapter extends ArrayAdapter<Item> {

	private ArrayList<Item> mArrayList;
	private LayoutInflater mInflater;

	public EntryAdapter(Context mContext, ArrayList<Item> mArrayList) {
		super(mContext, 0, mArrayList);
		this.mArrayList = mArrayList;
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Item mitem = mArrayList.get(position);
		if (mitem == null)
			return convertView;

		if (mitem.isSection()) {
			SectionItem mSection = (SectionItem) mitem;
			convertView = mInflater.inflate(R.layout.schedule_section, null);

			convertView.setOnClickListener(null);
			convertView.setOnLongClickListener(null);
			convertView.setLongClickable(false);

			TextView sectionView = (TextView) convertView
					.findViewById(R.id.list_item_section_text);
			sectionView.setText(mSection.getTitle());

		} else {
			EntryItem mEntry = (EntryItem) mitem;
			convertView = mInflater.inflate(R.layout.schedule_entry, null);
			TextView mTitle = (TextView) convertView
					.findViewById(R.id.list_item_entry_title);
			TextView mSummary = (TextView) convertView
					.findViewById(R.id.list_item_entry_summary);

			if (mTitle != null)
				mTitle.setText(mEntry.mTitle);

			if (mSummary != null)
				mSummary.setText(mEntry.mSummary);

			if (mEntry.isHoliday) {
				mTitle.setTextColor(Color.RED);
				mSummary.setTextColor(Color.RED);
			}
		}

		return convertView;
	}
}
